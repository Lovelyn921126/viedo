/*
 * FileName: PropertiesFileUtil.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

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
 * 2017年12月29日 下午2:44:32          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class PropertiesFileUtil {
    /**
     *   获取配置文件的 值
     * @param adress 配置文件的地址
     * @param key  配置文件的key
     * @return
     */
    public static String getProperties(String adress, String key) {
        InputStream reader = PropertiesFileUtil.class.getClassLoader().getResourceAsStream(adress);
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (Exception e) {
            return key;
        } finally {
            IOUtils.closeQuietly(reader);
        }
        String value = properties.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return value;

    }

    /**
    *获取配置文件的 值
    * @param adress 配置文件的地址
    * @param key 配置文件的key
    * @param encoder 配置文件编码
    * @return
    */
    public static String getProperties(String adress, String key, String encoder) {
        InputStreamReader reader = null;
        Properties properties = new Properties();
        try {
            reader = new InputStreamReader(PropertiesFileUtil.class.getClassLoader().getResourceAsStream(adress), encoder);
            properties.load(reader);
        } catch (Exception e) {
            return key;
        } finally {
            IOUtils.closeQuietly(reader);
        }
        String value = properties.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return value;

    }

    /**
     *  获取配置
     * @param adress
     * @param key
     * @param split
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] getProperties(String adress, String key, String split, Class<T> type) {
        String str = getProperties(adress, key);
        if (str.equals(key)) {
            return null;
        }

        return (T[]) ConvertUtils.convert(StringUtils.split(str, split), type);

    }

    public static List<String[]> getProperties(String adress) throws IOException {
        InputStream inputStream = PropertiesFileUtil.class.getClassLoader().getResourceAsStream(adress);
        BufferedReader bStream = new BufferedReader(new InputStreamReader(inputStream));
        List<String[]> result = new ArrayList<>();
        String line = null;
        try {
            if ((line = bStream.readLine()) != null) {
                String[] array = line.split("=");
                array[0] = array[0].trim();
                array[1] = array[1].trim();
                result.add(array);
            }
        } catch (IOException e) {

        } finally {
            inputStream.close();
        }
        return result;
    }

    public static Map<String, Object> getPropertiesMap(String adress) throws IOException {
        InputStream inputStream = PropertiesFileUtil.class.getClassLoader().getResourceAsStream(adress);
        BufferedReader bStream = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, Object> result = new HashMap();
        String line = null;
        try {
            if ((line = bStream.readLine()) != null) {
                String[] array = line.split("=", 2);
                if (array.length == 2 && StringUtils.isNotBlank(array[0].trim()) && StringUtils.isNotBlank(array[1].trim())) {
                    result.put(array[0].trim(), array[1].trim());
                }

            }
        } catch (IOException e) {

        } finally {
            inputStream.close();
        }
        return result;
    }

}
