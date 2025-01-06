package com.dayone;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        try{
            Connection con = Jsoup.connect("");
            Document doc = con.get();

            Elements eles = doc.getElementsByAttributeValue("data-test", "historical-prices");
            Element ele = eles.get(0);

            Element tbody = ele.children().get(1);

            for(Element e : tbody.children()){
                String txt = e.text();
                if(!txt.endsWith("Dividend")){
                    continue;
                }

                String[] splits = txt.split(" ");
                String month = splits[0];
                int day = Integer.valueOf(splits[1].replace(",",""));
                int year = Integer.valueOf(splits[2]);
                String dividend = splits[3];
            }

        } catch(IOException e){
            e.printStackTrace();;
        }
    }
}
