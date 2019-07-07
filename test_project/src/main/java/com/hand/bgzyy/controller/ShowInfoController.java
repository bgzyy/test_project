package com.hand.bgzyy.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hand.bgzyy.bean.AllData;
import com.hand.bgzyy.bean.GpBean;
import com.hand.bgzyy.bean.ZhangFu;
import com.hand.bgzyy.service.ProductService;
import com.hand.bgzyy.service.ShowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhao'yin
 * Date 2019/7/6.
 */
@Controller
public class ShowInfoController {

    @Autowired
    private ShowInfoService showInfoService;
    @Autowired
    private ProductService productService;

    @RequestMapping("getPageInfo")
    public ModelAndView firstPageInfo(Integer pageNum) {
        ModelAndView modelAndView = new ModelAndView("first");

        Page<GpBean> pages = PageHelper.startPage(pageNum, 48);
        List<GpBean> gpBenFromTab = showInfoService.getGpBenFromTab();
        PageInfo<GpBean> pageInfo = new PageInfo<>(gpBenFromTab);

        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    @RequestMapping("queryGpInfo")
    public ModelAndView queryGpInfoWithGpCodeOrGpName(String gpCodeOrName, Integer pageNum) {
        ModelAndView modelAndView = new ModelAndView("historyPage");
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(gpCodeOrName);
        List<AllData> allDataList;

        Page<GpBean> pages = PageHelper.startPage(pageNum, 13);
        if (matcher.matches()) {
            allDataList = showInfoService.queryHistoryWithCode(gpCodeOrName);
        } else {
            allDataList = showInfoService.queryHistoryWithName(gpCodeOrName);
        }
        PageInfo<AllData> pageInfo = new PageInfo<>(allDataList);

        modelAndView.addObject("gpName", allDataList.get(0).getGpName());
        modelAndView.addObject("gpCode", allDataList.get(0).getGpCode());
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    @RequestMapping("getAllZhangFuInfo")
    public ModelAndView getAllThirtyDaysInfo() {
        ModelAndView modelAndView = new ModelAndView("ZhangFu");
        List<Integer> allCodeFromTab = productService.getAllCodeFromTab();
        List<List<ZhangFu>> allZhangFuList = new ArrayList<>();
        for (Integer code : allCodeFromTab) {
            List<AllData> allDataList = showInfoService.queryThirtyDaysInfo(code.toString());
            List<ZhangFu> calculate = calculate(allDataList);
            allZhangFuList.add(calculate);
        }
        modelAndView.addObject("allZhangFuList", allZhangFuList);
        return modelAndView;
    }

    public List<ZhangFu> calculate(List<AllData> allDataList) {
        double result;
        List<ZhangFu> zhangFuList = new ArrayList<>();
        for (int i = 0; i < allDataList.size() - 1; i++) {
            result = (allDataList.get(i).getPrice() - allDataList.get(i + 1).getPrice()) / allDataList.get(i + 1).getPrice();
            result = (double) Math.round(result * 100 * 100) / 100;
            if (result > 5) {
                zhangFuList.add(new ZhangFu(allDataList.get(i), result));
            }
        }
        return zhangFuList;
    }
}