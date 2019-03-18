/*
 * FileName: ExceptionHandler.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.exception;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ultrapower.viedo.bean.ProvinceSynchroResult;
import com.ultrapower.viedo.constants.Constants;
import com.ultrapower.viedo.utils.DateTime;
import com.ultrapower.viedo.utils.SftpUtils;
import com.ultrapower.viedo.utils.XMLUtil;

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
 * 2018年6月28日 下午11:42:53          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    //省分同步全量数据FTP 文件读取完成之后 返回的文件后缀
    private final String FtpCompleteFileSuffix = Constants.FtpCompleteFileSuffix;

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) {
        PrintWriter writer = null;

        ProvinceSynchroResult result = new ProvinceSynchroResult();
        response.setContentType("application/json;charset=UTF-8");
        if (ex instanceof FTPExcpetion) {
            //ftp文件目录
            StringBuilder dir = new StringBuilder();
            dir.append(Constants.FtpPath).append(((FTPExcpetion) ex).getProvinceCode()).append("/");
            result.setCode(((FTPExcpetion) ex).getCode());
            result.setMsg(((FTPExcpetion) ex).getMsg());
            //ftp文件回传的文件名
            StringBuilder fileName = new StringBuilder();
            fileName.append(DateTime.Now().toTimeDayString()).append(FtpCompleteFileSuffix);
            //将结果写入临时 文件
            String resultXml = XMLUtil.convertToXml(result);
            String resultPath = Constants.FtpTempDir + "/" + fileName.toString();
            File resultXmlFile = new File(resultPath);
            //将结果上传到TFS 上
            if (resultXmlFile.exists()) {
                resultXmlFile.delete();
            }
            boolean isFlag = SftpUtils.writStringToFile(Constants.FtpTempDir, fileName.toString(), resultXml);
            if (isFlag) {
                //将结果上传到sftp 上

                SftpUtils.upload(dir.toString(), fileName.toString(), resultXmlFile);

            }
            String r = JSON.toJSONString(result);

            try {
                writer = response.getWriter();
                writer.println(r);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new FTPExcpetion(500, "系统IO 异常");

            }

        } else if (ex instanceof CommonsExcpetion) {
            try {
                writer = response.getWriter();
                response.setContentType("application/json;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                result.setCode(((CommonsExcpetion) ex).getCode().toString());
                result.setMsg(((CommonsExcpetion) ex).getMsg());
                String r = JSON.toJSONString(result);
                writer.println(r);
                writer.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new CommonsExcpetion(500, "系统IO 异常");
            }
        }
        return null;
    }

}
