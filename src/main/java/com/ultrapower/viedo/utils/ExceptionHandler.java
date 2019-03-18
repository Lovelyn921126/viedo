/*
 * FileName: ExceptionHandler.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ultrapower.viedo.utils.web.R;

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
 * 2018年4月17日 上午10:04:49          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    private Logger log = LoggerFactory.getLogger(getClass());

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        PrintWriter writer = null;
        R r = new R();
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            writer = response.getWriter();
            if (ex instanceof CommonExpection) {
                r.put("code", ((CommonExpection) ex).getCode());
                r.put("msg", ((CommonExpection) ex).getMsg());
            } else {
                r.put("code", 500);
                r.put("msg", ex.getMessage());

            }
            String result = JSON.toJSONString(r);
            writer.write(result);
            log.error(result + "=========error");
        } catch (Exception e) {
            log.error("异常解析失败", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return new ModelAndView();
    }

}
