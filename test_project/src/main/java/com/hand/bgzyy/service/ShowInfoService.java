package com.hand.bgzyy.service;

import com.hand.bgzyy.bean.AllData;
import com.hand.bgzyy.bean.GpBean;
import com.hand.bgzyy.dao.ProductMapper;
import com.hand.bgzyy.dao.ShowInfoToPageMapper;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/6.
 */
@Service
public class ShowInfoService {
    @Autowired
    private ShowInfoToPageMapper showInfoToPageMapper;

    public List<GpBean> getGpBenFromTab() {
        List<GpBean> gpNameFormTable = showInfoToPageMapper.getGpNameFormTable();
        return gpNameFormTable;
    }

    public List<AllData> queryHistoryWithCode(String code) {
        return showInfoToPageMapper.queryHistoryInfoWithCode(code);
    }

    public List<AllData> queryHistoryWithName(String name) {
        return showInfoToPageMapper.queryHistoryInfoWithName(name);
    }

    public List<AllData> queryThirtyDaysInfo(String code) {
        return showInfoToPageMapper.queryThirtyDaysInfo(code);
    }

    public List<String> getAllString() {
        return showInfoToPageMapper.getAllNameFromZf();
    }

    public Integer getZfNumWithName(String name) {
        return showInfoToPageMapper.getZfNum(name);
    }
}