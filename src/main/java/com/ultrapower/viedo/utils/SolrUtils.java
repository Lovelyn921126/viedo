/*
 * FileName: SolrUtils.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

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
 * 2018年1月2日 下午6:37:01          dell        4.1         To create
 * </p>
 *
 * @since 
 * @see     
 */
public class SolrUtils {
    private final static String URL = "http://192.168.180.94:8800/solr/";
    private static HttpSolrClient service = null;

    public static HttpSolrClient getSolrClient(String core) {
        service = new HttpSolrClient(URL + core);
        service.setConnectionTimeout(10);
        return service;
    }

}
