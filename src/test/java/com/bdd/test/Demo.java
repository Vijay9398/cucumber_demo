package com.bdd.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {

    public static void main(String[] args) {

        String text = "user opens 'policy center'";
        Pattern pattern = Pattern.compile("'(.*)'");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            int start_index = matcher.start();
            int end_index = matcher.end();
            String value = text.substring(start_index,end_index);
            System.out.println(value);
        }
    }

}
