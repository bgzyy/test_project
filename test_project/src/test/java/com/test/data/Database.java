package com.test.data;

import com.hand.bgzyy.bean.AllData;
import com.hand.bgzyy.bean.GpBean;
import com.hand.bgzyy.dao.ProductMapper;
import com.hand.bgzyy.dao.ShowInfoToPageMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/5.
 */
public class Database {

    private ApplicationContext context;
    private DataSource dataSource;
    private ProductMapper productMapper;
    private ShowInfoToPageMapper showInfoToPageMapper;

    {
        context = new ClassPathXmlApplicationContext("Spring_config.xml");
        dataSource = context.getBean(DataSource.class);
        productMapper = context.getBean(ProductMapper.class);
        showInfoToPageMapper = context.getBean(ShowInfoToPageMapper.class);
    }

    @Test
    public void testConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testGetNameAndCode() {
        List<GpBean> gpBeanList = showInfoToPageMapper.getGpNameFormTable();
        System.out.println(gpBeanList.size());
    }

    @Test
    public void testInsertManyProduct() throws ParseException {
        List<AllData> allDataList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            allDataList.add(new AllData("ssssss", "30000", "2001-1-1", 10001.1));
        }
        productMapper.insertListToInfoTab(allDataList);

        for (int i = 0; i < allDataList.size(); i++) {
            System.out.println(allDataList.get(i));
        }
    }

    @Test
    public void testOne() {
        AllData allData = new AllData("ssssss", "30000", "2001-1-1", 10001.1);
        productMapper.insertToInfoTab(allData);
    }

    @Test
    public void testGetCodeFromTab() {
        List<Integer> codeFromTab = productMapper.getCodeFromTab();
        List<String> codeStrList = new ArrayList<>();
        System.out.println(codeFromTab.size());
        for (Integer code : codeFromTab) {
            codeStrList.add(code.toString());
        }
        List<String> allCodeList = testGetCode();
        System.out.println(allCodeList.size());
        allCodeList.removeAll(codeStrList);
        System.out.println(allCodeList.size());
    }

    public List<String> testGetCode() {
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
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codeList;
    }

    @Test
    public void testQuery() {
        List<AllData> allDataList = showInfoToPageMapper.queryHistoryInfoWithCode("600000");
        System.out.println(allDataList.size());
    }

    @Test
    public void testCalculate() {
        List<AllData> allDataList = showInfoToPageMapper.queryThirtyDaysInfo("600000");
        double result;
        for (int i = 0; i < allDataList.size() - 1; i++) {
            result = (allDataList.get(i).getPrice() - allDataList.get(i + 1).getPrice()) / allDataList.get(i + 1).getPrice();
            System.out.println(result * 100 + " --> " + (double)Math.round(result * 100 * 100) / 100);
        }
    }
}