package com.hand.bgzyy.dao;

import com.hand.bgzyy.bean.AllData;
import com.hand.bgzyy.bean.GpBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/6.
 */
@Repository
public interface ShowInfoToPageMapper {

    List<GpBean> getGpNameFormTable();

    List<AllData> queryHistoryInfoWithCode(String code);

    List<AllData> queryHistoryInfoWithName(String name);

    List<AllData> queryThirtyDaysInfo(String code);
}