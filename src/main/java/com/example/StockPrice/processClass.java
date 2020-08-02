package com.example.StockPrice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class processClass {

    public static List<String> stckList = new ArrayList<>();
    public List<String> getStockList(String requestString){
     if(requestString.contains(",")) {
         String[] tempList = requestString.split(",");

         for (String str : tempList) {
             if (str.contains("IND")) {
                 String tmp = str.substring(0, str.length() - 3);
                 stckList.add(tmp + "BO");
                 stckList.add(tmp + "NS");
             } else {
                 String tmp1 = str.substring(0, str.length() - 3);
                 stckList.add(tmp1);
             }
         }
     }else{
         String tmp = requestString.substring(0, requestString.length() - 3);
         stckList.add(tmp + "BO");
         stckList.add(tmp + "NS");
     }
        return stckList;
    }
}
