package com.ben.interview;

import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulator {
    public static String callAPI(String acctNumber) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = "http://localhost:8080/loans/"+acctNumber;
            return restTemplate.getForObject(url, String.class);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public static void main(String[] args){
        String[] accountNumbers = {"42032075121","4203207510","4203207511", "4203207512", "4203207513",
                "4203207514","4203207515","4203207516","4203207517","4203207518","4203207519"};
        List<String> list = new ArrayList<>(Arrays.asList(accountNumbers));

        String filePath=System.getProperty("user.home")+"/APIResponse.txt";
        File file = new File(filePath);
        FileWriter fr = null;
        BufferedWriter br = null;
        final String[] response = {null};

        try {
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);

            BufferedWriter finalBr = br;
            list.forEach(acctNumber-> {
                try {
                   response[0] = Simulator.callAPI(acctNumber);
                    finalBr.write(acctNumber.concat("        ").concat(response[0]));
                    finalBr.newLine();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(br!=null)
                    br.close();
                if(fr!=null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
