package com.example.demo.rlinker;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@RestController
public class RLinker {


    @RequestMapping(value = "/rscriptexec", method = {RequestMethod.GET, RequestMethod.POST})
    public String rScriptExec() {
        System.out.println("rScriptExec");
        try {
            System.out.println(1);
            String[] cmd = new String[]{
                    "sh",
                    "/dev_script/java_r_connect/script/run.sh"
            };
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd); //시스템 명령어
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return "rscriptexec";
    }

    @RequestMapping(value = "/rlinker", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject rconnect() {
        JSONObject result = new JSONObject();
        JSONArray year = new JSONArray();
        JSONArray gdp = new JSONArray();
        try {
            //파일 객체 생성
            File file = new File("/dev_script/java_r_connect/script/result_data.csv");
//            File file = new File("/home/wkwon/dev/Rproject/test/result_data.csv");

            if (file.exists()) {
                BufferedReader inFile = new BufferedReader(new FileReader(file));
                String sLine = null;
                boolean first = true;
                while ((sLine = inFile.readLine()) != null) {
                    String[] r= sLine.split(",");
                    if(first){
                        first = false;
                        continue;
                    }
                    year.add(r[1]);
                    gdp.add(r[2]);

                }
                //읽어들인 문자열을 출력 합니다.
            }
            result.put("year",year);
            result.put("gdp",gdp);


        } catch (FileNotFoundException e) {
            // TODO: handle exception
        } catch (IOException e) {
            System.out.println(e);
        }


        return result;
    }
}