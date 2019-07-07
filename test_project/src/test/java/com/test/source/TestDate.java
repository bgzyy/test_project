package com.test.source;

import org.junit.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/4.
 */
public class TestDate {

    @Test
    public void testDate() {
        Integer year = 2019;
        Integer jidu = 4;
        LocalDate date = LocalDate.now();
        Integer monthValue = date.getMonthValue();
        int dayOfYear = date.getYear();

        for (int i = year; i >= 2009; i--) {
            if (i == dayOfYear) {
                switch (monthValue) {
                    case 1:
                    case 2:
                    case 3:
                        jidu = 1;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        jidu = 2;
                        break;
                    case 7:
                    case 8:
                    case 9:
                        jidu = 3;
                        break;
                    case 10:
                    case 11:
                    case 12:
                        jidu = 4;
                        break;
                }
            }
            for (int j = jidu; j > 0; j--) {
                String lastUrl = "?year=" + i + "&jidu=" + j;
                System.out.println(lastUrl);
            }
            jidu = 4;
        }
    }

    @Test
    public void testGetCode() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("code.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> codeList = new ArrayList<>();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 0) {
                    Integer index = line.indexOf("(");
                    String code = line.substring(index + 1, line.length() - 1);
                    codeList.add(code);
                    System.out.println(code);
                }
            }
            System.out.println(codeList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}