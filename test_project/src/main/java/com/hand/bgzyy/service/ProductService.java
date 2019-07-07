package com.hand.bgzyy.service;

import com.hand.bgzyy.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/7.
 */
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Integer> getAllCodeFromTab() {
        return productMapper.getCodeFromTab();
    }
}