package com.example.crawl.controller;

import com.example.crawl.domain.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class crawlController {

    final String getOpggUrl="https://www.op.gg/summoners/kr/";

    @PostMapping("/info/detail")
    public String getInfo(UserDto dto){
    log.info("[userId] "+dto.getStreamId());
    String [] userIds= dto.getStreamId().split(",");
    String url="";
    Connection conn;
        for (String users: userIds
             ) {
          url = getOpggUrl+users;
          conn= Jsoup.connect(url);


            try{

                Document document = conn.get();
                Elements tierElements = document.getElementsByClass("tier");
                Elements lpElements = document.getElementsByClass("lp");
                Elements gameContents = document.select("#content-container > div.css-150oaqg.e1shm8tx0 > div.css-3i6n1d.ehasqiv3");

                for (Element ele: gameContents
                ) {
                    System.out.println("[element val]: "+ele.text());
                }
                String [] tierBox = tierElements.text().split(" ");
                String [] lpBox = lpElements.text().split(" ");


                System.out.println("[스트리머의 티어]: "+tierBox[0]);
                System.out.println("[스트리머의 Lp]: "+lpBox[0]);



            }catch (IOException e){
                e.printStackTrace();
            }


        }





        return "/crawling/detail";
    }
}
