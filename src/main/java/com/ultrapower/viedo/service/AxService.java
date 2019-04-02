/*
 * FileName: AxService.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultrapower.viedo.bean.Ad;
import com.ultrapower.viedo.bean.Articles;
import com.ultrapower.viedo.dao.one.AdMapper;
import com.ultrapower.viedo.dao.two.ArticlesMapper;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2019年4月2日 上午11:04:31          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Service
public class AxService {

    @Autowired
    AdMapper adMapper;
    @Autowired
    ArticlesMapper articlesMapper;

    public void name() {
        Ad ad = new Ad();
        ad.setContent("测试");
        adMapper.insert(ad);
        Articles articles = new Articles();
        articles.setBody("articles body");
        articles.setTitle("artiles title");
        articlesMapper.insert(articles);
        //int a = 1 / 0;
    }
}
