package com.hand.bgzyy.dao;

import com.hand.bgzyy.bean.AllData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/5.
 */
@Repository
public interface ProductMapper {

    void insertToInfoTab(AllData data);

    List<Integer> getCodeFromTab();

    void insertListToInfoTab(List<AllData> list);
}