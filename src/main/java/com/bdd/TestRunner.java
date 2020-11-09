package com.bdd;

import com.bdd.annotations.Action;
import com.bdd.extractor.ManualSteps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) throws Exception {
        File file = new File("src/main/resources/feature.txt");
        FileReader reader = new FileReader(file);
        BufferedReader bf = new BufferedReader(reader);
        List<String> actions = new ArrayList<String>();
        String line = bf.readLine();
        while(line != null){
            actions.add(line);
            line = bf.readLine();
        }
        bf.close();
        reader.close();
        Method[] methods = ManualSteps.class.getMethods();
        for(String action : actions){
            boolean flag = false;
            for(Method method : methods){
                Annotation[] annotations = method.getDeclaredAnnotations();
                for(Annotation annotation: annotations){
                    if(annotation instanceof Action){
                        if(((Action) annotation).value().equals(action)){
                            method.invoke(new ManualSteps());
                            flag = true;
                        }
                    }
                }
            }
            if(!flag){
                throw new Exception("unable to find a method for the action : "+action);
            }
        }
    }

}
