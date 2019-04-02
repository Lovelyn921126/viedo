/*
 * FileName: provinceSynchroDemandService.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultrapower.viedo.bean.ProvinceSynChro;
import com.ultrapower.viedo.bean.ProvinceSynchroDemand;
import com.ultrapower.viedo.bean.ProvinceSynchroHandle;
import com.ultrapower.viedo.bean.ProvinceSynchroResult;
import com.ultrapower.viedo.constants.Constants;
import com.ultrapower.viedo.dao.one.ProvinceSynchroDemandDao;
import com.ultrapower.viedo.dao.one.ProvinceSynchroHandleDao;
import com.ultrapower.viedo.dao.one.ProvinceZBMappingDao;
import com.ultrapower.viedo.utils.DateTime;
import com.ultrapower.viedo.utils.ProvinceSynchroResultEnum;
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
 * 2018年6月26日 下午1:56:23          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Service
public class ProvinceSynchroDemandService {

    @Autowired
    private ProvinceSynchroDemandDao provinceSynchroDemandDao;
    @Autowired
    private ProvinceSynchroHandleDao provinceSynchroHandleDao;
    @Autowired
    private ProvinceZBMappingDao provinceZBMappingDao;
    //省分同步全量数据FTP 文件的后缀
    private static String FtpFileSuffix = Constants.FtpFileSuffix;

    private final static Log logger = LogFactory.getLog(ProvinceSynchroDemandService.class);
    //省分同步全量数据FTP 文件读取完成之后 返回的文件后缀
    //private final String FtpCompleteFileSuffix = Constants.FtpCompleteFileSuffix;

    public Integer updateProvinceSynchroHandel() {
        provinceSynchroHandleDao.updateProvinceSynchroHandel();
        throw new RuntimeException("发生了异常");
    }

    /**
    * 读取 各个省 上传的工单信息
    * @param provinceCode
     * @throws ParseException
    */
    public ProvinceSynchroResult getProvinceSynchroDemand(String provinceCode, String createDate) throws ParseException {
        //try {

        //获取 当前时间 格式 为  yyyyMMdd
        if (StringUtils.isBlank(createDate)) {
            createDate = DateTime.Now().toTimeDayString();
        }
        logger.info("start read--" + provinceCode + ", datetime：" + createDate + "==");
        //获取 当前时间 格式 为  yyyy-MM-dd
        DateTime create = DateTime.parse(createDate);

        //用于返回 描述的 拼接
        StringBuilder descri = new StringBuilder();
        //返回的数据类型
        ProvinceSynchroResult result = new ProvinceSynchroResult();
        result.setCode(ProvinceSynchroResultEnum.readNormal.getCode());
        result.setMsg(ProvinceSynchroResultEnum.readNormal.getMsg());

        //拼接 该省文件在ftp 服务器上的目录     （文件的存储的配置目录+省分编号/）
        StringBuilder dir = new StringBuilder();
        dir.append(Constants.FtpPath).append(provinceCode).append("/");
        logger.info("ftp dir" + dir.toString());
        //获取文件名   省分上传的文件名为  当日时间 （例如 20180406）+文件配置的后缀（_process.xml）
        StringBuilder fileName = new StringBuilder();
        fileName.append(createDate).append(Constants.FtpFileSuffix);
        logger.info("ftp fileName：" + fileName.toString());
        //拼接本地的路径 用于暂时存储 从 sftp 下载下来的文件
        StringBuilder localFile = new StringBuilder();
        // 暂存文件的目录 为   配置的文件临时目录（/home/temp/）+当日时间（例如 20180406）+文件配置的后缀（_process.xml）
        localFile.append(Constants.FtpTempDir).append(createDate).append(Constants.FtpFileSuffix);
        logger.info("localFile:" + localFile.toString());
        //验证省分上传的文件是否存在
        //拼接文件的完整目录 （文件的目录+文件的 的文件名）
        String sftpFileName = dir.toString() + fileName.toString();
        logger.info("completeFile:" + sftpFileName.toString());
        //验证文件是否存在
        boolean isSftpFileExists = SftpUtils.existsFile(sftpFileName);
        //验证本地要下载的文件是否存在
        //该验证的目的 主要是为了防止 省分文件出错之后  需要再次上传 验证 这时该文件可能已经存在了  当然 在由于sftp下载文件是默认采用的 覆盖的模式
        // boolean isSftpTempFileExists = SftpUtils.existsFile(localFile.toString());
        /* if (isSftpTempFileExists) {
        //删除已存在的临时文件
        SftpUtils.remove(localFile.toString());
        // SftpUtils.CreateDirecroty(localFile.toString());
        }*/
        File local = new File(localFile.toString());
        if (local.exists()) {
            local.delete();
        }
        logger.info("validate file is exists===" + sftpFileName.toString() + " status：" + isSftpFileExists);
        //如果文件存在 说明省分上传了 则继续处理 如果没有则抛出异常
        if (isSftpFileExists) {
            //获取该省在ftp上的文件
            boolean isDownload = SftpUtils.download(dir.toString(), fileName.toString(), localFile.toString());
            if (isDownload) {
                logger.info("validate file is exists===" + sftpFileName.toString() + " status：" + isSftpFileExists);
                //获取本地文件  将本地文件解析成为 java 类
                // File tempLoacl = new File(localFile.toString());
                ProvinceSynChro provinceSynChro = XMLUtil.convertXmlFileToObject(ProvinceSynChro.class, localFile.toString());
                //文件内容有误
                if (provinceSynChro == null) {
                    result.setCode(ProvinceSynchroResultEnum.fileError.getCode());
                    result.setMsg(descri.toString() + ProvinceSynchroResultEnum.fileError.getMsg());
                    return result;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.fileMessageError.getCode(), descri.toString() + ProvinceSynchroResultEnum.fileMessageError.getMsg(), provinceCode);
                }
                //验证文件内容 是否正确 即验证文件 的  创建时间 还有 省分ID 是否正确
                if (!create.toDateString().equals(provinceSynChro.getCreateDate()) || !provinceCode.equals(provinceSynChro.getProvinceCode())) {
                    //文件内的创建时间 与省分Code 是否正确 ----不知道是否必要
                    result.setCode(ProvinceSynchroResultEnum.fileMessageError.getCode());
                    result.setMsg(descri.toString() + ProvinceSynchroResultEnum.fileMessageError.getMsg());
                    return result;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.fileMessageError.getCode(), descri.toString() + ProvinceSynchroResultEnum.fileMessageError.getMsg(), provinceCode);
                }
                //检验是不是空文件  即 文件的  CreateProcess 和 EndProcess()  是否有值
                if ((provinceSynChro.getCreateProcess() != null && provinceSynChro.getCreateProcess().size() > 0) || (provinceSynChro.getEndProcess() != null && provinceSynChro.getEndProcess().size() > 0)) {

                    //说明 有创建的单子
                    if (provinceSynChro.getCreateProcess() != null && provinceSynChro.getCreateProcess().size() > 0) {
                        List<ProvinceSynchroDemand> list = provinceSynChro.getCreateProcess();
                        if (list != null && list.size() > 0) {
                            for (ProvinceSynchroDemand provinceSynchroDemand : list) {
                                descri.append(provinceSynchroDemand.getProcessNum()).append(":");
                                //通过注解验证 工单的参数是否正确
                                result = provinceSynchroDemand.validate(provinceCode, provinceSynchroDemand.getProcessNum());
                                if (!result.getCode().equals("000")) {
                                    return result;
                                }
                                //如果升级了 则验证关于升级的信息 因为 默认 关于升级的信息 默认是不传的 但是如果升级了 则必须验证
                                if (provinceSynchroDemand.getIsUpgrade().equals("1")) {
                                    result = provinceSynchroDemand.validateUpgrade(provinceCode, provinceSynchroDemand.getProcessNum());
                                    if (!result.getCode().equals("000")) {
                                        return result;
                                    }
                                }
                                //获取工单的处理记录
                                List<ProvinceSynchroHandle> handles = provinceSynchroDemand.getHandleList();
                                //验证工单的处理记录参数是否正确
                                if (handles != null && handles.size() > 0) {
                                    for (ProvinceSynchroHandle provinceSynchroHandle : handles) {
                                        result = provinceSynchroHandle.validate(provinceCode, provinceSynchroDemand.getProcessNum());
                                        if (!result.getCode().equals("000")) {
                                            return result;
                                        }
                                    }
                                } else {
                                    result.setCode(ProvinceSynchroResultEnum.demandHandleEmpty.getCode());
                                    result.setMsg(descri.toString() + ProvinceSynchroResultEnum.demandHandleEmpty.getMsg());
                                    //如果没有处理记录 则抛出异常  因为 有工单记录 就一定会有处理记录 （至少有一条发单的记录）
                                    return result;
                                    //throw new FTPExcpetion(ProvinceSynchroResultEnum.demandHandleEmpty.getCode(), descri.toString() + ProvinceSynchroResultEnum.demandHandleEmpty.getMsg(), provinceCode);
                                }
                                /*  //添加数据
                                provinceSynchroDemandDao.addProvinceSynchroDemand(provinceSynchroDemand);
                                //删除 该工单之前的处理记录
                                provinceSynchroHandleDao.deleteProvinceSynchroHandelByProcessNum(provinceSynchroDemand.getProcessNum());
                                //重新添加工单 处理记录
                                for (ProvinceSynchroHandle provinceSynchroHandle : handles) {
                                provinceSynchroHandle.setProcessNum(provinceSynchroDemand.getProcessNum());
                                provinceSynchroHandleDao.addProvinceSynchroHandel(provinceSynchroHandle);
                                }
                                */
                            }
                        }
                    } /*else {
                      // 上传 结果没有记录
                      result.setCode(ProvinceSynchroResultEnum.notMessage.getCode());
                      result.setMsg(descri.toString() + ProvinceSynchroResultEnum.notMessage.getMsg());
                      return result;
                      // throw new FTPExcpetion(ProvinceSynchroResultEnum.notMessage.getCode(), descri.toString() + ProvinceSynchroResultEnum.notMessage.getMsg(), provinceCode);

                      }*/

                    //说明 有关闭的单子
                    if (provinceSynChro.getEndProcess() != null && provinceSynChro.getEndProcess().size() > 0) {
                        List<ProvinceSynchroDemand> list = provinceSynChro.getEndProcess();
                        if (list != null && list.size() > 0) {
                            for (ProvinceSynchroDemand provinceSynchroDemand : list) {
                                descri.append(provinceSynchroDemand.getProcessNum()).append(":");
                                //通过注解验证 工单的参数是否正确
                                result = provinceSynchroDemand.validate(provinceCode, provinceSynchroDemand.getProcessNum());
                                if (!result.getCode().equals("000")) {
                                    return result;
                                }
                                //如果升级了 则验证关于升级的信息 因为 默认 关于升级的信息 默认是不传的 但是如果升级了 则必须验证
                                if (provinceSynchroDemand.getIsUpgrade().equals("1")) {
                                    result = provinceSynchroDemand.validateUpgrade(provinceCode, provinceSynchroDemand.getProcessNum());
                                    if (!result.getCode().equals("000")) {
                                        return result;
                                    }
                                }
                                //获取工单的处理记录
                                List<ProvinceSynchroHandle> handles = provinceSynchroDemand.getHandleList();
                                //验证工单的处理记录参数是否正确
                                if (handles != null && handles.size() > 0) {
                                    for (ProvinceSynchroHandle provinceSynchroHandle : handles) {
                                        result = provinceSynchroHandle.validate(provinceCode, provinceSynchroDemand.getProcessNum());
                                        if (!result.getCode().equals("000")) {
                                            return result;
                                        }
                                    }
                                } else {
                                    result.setCode(ProvinceSynchroResultEnum.demandHandleEmpty.getCode());
                                    result.setMsg(descri.toString() + ProvinceSynchroResultEnum.demandHandleEmpty.getMsg());
                                    //如果没有处理记录 则抛出异常  因为 有工单记录 就一定会有处理记录 （至少有一条发单的记录）
                                    return result;
                                    //throw new FTPExcpetion(ProvinceSynchroResultEnum.demandHandleEmpty.getCode(), descri.toString() + ProvinceSynchroResultEnum.demandHandleEmpty.getMsg(), provinceCode);
                                }
                                /* //添加数据
                                provinceSynchroDemandDao.addProvinceSynchroDemand(provinceSynchroDemand);
                                //删除 该工单之前的处理记录
                                provinceSynchroHandleDao.deleteProvinceSynchroHandelByProcessNum(provinceSynchroDemand.getProcessNum());
                                //重新添加工单 处理记录
                                for (ProvinceSynchroHandle provinceSynchroHandle : handles) {
                                provinceSynchroHandle.setProcessNum(provinceSynchroDemand.getProcessNum());
                                provinceSynchroHandleDao.addProvinceSynchroHandel(provinceSynchroHandle);
                                }*/

                            }
                        }
                    } /*else {
                      // 上传 结果没有记录
                      result.setCode(ProvinceSynchroResultEnum.notMessage.getCode());
                      result.setMsg(descri.toString() + ProvinceSynchroResultEnum.notMessage.getMsg());
                      return result;
                      // throw new FTPExcpetion(ProvinceSynchroResultEnum.notMessage.getCode(), descri.toString() + ProvinceSynchroResultEnum.notMessage.getMsg(), provinceCode);

                      }*/
                    //添加 当天的创建工单
                    List<ProvinceSynchroDemand> CreateProcess = provinceSynChro.getCreateProcess();
                    for (ProvinceSynchroDemand provinceSynchroDemand : CreateProcess) {
                        //添加数据
                        provinceSynchroDemandDao.addProvinceSynchroDemand(provinceSynchroDemand);
                        //删除 该工单之前的处理记录
                        provinceSynchroHandleDao.deleteProvinceSynchroHandelByProcessNum(provinceSynchroDemand.getProcessNum());
                        //重新添加工单 处理记录
                        List<ProvinceSynchroHandle> handles = provinceSynchroDemand.getHandleList();
                        for (ProvinceSynchroHandle provinceSynchroHandle : handles) {
                            provinceSynchroHandle.setProcessNum(provinceSynchroDemand.getProcessNum());
                            provinceSynchroHandleDao.addProvinceSynchroHandel(provinceSynchroHandle);
                        }

                    }
                    //添加 当天的关闭工单
                    List<ProvinceSynchroDemand> getEndProcess = provinceSynChro.getEndProcess();
                    for (ProvinceSynchroDemand provinceSynchroDemand : getEndProcess) {
                        //添加数据
                        provinceSynchroDemandDao.addProvinceSynchroDemand(provinceSynchroDemand);
                        //删除 该工单之前的处理记录
                        provinceSynchroHandleDao.deleteProvinceSynchroHandelByProcessNum(provinceSynchroDemand.getProcessNum());
                        //重新添加工单 处理记录
                        List<ProvinceSynchroHandle> handles = provinceSynchroDemand.getHandleList();
                        for (ProvinceSynchroHandle provinceSynchroHandle : handles) {
                            provinceSynchroHandle.setProcessNum(provinceSynchroDemand.getProcessNum());
                            provinceSynchroHandleDao.addProvinceSynchroHandel(provinceSynchroHandle);
                        }

                    }
                    return result;
                } else {
                    //没有下载下来
                    result.setCode(ProvinceSynchroResultEnum.notMessage.getCode());
                    result.setMsg(descri.toString() + ProvinceSynchroResultEnum.notMessage.getMsg());
                    return result;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.fileError.getCode(), descri.toString() + ProvinceSynchroResultEnum.fileError.getMsg(), provinceCode);
                }
            }
        } else {
            //文件不存
            result.setCode(ProvinceSynchroResultEnum.fileNotExist.getCode());
            result.setMsg(descri.toString() + ProvinceSynchroResultEnum.fileNotExist.getMsg());
            // throw new FTPExcpetion(ProvinceSynchroResultEnum.fileNotExist.getCode(), descri.toString() + ProvinceSynchroResultEnum.fileNotExist.getMsg(), provinceCode);
            return result;
        }
        logger.info(provinceCode + "  file:" + fileName.toString() + " validate SUCCESS");

        // } catch (Exception e) {
        // TODO: handle exception
        // } /*finally {

        // }*/
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        // DateTime create = DateTime.parse("2018_07_07", "1970-01-01");
        DateTime create = DateTime.parse("2018_07_07");
        System.out.println(create.toDateString());
        /*
        String createDate = DateTime.Now().toTimeDayString();
        ProvinceSynchroResult result = new ProvinceSynchroResult();
        FtpBean ftpBean = new FtpBean();
        ftpBean.setIpAddr("192.168.180.93");
        ftpBean.setPort(22);
        ftpBean.setUserName("root");
        ftpBean.setPwd("RedHatyhjc");
        ftpBean.setPath("/home/ithelp/");
        SftpUtils.connect(ftpBean);
        File file = new File("E://ftp.xml");
        
        String provinceCode = "HQ";
        //拼接 该省文件在ftp 服务器上的目录
        StringBuilder dir = new StringBuilder();
        dir.append("/home/ithelp/").append(provinceCode).append("/");
        
        //获取文件名
        StringBuilder fileName = new StringBuilder();
        fileName.append(DateTime.Now().toTimeDayString()).append("_process.xml");
        // FileOutputStream outputStream = new FileOutputStream(dir.toString() + "/" + fileName.toString());
        boolean isDownload = SftpUtils.download(dir.toString(), fileName.toString(), file);
        ProvinceSynChro provinceSynChro = (ProvinceSynChro) XMLUtil.convertXmlFileToObject(ProvinceSynChro.class, file.getAbsolutePath());
        // System.out.println(provinceSynChro.toString());
        if (isDownload) {
            //获取本地文件
            // File tempLoacl = new File(localFile.toString());
        
            //检验是不是空文件
            if (provinceSynChro.getCreateProcess() != null && provinceSynChro.getEndProcess() != null) {
                       if (createDate != provinceSynChro.getCreateDate() || provinceCode != provinceSynChro.getProvinceCode()) {
                    //文件内的创建时间 与省分Code 是否正确 ----不知道是否必要
                    result.setCode(ProvinceSynchroResultEnum.fileMessageError.getCode());
                    result.setMsg(ProvinceSynchroResultEnum.fileMessageError.getMsg());
                    throw new FTPExcpetion(ProvinceSynchroResultEnum.fileMessageError.getCode(), ProvinceSynchroResultEnum.fileMessageError.getMsg(), provinceCode);
                } else {
        
                //说明 有创建的单子
                if (provinceSynChro.getCreateProcess() != null) {
                    List<ProvinceSynchroDemand> list = provinceSynChro.getCreateProcess();
                    if (list != null && list.size() > 0) {
                        for (ProvinceSynchroDemand provinceSynchroDemand : list) {
                            provinceSynchroDemand.validate(provinceSynchroDemand.getProvinceCode(), provinceSynchroDemand.getProcessNum());
                            List<ProvinceSynchroHandle> handles = provinceSynchroDemand.getHandleList();
                            if (handles != null && handles.size() > 0) {
                                for (ProvinceSynchroHandle provinceSynchroHandle : handles) {
                                    provinceSynchroHandle.validate(provinceSynchroDemand.getProvinceCode(), provinceSynchroDemand.getProcessNum());
                                }
                            } else {
                                throw new FTPExcpetion(ProvinceSynchroResultEnum.demandHandleEmpty.getCode(), ProvinceSynchroResultEnum.demandHandleEmpty.getMsg(), provinceCode);
                            }
                        }
                    }
                }
        
                //说明 有关闭的单子
                if (provinceSynChro.getEndProcess() != null) {
                    List<ProvinceSynchroDemand> list = provinceSynChro.getEndProcess();
                    if (list != null && list.size() > 0) {
                        for (ProvinceSynchroDemand provinceSynchroDemand : list) {
                            provinceSynchroDemand.validate(provinceSynchroDemand.getProvinceCode(), provinceSynchroDemand.getProcessNum());
                            List<ProvinceSynchroHandle> handles = provinceSynchroDemand.getHandleList();
                            if (handles != null && handles.size() > 0) {
                                for (ProvinceSynchroHandle provinceSynchroHandle : handles) {
                                    provinceSynchroHandle.validate(provinceSynchroDemand.getProvinceCode(), provinceSynchroDemand.getProcessNum());
                                }
                            } else {
                                throw new FTPExcpetion(ProvinceSynchroResultEnum.Other.getCode(), ProvinceSynchroResultEnum.Other.getMsg(), provinceCode);
                            }
                        }
                    }
                }
        
            }
        
        } else {
            // 上传 结果没有记录
            result.setCode(ProvinceSynchroResultEnum.notMessage.getCode());
            result.setMsg(ProvinceSynchroResultEnum.notMessage.getMsg());
            throw new FTPExcpetion(ProvinceSynchroResultEnum.notMessage.getCode(), ProvinceSynchroResultEnum.notMessage.getMsg(), provinceCode);
        
        }*/
        /* ProvinceSynchroResult result = new ProvinceSynchroResult();
        result.setCode(200);
        result.setMsg("SUCCESS");
        Class c = result.getClass();
        Field[] f = c.getDeclaredFields();
        for (Field field : f) {
        field.setAccessible(true);
        //System.out.println(field.getInt(field));
        }*/

        /*String createDate = DateTime.Now().toTimeDayString();
        System.out.println(createDate);*/
        /* Method[] methods = c.getMethods();
        for (Method method : methods) {
        if (method.getName().startsWith("get")) {
            System.out.println(method.getName());
            System.out.println();
        }
        }*/
        // String a = XMLUtil.convertToXml(result);
        // writStringToFile("E://app", "ftp.xml", a);

    }
    /*}*/

}
