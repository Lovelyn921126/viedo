/*
 * FileName: ListenUtil.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ultrapower.viedo.constants.Constants;

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
 * 2017年12月29日 下午2:34:52          dell        4.1         To create
 * </p>
 *
 * @since 
 * @see     
 */
public class ListenUtil implements ServletContextListener {

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        /**
         * //图片上传的高
         */
        Constants.UPLOAD_IMG_HEIGHT = PropertiesFileUtil.getProperties("config.property", "");

        /**
         *  ////图片上传的宽
         */
        Constants.UPLOAD_IMG_WEIGH = PropertiesFileUtil.getProperties("", "");
        /**
         * //图片上传的高--首页
         */
        Constants.UPLOAD_MAX_IMG_HEIGHT = PropertiesFileUtil.getProperties("", "");
        /**
         * 图片上传的宽--首页
         */
        Constants.UPLOAD_MAX_IMG_WEIGH = PropertiesFileUtil.getProperties("", "");
        /**
         * 图片上传的高--相似推荐
         */
        Constants.UPLOAD_SMALL_IMG_HEIGHT = PropertiesFileUtil.getProperties("", "");
        /**
         * 图片上传的宽--相似推荐
         */
        Constants.UPLOAD_SMALL_IMG_WEIGH = PropertiesFileUtil.getProperties("", "");
        /**
         * 视频上传的路径
         */
        Constants.UPLOAD_IMG_PATH = PropertiesFileUtil.getProperties("", "");
        /**
         * 临时文件的路径
         */
        Constants.UPLOAD_TEMP_PATH = PropertiesFileUtil.getProperties("", "");
        /**
         * 图片上传格式
         */
        Constants.UPLOAD_IMG_FORMAT = PropertiesFileUtil.getProperties("", "");
        /**
         * 图片上传的大小
         */
        Constants.UPLOAD_IMG_SIZE = PropertiesFileUtil.getProperties("", "");
        /**
         * 视频上传的格式
         */
        Constants.UPLOAD_VIEDO_FORMAT = PropertiesFileUtil.getProperties("", "");
        /**
         * 视频上传的大小
         */
        Constants.UPLOAD_VIEDO_SIZE = PropertiesFileUtil.getProperties("", "");
        /**
         * 微课程文件的访问地址
         */
        Constants.VK_DOWN_URL = PropertiesFileUtil.getProperties("", "");
        /**
         * 微课程 文件服务器的项目名称 用来 截取路径
         */
        Constants.VK_PROJECT_URL = PropertiesFileUtil.getProperties("", "");
        /**
         * 微课程 文件服务器的地址
         */
        Constants.VK_UOLOADFILE_URL = PropertiesFileUtil.getProperties("", "");
        /**
         *  微课程文件的访问地址
         */
        Constants.VK_DOWN_URL = PropertiesFileUtil.getProperties("", "");

    }

}
