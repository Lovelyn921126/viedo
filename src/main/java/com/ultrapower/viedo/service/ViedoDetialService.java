/*
 * FileName: BrowseRecordDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.ultrapower.viedo.bean.SearchContent;
import com.ultrapower.viedo.bean.UserInfo;
import com.ultrapower.viedo.bean.ViedoClassify;
import com.ultrapower.viedo.bean.ViedoDetial;
import com.ultrapower.viedo.bean.ViedoRecord;
import com.ultrapower.viedo.constants.Constants;
import com.ultrapower.viedo.constants.ViedoSystemResult;
import com.ultrapower.viedo.dao.one.UserInfoDaoMapper;
import com.ultrapower.viedo.dao.one.ViedoClassifyDaoMapper;
import com.ultrapower.viedo.dao.one.ViedoDetialDaoMapper;
import com.ultrapower.viedo.utils.FileOperationUtils;
import com.ultrapower.viedo.utils.HttpUploadUtil;
import com.ultrapower.viedo.vo.RepData;
import com.ultrapower.viedo.vo.UserParentOrganization;
import com.ultrapower.viedo.vo.ViedoDetialVo;

/**
 * <p>
 * Description:  视频详情
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2017年11月29日 下午2:53:20          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Service
public class ViedoDetialService {
    @Autowired
    private ViedoDetialDaoMapper viedoDetialDao;

    @Autowired
    private ViedoClassifyDaoMapper viedoClassifyDao;
    @Autowired
    private UserInfoDaoMapper userInfoCacheService;
    /*  private final int VIEDO = 0;
    
    private final int IMG = 1;*/
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final int TEMP = 2;

    private final int VERSION = 1;
    //1 图片
    private final int IMG = 1;
    //2 大图
    private final int MAXIMG = 2;
    //3小图
    private final int SMALLIMG = 3;

    //4 二维码
    private final int CODE = 4;
    //5 视频
    private final int VIEDO = 5;

    // private final int VERSION = 1;
    //private final int VERSION = 1;

    /**
     * 查询视频详情
     * @param map id 视频ID orderByUploadTime 详情页排序按上传时间
     * @return
     */
    public List<ViedoDetialVo> getViedoDetail(Map<String, Object> map, PageBounds pageBounds) {
        return viedoDetialDao.getViedoDetail(map, pageBounds);
    }//getViedoDetailById

    /**
     * 查询视频详情
     * @param map id 视频ID orderByUploadTime 详情页排序按上传时间
     * @return
     */
    public ViedoDetialVo getViedoDetailById(Map<String, Object> map) {
        return viedoDetialDao.getViedoDetailById(map);
    }

    /**
     * 更新视频详情
     * @param file
     * @param viedoDetial
     * @param imgName
     * @param viedoName
     * @param type
     * @return
     */

    public RepData<Integer> updateViedoDetail(String imgURL, String viedoURL, ViedoDetial viedoDetial, String viedoName, String imgName) {
        RepData<Integer> repData = new RepData<Integer>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(0);
        repData.setCount(0);
        //标识是否改变
        boolean isChange = false;
        //封面 还有视频 的文件
        File imgFile = null;
        File viedoFile = null;
        Map<String, Object> map = new HashMap<String, Object>();
        //id 视频ID
        map.put("id", viedoDetial.getId());
        //视频详情
        ViedoDetial vo = viedoDetialDao.getViedoDetailById(map);
        if (vo == null) {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg(ViedoSystemResult.Empty.getMsg());
            return repData;
        }

        if (StringUtils.isNotBlank(viedoDetial.getUserName())) {
            // 判断用户是否存在
            UserInfo u = userInfoCacheService.getUserByUserName(viedoDetial.getUserName());
            if (u == null) {
                repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
                repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
                return repData;
            }
            // 判断 当前用户是否存在 并且是否是 视频的上传人
            if (!vo.getUserName().equals(viedoDetial.getUserName())) {
                repData.setCode(ViedoSystemResult.UserInfoERROR.getCode());
                repData.setMsg(ViedoSystemResult.UserInfoERROR.getMsg());
                return repData;
            }
        } else {
            repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
            repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
            return repData;
        }

        // 根据传参 修改视频详情
        //更改视频的名称
        if (StringUtils.isNotBlank(viedoDetial.getViedoTitle()) && !viedoDetial.getViedoTitle().equals(vo.getViedoTitle())) {
            isChange = true;
            vo.setViedoTitle(viedoDetial.getViedoTitle());
        }
        //更改视频描述
        if (StringUtils.isNotBlank(viedoDetial.getViedoDescription()) && !viedoDetial.getViedoDescription().equals(vo.getViedoDescription())) {
            isChange = true;
            vo.setViedoDescription(viedoDetial.getViedoDescription());
        }
        //更改视频的分类
        if (viedoDetial.getViedoClassifyId() != vo.getViedoClassifyId()) {
            isChange = true;
            vo.setViedoClassifyId(viedoDetial.getViedoClassifyId());
            map.put("id", viedoDetial.getViedoClassifyId());
            ViedoClassify list = viedoClassifyDao.getViedoClassifyById(map);
            if (list != null) {
                vo.setViedoClassifyName(list.getClassifyName());
            } else {
                repData.setCode(ViedoSystemResult.Empty.getCode());
                repData.setMsg(ViedoSystemResult.Empty.getMsg());
                return repData;
            }

        }
        // 更改视频 或者封面
        //用户重新上传了视频或者封面
        if (StringUtils.isNotBlank(imgURL) || StringUtils.isNotBlank(viedoURL)) {
            //用户上传了封面
            if (StringUtils.isNotBlank(imgURL)) {
                imgFile = new File(imgURL);
                if (imgFile.isFile() && imgFile.exists()) {
                    isChange = true;
                    boolean isFormat = false;
                    //获取封面的后缀
                    String fileName = imgFile.getName();
                    String format = fileName.substring(fileName.lastIndexOf("."));
                    //通过文件后缀 判断是不是封面
                    String[] formats = Constants.UPLOAD_IMG_FORMAT.split(",");
                    for (String string : formats) {
                        if (format.equalsIgnoreCase(string)) {
                            isFormat = true;
                        }
                    }
                    // String format = imgURL.getOriginalFilename().substring(imgURL.getOriginalFilename().indexOf("."));
                    //通过文件后缀 判断是不是封面
                    if (isFormat) {
                        //设置图片的大小
                        vo.setImgSize(String.valueOf(imgFile.length() / 1024));
                        //设置图片的
                        vo.setImgName(imgName);
                        //设置封面的类型
                        vo.setImgFormat(format);
                        //设置封面的正式路径
                        // vo.setImgUrl(imgPath);
                        //保存封面

                    } else {
                        repData.setCode(ViedoSystemResult.FormatError.getCode());
                        repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                        return repData;
                    }
                } else {
                    repData.setCode(ViedoSystemResult.Empty.getCode());
                    repData.setMsg(ViedoSystemResult.Empty.getMsg());
                    return repData;
                }
            }
            //视频的逻辑
            // 如果用户上传了 视频 因为在上传临时文件是已经 验证了MD5 所有这块不用在验证了
            if (StringUtils.isNotBlank(viedoURL)) {
                viedoFile = new File(viedoURL);
                if (viedoFile.exists() && viedoFile.isFile()) {
                    isChange = true;
                    //视频格式正确与否的标识
                    boolean isFormat = false;
                    String fileName = viedoFile.getName();
                    String format = fileName.substring(fileName.lastIndexOf("."));
                    //通过文件后缀 判断是不是封面

                    String[] formats = Constants.UPLOAD_VIEDO_FORMAT.split(",");
                    for (String string : formats) {
                        if (format.equalsIgnoreCase(string)) {
                            isFormat = true;
                        }
                    }
                    //如果视频格式正确
                    if (isFormat) {
                        //设置视频格式
                        vo.setViedoFormat(format);
                        //设置视频的长度
                        vo.setViedoLength(FileOperationUtils.getViedoLength(viedoFile));
                        //设置视频的大小
                        vo.setViedoSize(String.valueOf(viedoFile.length() / 1024));
                        //设置视频的类型  (0视频课程，1H5课程)
                        vo.setViedoType(0);
                        //设置MD5
                        vo.setViedoMd5(FileOperationUtils.getFileMd5(viedoFile));
                        vo.setViedoName(viedoName);

                    } else {
                        repData.setCode(ViedoSystemResult.FormatError.getCode());
                        repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                        return repData;
                    }
                } else {
                    repData.setCode(ViedoSystemResult.Empty.getCode());
                    repData.setMsg(ViedoSystemResult.Empty.getMsg());
                    return repData;
                }
            }
        }
        //如果都没有错然后保存文件
        //保存封面 还有视频
        // 保存封面
        try {
            if (StringUtils.isNotBlank(imgURL)) {

                //设置 封面 即高级搜索的图片路径
                vo.setImgUrl(getFileUploadPath(imgURL, IMG));
                //设置 封面 即高级搜索的图片路径
                //设置 大图 即 首页
                FileOperationUtils.resize(imgFile, imgFile, MAXIMG);
                vo.setMaxImgUrl(getFileUploadPath(imgFile.getAbsolutePath(), MAXIMG));
                //设置 封面 即相似推荐的图片路径
                FileOperationUtils.resize(imgFile, imgFile, SMALLIMG);
                vo.setSmallImgUrl(getFileUploadPath(imgFile.getAbsolutePath(), SMALLIMG));
                imgFile.delete();

            }
            if (StringUtils.isNotBlank(viedoURL)) {
                vo.setViedoUrl(getFileUploadPath(viedoFile.getAbsolutePath(), VIEDO));
                viedoFile.delete();

            }

        } catch (Exception e) {
            e.printStackTrace();
            repData.setCode(ViedoSystemResult.SaveError.getCode());
            repData.setMsg(ViedoSystemResult.SaveError.getMsg());
            return repData;
        }

        if (isChange) {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            //设置更新时间
            vo.setUpdateTime(format.format(ts));
            vo.setVaildStatus(1);
            int result = viedoDetialDao.updateViedoDetail(vo);
            if (result > 0) {
                addViedoDetialToSolr(vo);
                ViedoRecord record = new ViedoRecord();
                try {
                    BeanUtils.copyProperties(record, vo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                record.setViedoId(vo.getId());
                viedoDetialDao.addViedoDetailUploadRecord(record);
            }
            repData.setData(result);
            repData.setCount(1);
        } else {
            repData.setCode(ViedoSystemResult.NotChange.getCode());
            repData.setMsg(ViedoSystemResult.NotChange.getMsg());
            return repData;
        }

        return repData;
    }

    /**
     * 获取视频总数
     * @param map
     * @param viedoDetial
     * @return
     */
    public Integer getCountViedoDetail(Map<String, Object> map) {
        return viedoDetialDao.getCountViedoDetail(map);
    }

    /**
     * 上传视频
     * @param imgURL 封面的临时文件路径 存储在服务器本地
     * @param viedoURL 视频的临时文件路径 存储在服务器本地
     * @param vo  视频详情的信息
     * @param OriginfileName
     * @param imgName
     * @return
     */

    public RepData<Integer> uploadViedo(String imgURL, String viedoURL, ViedoDetial vo, String viedoName, String imgName) {
        RepData<Integer> repData = new RepData<Integer>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(0);
        repData.setCount(0);
        // 封面 视频 文件
        File imgfile = new File(imgURL);
        File viedoFile = new File(viedoURL);

        //设置用户
        UserInfo u = userInfoCacheService.getUserByUserName(vo.getUserName());
        if (u == null) {
            repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
            repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
            return repData;
        }

        //设置视频上传用户的地域属性
        //设置用户的城市ID
        UserParentOrganization userParentOrganization = userInfoCacheService.getParentOrganization(u.getId());
        if (userParentOrganization != null) {
            vo.setUserCityId(userParentOrganization.getUserCityId());
            //设置用户城市名称
            vo.setUserCityName(userParentOrganization.getUserCityName());
            //设置用户的省ID
            vo.setUserProvinceId(userParentOrganization.getUserProvinceId());
            //设置用户的省名称
            vo.setUserProvinceName(userParentOrganization.getUserProvinceName());
        } else {
            repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
            repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
            return repData;
        }

        //封面的逻辑
        //临时路径不为空 且 路径下的临时文件存在
        System.out.println("1=================" + StringUtils.isNotBlank(imgURL));
        System.out.println(imgfile.getPath());
        System.out.println("2=================" + imgfile.exists());
        System.out.println("3=================" + imgfile.isFile());
        if (StringUtils.isNotBlank(imgURL) && imgfile.exists() && imgfile.isFile()) {
            //标识 文件格式
            boolean isFormat = false;
            //获取封面的后缀
            String fileName = imgfile.getName();
            String format = fileName.substring(fileName.lastIndexOf("."));
            //通过文件后缀 判断是不是封面
            String[] formats = Constants.UPLOAD_IMG_FORMAT.split(",");
            for (String string : formats) {
                if (format.equalsIgnoreCase(string)) {
                    isFormat = true;
                }
            }
            //标识是封面格式是否正确
            if (isFormat) {
                //设置图片的大小
                vo.setImgSize(String.valueOf(imgfile.length() / 1024));
                //设置图片的名称
                vo.setImgName(imgName);
                //设置图片的格式
                vo.setImgFormat(format);
                //设置图片的路径
            } else {
                repData.setCode(ViedoSystemResult.FormatError.getCode());
                repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                return repData;
            }

        } else {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg("tupian" + ViedoSystemResult.Empty.getMsg());
            return repData;
        }
        //视频 逻辑
        //临时路径不为空 且 路径下的临时文件存在
        if (viedoFile.exists() && StringUtils.isNotBlank(viedoURL) && viedoFile.isFile()) {
            //标识 文件格式
            boolean isFormat = false;
            //获取视频的后缀
            String fileName = viedoFile.getName();
            String format = fileName.substring(fileName.lastIndexOf("."));
            String[] formats = Constants.UPLOAD_VIEDO_FORMAT.split(",");
            for (String string : formats) {
                if (format.equalsIgnoreCase(string)) {
                    isFormat = true;
                }
            }
            //通过文件后缀 判断是不是视频
            if (isFormat) {
                //标识是否传了视频
                // isViedo = true;
                //设置视频分类
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", vo.getViedoClassifyId());
                ViedoClassify list = viedoClassifyDao.getViedoClassifyById(map);
                if (list != null) {
                    vo.setViedoClassifyName(list.getClassifyName());
                } else {
                    repData.setCode(ViedoSystemResult.Empty.getCode());
                    repData.setMsg("ViedoClassifyId" + ViedoSystemResult.Empty.getMsg());
                    return repData;
                }
                //**
                vo.setViedoName(viedoName);
                //设置视频格式
                vo.setViedoFormat(format);
                //设置视频的长度
                vo.setViedoLength(FileOperationUtils.getViedoLength(viedoFile));
                //设置视频的大小 单位KB
                vo.setViedoSize(String.valueOf(viedoFile.length() / 1024));
                //设置视频的类型  (0视频课程，1H5课程)
                vo.setViedoType(0);
                //设置视频的MD5
                vo.setViedoMd5(FileOperationUtils.getFileMd5(viedoFile));

            } else {
                //格式错误
                repData.setCode(ViedoSystemResult.FormatError.getCode());
                repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                return repData;
            }

        } else {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg("视频文件" + ViedoSystemResult.Empty.getMsg());
            return repData;
        }

        //只有明确传了 正确的封面还有视频的时候 才会保存文件
        //设置默认的浏览次数
        vo.setBrowseCount(0);
        //设置默认的视频状态（1 正常 0无效）
        vo.setVaildStatus(1);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        //创建时间
        vo.setCreateTime(format.format(ts));
        //更新时间
        vo.setUpdateTime(format.format(ts));
        //默认点赞次数
        vo.setPraiseCount(0);
        try {
            //设置 封面 即高级搜索的图片路径
            vo.setImgUrl(getFileUploadPath(imgURL, IMG));
            //设置 封面 即高级搜索的图片路径
            //设置 大图 即 首页
            FileOperationUtils.resize(imgfile, imgfile, MAXIMG);
            vo.setMaxImgUrl(getFileUploadPath(imgfile.getAbsolutePath(), MAXIMG));
            //设置 封面 即相似推荐的图片路径
            FileOperationUtils.resize(imgfile, imgfile, SMALLIMG);
            vo.setSmallImgUrl(getFileUploadPath(imgfile.getAbsolutePath(), SMALLIMG));
            //设置视频路径
            vo.setViedoUrl(getFileUploadPath(viedoURL, VIEDO));
            imgfile.delete();
            viedoFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
            repData.setCode(ViedoSystemResult.SaveError.getCode());
            repData.setMsg(ViedoSystemResult.SaveError.getMsg());
            return repData;
        }
        ViedoDetial result = viedoDetialDao.addViedoDetail(vo);
        //添加视频详情到 solr

        if (result.getId() > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", result);
            ViedoDetialVo resultVO = viedoDetialDao.getViedoDetailById(map);
            addViedoDetialToSolr(resultVO);
            ViedoRecord record = new ViedoRecord();
            try {
                BeanUtils.copyProperties(record, vo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            record.setViedoId(vo.getId());
            viedoDetialDao.addViedoDetailUploadRecord(record);
        }
        repData.setData(result.getId());
        repData.setCount(1);

        return repData;
    }

    /**上传H5视频
     * @param imgURL
     * @param codeURL
     * @param url
     * @param vo
     * @param imgName
     * @param codeName
     * @return
     */

    public RepData<Integer> uploadH5(String imgURL, String codeURL, String url, ViedoDetial vo, String codeName, String imgName) {
        RepData<Integer> repData = new RepData<Integer>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(0);
        repData.setCount(0);
        // 封面 视频 文件
        File imgfile = new File(imgURL);
        File codeFile = new File(codeURL);

        //设置用户
        UserInfo u = userInfoCacheService.getUserByUserName(vo.getUserName());
        if (u == null) {
            repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
            repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
            return repData;
        }
        //设置视频上传用户的地域属性
        //设置用户的城市ID
        UserParentOrganization userParentOrganization = userInfoCacheService.getParentOrganization(u.getId());
        if (userParentOrganization != null) {
            vo.setUserCityId(userParentOrganization.getUserCityId());
            //设置用户城市名称
            vo.setUserCityName(userParentOrganization.getUserCityName());
            //设置用户的省ID
            vo.setUserProvinceId(userParentOrganization.getUserProvinceId());
            //设置用户的省名称
            vo.setUserProvinceName(userParentOrganization.getUserProvinceName());
        } else {
            repData.setCode(ViedoSystemResult.UserInfoERROR.getCode());
            repData.setMsg(ViedoSystemResult.UserInfoERROR.getMsg());
            return repData;
        }

        //封面的逻辑
        //临时路径不为空 且 路径下的临时文件存在
        if (StringUtils.isNotBlank(imgURL) && imgfile.isFile() && imgfile.exists()) {
            boolean isFormat = false;
            String fileName = imgfile.getName();
            String format = fileName.substring(fileName.lastIndexOf("."));
            //通过文件后缀 判断是不是封面

            String[] formats = Constants.UPLOAD_IMG_FORMAT.split(",");
            for (String string : formats) {
                if (format.equalsIgnoreCase(string)) {
                    isFormat = true;
                }
            }

            //String format = imgURL.getOriginalFilename().substring(imgURL.getOriginalFilename().indexOf("."));
            //通过文件后缀 判断是不是封面
            if (isFormat) {
                //设置图片的大小
                vo.setImgSize(String.valueOf(imgfile.length() / 1024));
                //设置图片的
                vo.setImgName(imgName);
                vo.setImgFormat(format);
            } else {
                repData.setCode(ViedoSystemResult.FormatError.getCode());
                repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                return repData;
            }

        } else {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg("图片" + ViedoSystemResult.Empty.getMsg());
            return repData;
        }
        //H5 URL 的逻辑
        if (StringUtils.isNotBlank(url)) {
            //设置视频分类
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", vo.getViedoClassifyId());
            ViedoClassify list = viedoClassifyDao.getViedoClassifyById(map);
            if (list != null) {
                vo.setViedoClassifyName(list.getClassifyName());
            } else {
                repData.setCode(ViedoSystemResult.Empty.getCode());
                repData.setMsg("CLASSIFYid" + ViedoSystemResult.Empty.getMsg());
                return repData;
            }

            //设置视频格式
            vo.setViedoFormat(null);

            //设置视频的长度
            vo.setViedoLength("0");
            //设置视频的大小
            vo.setViedoSize("0");
            //设置视频的类型  (0视频课程，1H5课程)
            vo.setViedoType(1);

            vo.setViedoUrl(url);

        } else {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg("url" + ViedoSystemResult.Empty.getMsg());
            return repData;
        }
        // 二维码的逻辑
        //临时路径不为空 且 路径下的临时文件存在
        if (StringUtils.isNotBlank(codeURL) && codeFile.isFile() && codeFile.exists()) {
            boolean isFormat = false;
            String fileName = imgfile.getName();
            String format = fileName.substring(fileName.lastIndexOf("."));
            //通过文件后缀 判断是不是封面

            String[] formats = Constants.UPLOAD_IMG_FORMAT.split(",");
            for (String string : formats) {
                if (format.equalsIgnoreCase(string)) {
                    isFormat = true;
                }
            }

            //通过文件后缀 判断是不是png
            if (isFormat) {
            } else {
                repData.setCode(ViedoSystemResult.FormatError.getCode());
                repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                return repData;
            }
            vo.setCodeName(codeName);
            vo.setCodeFormat(format);
        } else {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg("code" + ViedoSystemResult.Empty.getMsg());
            return repData;
        }
        vo.setBrowseCount(0);
        vo.setVaildStatus(1);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        vo.setCreateTime(format.format(ts));
        vo.setUpdateTime(format.format(ts));
        vo.setPraiseCount(0);
        try {
            //设置 封面 即高级搜索的图片路径
            vo.setImgUrl(getFileUploadPath(imgURL, IMG));
            //设置 封面 即高级搜索的图片路径
            //设置 大图 即 首页
            FileOperationUtils.resize(imgfile, imgfile, MAXIMG);
            vo.setMaxImgUrl(getFileUploadPath(imgfile.getAbsolutePath(), MAXIMG));
            //设置 封面 即相似推荐的图片路径
            FileOperationUtils.resize(imgfile, imgfile, SMALLIMG);
            vo.setSmallImgUrl(getFileUploadPath(imgfile.getAbsolutePath(), SMALLIMG));
            //设置二维码路径
            vo.setCodeUrl(getFileUploadPath(codeURL, CODE));
            imgfile.delete();
            codeFile.delete();

            //根据返回结果 判断是否保存成功

        } catch (Exception e) {
            repData.setCode(ViedoSystemResult.SaveError.getCode());
            repData.setMsg(ViedoSystemResult.SaveError.getMsg());
            return repData;
        }
        ViedoDetial result = viedoDetialDao.addViedoDetail(vo);
        if (result.getId() > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", result);
            ViedoDetialVo resultVO = viedoDetialDao.getViedoDetailById(map);
            addViedoDetialToSolr(resultVO);
            ViedoRecord record = new ViedoRecord();
            try {
                BeanUtils.copyProperties(record, vo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            record.setViedoId(vo.getId());
            viedoDetialDao.addViedoDetailUploadRecord(record);
        }
        repData.setData(result.getId());
        repData.setCount(1);

        return repData;
    }

    /**跟新H5 视频
     * @param imgURL
     * @param codeURL
     * @param url
     * @param imgName
     * @param codeName
     * @param type
     * @return
     */
    public RepData<Integer> updateH5ViedoDetail(String imgURL, String codeURL, String url, ViedoDetial detial, String codeName, String imgName) {
        RepData<Integer> repData = new RepData<Integer>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(0);
        repData.setCount(0);
        //标识 更新是否参数改变
        boolean isChange = false;
        // 封面 二维码 文件
        File imgFile = new File(imgURL);
        File codeFile = new File(codeURL);
        Map<String, Object> map = new HashMap<String, Object>();
        //id 视频ID
        map.put("id", detial.getId());
        //获取视频详情
        ViedoDetial vo = viedoDetialDao.getViedoDetailById(map);
        if (vo == null) {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg(ViedoSystemResult.Empty.getMsg());
            return repData;
        }
        //判断用户
        if (StringUtils.isNotBlank(detial.getUserName())) {
            // 判断用户是否存在
            UserInfo u = userInfoCacheService.getUserByUserName(detial.getUserName());
            if (u == null) {
                repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
                repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
                return repData;
            }
            // 判断 当前用户是否存在 并且是否是 视频的上传人
            if (!vo.getUserName().equals(detial.getUserName())) {
                repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
                repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
                return repData;
            }
        } else {
            repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
            repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
            return repData;
        }
        // 更新视频名称
        if (StringUtils.isNotBlank(detial.getViedoTitle()) && !detial.getViedoTitle().equals(vo.getViedoTitle())) {
            isChange = true;
            vo.setViedoTitle(detial.getViedoTitle());
        }
        // 更新视频描述
        if (StringUtils.isNotBlank(detial.getViedoDescription()) && !detial.getViedoDescription().equals(vo.getViedoDescription())) {
            isChange = true;
            vo.setViedoDescription(detial.getViedoDescription());
        }
        // 更新视频分类
        if (detial.getViedoClassifyId() != vo.getViedoClassifyId()) {
            isChange = true;
            vo.setViedoClassifyId(detial.getViedoClassifyId());
            map.put("id", detial.getViedoClassifyId());
            ViedoClassify list = viedoClassifyDao.getViedoClassifyById(map);
            if (list != null) {
                vo.setViedoClassifyName(list.getClassifyName());
            } else {
                repData.setCode(ViedoSystemResult.Empty.getCode());
                repData.setMsg(ViedoSystemResult.Empty.getMsg());
                return repData;
            }

        }
        //用户重新上传了视频或者封面
        if (StringUtils.isNotBlank(imgURL) || StringUtils.isNotBlank(codeURL)) {
            // imgFile 为 临时 文件 判断 临时文件 是否合法 存在
            if (StringUtils.isNotBlank(imgURL)) {
                if (imgFile.isFile() && imgFile.exists()) {

                    isChange = true;
                    // 文件 是否符合格式 标识符
                    boolean isFormat = false;
                    //获取文件后缀
                    String fileName = imgFile.getName();
                    String format = fileName.substring(fileName.lastIndexOf("."));
                    //通过文件后缀 判断是不是封面
                    String[] formats = Constants.UPLOAD_IMG_FORMAT.split(",");
                    for (String string : formats) {
                        if (format.equalsIgnoreCase(string)) {
                            isFormat = true;
                        }
                    }
                    // String format = imgURL.getOriginalFilename().substring(imgURL.getOriginalFilename().indexOf("."));
                    //通过文件后缀 判断是不是封面
                    if (isFormat) {

                        //设置图片的大小
                        vo.setImgSize(String.valueOf(imgFile.length() / 1024));
                        //设置图片的
                        vo.setImgName(imgName);
                        // 设置封面格式
                        vo.setImgFormat(format);
                        //保存封面的路径
                        //vo.setImgUrl(imgPath);
                    } else {
                        repData.setCode(ViedoSystemResult.FormatError.getCode());
                        repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                        return repData;
                    }
                } else {
                    repData.setCode(ViedoSystemResult.Empty.getCode());
                    repData.setMsg(ViedoSystemResult.Empty.getMsg());
                    return repData;
                }
            }
            // imgFile 为 临时 文件 判断 临时文件 是否合法 存在
            if (StringUtils.isNotBlank(codeURL)) {
                if (codeFile.isFile() && codeFile.exists()) {

                    isChange = true;
                    // 格式合法的标识符
                    boolean isFormat = false;
                    // 获取文件后缀
                    String fileName = codeFile.getName();
                    String format = fileName.substring(fileName.lastIndexOf("."));
                    //通过文件后缀 判断是不是封面

                    String[] formats = Constants.UPLOAD_IMG_FORMAT.split(",");
                    for (String string : formats) {
                        if (format.equalsIgnoreCase(string)) {
                            isFormat = true;
                        }
                    }
                    //String format = codeURL.getOriginalFilename().substring(codeURL.getOriginalFilename().indexOf("."));
                    //通过文件后缀 判断是不是封面
                    if (!isFormat) {
                        repData.setCode(ViedoSystemResult.FormatError.getCode());
                        repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                        return repData;
                    }
                    vo.setCodeName(codeName);
                    vo.setCodeFormat(format);
                } else {
                    repData.setCode(ViedoSystemResult.Empty.getCode());
                    repData.setMsg(ViedoSystemResult.Empty.getMsg());
                    return repData;
                }
            }

        }
        // 判断 H5 路径
        if (StringUtils.isNotBlank(url) && !vo.getViedoUrl().equals(url)) {
            isChange = true;
            vo.setViedoUrl(url);
            /* if (FileOperationUtils.URLAvailability(url)) {
            
            } else {
                repData.setCode(ViedoSystemResult.UrlError.getCode());
                repData.setMsg(ViedoSystemResult.UrlError.getMsg());
                return repData;
            }*/

        }
        // 通过是否改变的标识符 判断文件是否改变
        // 文件 最后保存是因为 如果 分开保存 可能会造成 后面报错 可是前面的文件 已经报错的情况 这是不应该的
        if (StringUtils.isNotBlank(codeURL)) {
            // String codeResult;
            try {
                vo.setCodeUrl(getFileUploadPath(codeURL, CODE));
                codeFile.delete();
            } catch (Exception e) {
                repData.setCode(ViedoSystemResult.SaveError.getCode());
                repData.setMsg(ViedoSystemResult.SaveError.getMsg());
                return repData;
            }

        }
        if (StringUtils.isNotBlank(imgURL)) {

            try {
                //设置 封面 即高级搜索的图片路径
                vo.setImgUrl(getFileUploadPath(imgURL, IMG));
                //设置 封面 即高级搜索的图片路径
                //设置 大图 即 首页
                FileOperationUtils.resize(imgFile, imgFile, MAXIMG);
                vo.setMaxImgUrl(getFileUploadPath(imgFile.getAbsolutePath(), MAXIMG));
                //设置 封面 即相似推荐的图片路径
                FileOperationUtils.resize(imgFile, imgFile, SMALLIMG);
                vo.setSmallImgUrl(getFileUploadPath(imgFile.getAbsolutePath(), SMALLIMG));
                imgFile.delete();
            } catch (Exception e) {
                repData.setCode(ViedoSystemResult.SaveError.getCode());
                repData.setMsg(ViedoSystemResult.SaveError.getMsg());
                return repData;
            }

        }
        if (isChange) {

            Timestamp ts = new Timestamp(System.currentTimeMillis());

            //设置更新时间
            vo.setUpdateTime(format.format(ts));
            vo.setVaildStatus(1);
            // vo.setViedoMd5(null);
            // 首先保存 视频详情记录
            int result = viedoDetialDao.updateViedoDetail(vo);
            if (result > 0) {
                addViedoDetialToSolr(vo);
                // 如果视频详情保存成功则 保存 记录表
                ViedoRecord record = new ViedoRecord();
                try {
                    BeanUtils.copyProperties(record, vo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                record.setViedoId(vo.getId());
                viedoDetialDao.addViedoDetailUploadRecord(record);
            }

            repData.setData(result);
            repData.setCount(1);

        } else {
            repData.setCode(ViedoSystemResult.NotChange.getCode());
            repData.setMsg(ViedoSystemResult.NotChange.getMsg());
            return repData;
        }

        return repData;
    }

    /**
     * 保存 视频的临时文件
     * @param viedo2
     * @return
     */
    public RepData<String> uploadTempViedo(CommonsMultipartFile viedo) {
        RepData<String> repData = new RepData<String>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(null);
        repData.setCount(0);
        //标识 是否支持格式   格式 是从配置文件中获取的
        boolean isFormat = false;
        if (!viedo.isEmpty()) {
            //判断格式
            String format = viedo.getOriginalFilename().substring(viedo.getOriginalFilename().indexOf("."));
            String[] formats = Constants.UPLOAD_VIEDO_FORMAT.split(",");
            for (String string : formats) {
                if (format.equalsIgnoreCase(string)) {
                    isFormat = true;
                }
            }
            if (!isFormat) {
                repData.setCode(ViedoSystemResult.FormatError.getCode());
                repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                return repData;
            }
            //判断大小
            if (viedo.getSize() > Integer.valueOf(Constants.UPLOAD_VIEDO_SIZE)) {
                repData.setCode(ViedoSystemResult.Overflow.getCode());
                repData.setMsg(ViedoSystemResult.Overflow.getMsg());
                return repData;
            }
            if (viedo.getSize() <= 0) {
                repData.setCode(ViedoSystemResult.Empty.getCode());
                repData.setMsg(ViedoSystemResult.Empty.getMsg());
                return repData;
            }

            //MD5
            DiskFileItem diskFileItem = (DiskFileItem) viedo.getFileItem();
            File tempFile = diskFileItem.getStoreLocation();
            String fileMD5 = FileOperationUtils.getFileMd5(tempFile);
            Integer resultMD5 = viedoDetialDao.comparisonViedoMD5(fileMD5);
            if (resultMD5 > 0) {
                repData.setCode(ViedoSystemResult.ViedoExsts.getCode());
                repData.setMsg(ViedoSystemResult.ViedoExsts.getMsg());
                return repData;
            }
            //生成路径 保存在临时路径
            String viedoTempPath = null;
            try {
                viedoTempPath = FileOperationUtils.getPath(TEMP);
            } catch (UnsupportedEncodingException e1) {
                repData.setCode(ViedoSystemResult.SystemError.getCode());
                repData.setMsg(ViedoSystemResult.SystemError.getMsg());
                return repData;
            }
            //当 getPath 方法传参错误 或者 文件夹创建失败时 imgPath会为空
            if (viedoTempPath == null) {
                repData.setCode(ViedoSystemResult.SystemError.getCode());
                repData.setMsg(ViedoSystemResult.SystemError.getMsg());
                return repData;
            }
            viedoTempPath += format;
            // 保存 文件
            try {
                viedo.transferTo(new File(viedoTempPath));
            } catch (Exception e) {
                repData.setCode(ViedoSystemResult.SaveError.getCode());
                repData.setMsg(ViedoSystemResult.SaveError.getMsg());
                return repData;
            }
            repData.setData(viedoTempPath);
            repData.setCount(1);
        }
        return repData;
    }

    /**上传封面的临时文件
     * @param img  图片
     * @param type  标记是否是二维码 1是 0不是
     * @return
     */

    public RepData<String> uploadTempImg(CommonsMultipartFile img) {
        RepData<String> repData = new RepData<String>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(null);
        repData.setCount(0);
        //标识 是否支持格式   格式 是从配置文件中获取的
        boolean isFormat = false;
        if (!img.isEmpty()) {
            //判断格式
            String format = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
            String[] formats = Constants.UPLOAD_IMG_FORMAT.split(",");
            for (String string : formats) {
                if (format.equalsIgnoreCase(string)) {
                    isFormat = true;
                }
            }
            if (!isFormat) {
                repData.setCode(ViedoSystemResult.FormatError.getCode());
                repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                return repData;
            }
            //判断大小
            if (img.getSize() > Integer.valueOf(Constants.UPLOAD_IMG_SIZE)) {
                repData.setCode(ViedoSystemResult.Overflow.getCode());
                repData.setMsg(ViedoSystemResult.Overflow.getMsg());
                return repData;
            }
            if (img.getSize() <= 0) {
                repData.setCode(ViedoSystemResult.Empty.getCode());
                repData.setMsg(ViedoSystemResult.Empty.getMsg());
                return repData;
            }

            // 图片不用 MD5

            //生成路径 保存在临时路径
            String imgTempPath = null;
            try {
                imgTempPath = FileOperationUtils.getPath(TEMP);
            } catch (UnsupportedEncodingException e1) {
                repData.setCode(ViedoSystemResult.SystemError.getCode());
                repData.setMsg(ViedoSystemResult.SystemError.getMsg());
                e1.printStackTrace();
            }
            //当 getPath 方法传参错误 或者 文件夹创建失败时 imgPath会为空
            if (imgTempPath == null) {
                repData.setCode(ViedoSystemResult.SystemError.getCode());
                repData.setMsg(ViedoSystemResult.SystemError.getMsg());
                return repData;
            }
            imgTempPath += format;
            //普通图片需要裁剪二维码不裁剪
            //图片裁剪

            DiskFileItem diskFileItem = (DiskFileItem) img.getFileItem();
            File imgTemp = diskFileItem.getStoreLocation();
            try {
                FileOperationUtils.resize(imgTemp, imgTemp, IMG);
            } catch (IOException e1) {
                repData.setCode(ViedoSystemResult.SystemError.getCode());
                repData.setMsg(ViedoSystemResult.SystemError.getMsg());
                return repData;
            }
            BufferedInputStream bufferedInputStream = null;
            BufferedOutputStream stream = null;
            byte[] buff = new byte[1024];
            try {
                //imgTemp 裁剪后的文件
                bufferedInputStream = new BufferedInputStream(new FileInputStream(imgTemp));
                stream = new BufferedOutputStream(new FileOutputStream(new File(imgTempPath)));
                int bytesRead = 0;
                while (-1 != (bytesRead = bufferedInputStream.read(buff, 0, buff.length))) {
                    stream.write(buff, 0, bytesRead);
                }
                // img.transferTo(new File(imgTempPath));
            } catch (Exception e) {
                repData.setCode(ViedoSystemResult.SaveError.getCode());
                repData.setMsg(ViedoSystemResult.SaveError.getMsg());
                return repData;
            } finally {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            repData.setData(imgTempPath);
            repData.setCount(1);
        }
        return repData;
    }

    /**上传二维码的临时文件
     * @param img  图片
     * @param type  标记是否是二维码 1是 0不是
     * @return
     */

    public RepData<String> uploadTempCode(CommonsMultipartFile img) {
        RepData<String> repData = new RepData<String>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(null);
        repData.setCount(0);
        //标识 是否支持格式   格式 是从配置文件中获取的
        boolean isFormat = false;
        if (!img.isEmpty()) {
            //判断格式
            String format = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
            String[] formats = Constants.UPLOAD_IMG_FORMAT.split(",");
            for (String string : formats) {
                if (format.equalsIgnoreCase(string)) {
                    isFormat = true;
                }
            }
            if (!isFormat) {
                repData.setCode(ViedoSystemResult.FormatError.getCode());
                repData.setMsg(ViedoSystemResult.FormatError.getMsg());
                return repData;
            }
            //判断大小
            if (img.getSize() > Integer.valueOf(Constants.UPLOAD_IMG_SIZE)) {
                repData.setCode(ViedoSystemResult.Overflow.getCode());
                repData.setMsg(ViedoSystemResult.Overflow.getMsg());
                return repData;
            }
            if (img.getSize() <= 0) {
                repData.setCode(ViedoSystemResult.Empty.getCode());
                repData.setMsg(ViedoSystemResult.Empty.getMsg());
                return repData;
            }

            // 图片不用 MD5

            //生成路径 保存在临时路径
            String imgTempPath = null;
            try {
                imgTempPath = FileOperationUtils.getPath(TEMP);
            } catch (UnsupportedEncodingException e1) {
                repData.setCode(ViedoSystemResult.SystemError.getCode());
                repData.setMsg(ViedoSystemResult.SystemError.getMsg());
                e1.printStackTrace();
            }
            //当 getPath 方法传参错误 或者 文件夹创建失败时 imgPath会为空
            if (imgTempPath == null) {
                repData.setCode(ViedoSystemResult.SystemError.getCode());
                repData.setMsg(ViedoSystemResult.SystemError.getMsg());
                return repData;
            }
            imgTempPath += format;
            //普通图片需要裁剪二维码不裁剪
            try {
                img.transferTo(new File(imgTempPath));
            } catch (Exception e) {
                e.printStackTrace();
                repData.setCode(ViedoSystemResult.SystemError.getCode());
                repData.setMsg(ViedoSystemResult.SystemError.getMsg());
                return repData;

            }

            repData.setData(imgTempPath);
            repData.setCount(1);
        }
        return repData;
    }

    /**删除视频详情 软删除
     * @param viedoDetial
     * @return
     */
    public RepData<Integer> deleteViedoDetial(ViedoDetial viedoDetial) {
        RepData<Integer> repData = new RepData<Integer>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(null);
        repData.setCount(0);
        viedoDetial.setVaildStatus(0);
        Map<String, Object> map = new HashMap<String, Object>();
        //id 视频ID
        map.put("id", viedoDetial.getId());
        //获取视频详情
        ViedoDetial vo = viedoDetialDao.getViedoDetailById(map);
        if (vo == null) {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg(ViedoSystemResult.Empty.getMsg());
            return repData;
        }
        //判断用户
        if (StringUtils.isNotBlank(viedoDetial.getUserName())) {
            // 判断用户是否存在
            UserInfo u = userInfoCacheService.getUserByUserName(viedoDetial.getUserName());
            if (u == null) {
                repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
                repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
                return repData;
            }
            // 判断 当前用户是否存在 并且是否是 视频的上传人
            if (!vo.getUserName().equals(viedoDetial.getUserName())) {
                repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
                repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
                return repData;
            }
        } else {
            repData.setCode(ViedoSystemResult.UserInfoEmpty.getCode());
            repData.setMsg(ViedoSystemResult.UserInfoEmpty.getMsg());
            return repData;
        }
        int result = viedoDetialDao.updateViedoDetail(viedoDetial);
        if (result > 0) {
            deleteViedoDetialToSolr(vo.getId());
        }

        repData.setData(result);
        repData.setCount(1);
        return repData;
    }

    /**
     * 根据ID 将视频详情添加到 solr 库中
     * @param id
     * @return
     */
    public RepData<String> addViedoDetialToSolr(int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        RepData<String> repData = new RepData<String>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setCount(0);
        repData.setData(null);
        if (id != 0) {
            //通过ID 获取视频详情 用于视频播放
            map.put("id", id);
            map.put("pageSize", "1");
            map.put("pageNum", "0");
            ViedoDetialVo vo = viedoDetialDao.getViedoDetailById(map);
            //Integer count = viedoDetialService.getCountViedoDetail();
            if (vo != null) {
                addViedoDetialToSolr(vo);

            } else {
                repData.setCode(ViedoSystemResult.Empty.getCode());
                repData.setMsg(ViedoSystemResult.Empty.getMsg());
            }

        } else {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg(ViedoSystemResult.Empty.getMsg());
        }
        return repData;
    }

    /**
     * 将视频详情添加到 solr 库中
     * @param vo
     */
    public void addViedoDetialToSolr(ViedoDetial vo) {
        /*  SolrInputDocument inputDocument = new SolrInputDocument();
        //PK
        String solrPKID = KnowledgeConfigEnum.KnowledgeCategory.microcourse.name() + vo.getId();
        inputDocument.addField("id", solrPKID);
        //基本信息
        //检索类型 目前类型分为 工单和视频
        inputDocument.addField("sm_category", KnowledgeConfigEnum.KnowledgeCategory.microcourse.name());
        //知识标识 知识 和 非知识
        inputDocument.addField("sm_knowledgeType", KnowledgeConfigEnum.KnowledgeValidity.enabled);
        //记录对应的关系数据库的主键id
        inputDocument.addField("sm_pkId", vo.getId());
        //视频的标题 [对应工单的标题][对应视频的标题]
        inputDocument.addField("sm_documentTitle", vo.getViedoTitle());
        //视频的描述 [对应工单的问题描述] [对应视频的描述]
        inputDocument.addField("sm_documentSummary", vo.getViedoDescription());
        //视频的正文 [对应工单的采纳答案] [对应视频的正文]
        
        // String findDocumentBodyById = findDocumentBodyById(solrPKID);
        inputDocument.addField("sm_documentBody", vo.getViedoDescription());
        if (findDocumentBodyById == null) {
            throw new RuntimeException("documentBody 你能为空!");
        
        }
        
        //视频的来源
        inputDocument.addField("sm_documentSource", KnowledgeConfigEnum.KnowledgeSource.wodemand.name());
        
        //地域信息
        //视频所属省份的ID [对应工单省份信息] [对应视频分类的省分信息]
        inputDocument.addField("sm_provinceId", vo.getUserProvinceId());
        //视频所属省份的中文名称 [对应工单省份信息] [对应视频分类的省分信息]
        inputDocument.addField("sm_provinceName", vo.getUserProvinceName());
        //视频所属地市的ID [对应工单地市信息] [对应视频分类的地市信息]
        inputDocument.addField("sm_cityId", vo.getUserCityId());
        //视频所属地市的中文名称 [对应工单地市信息] [对应视频分类的地市信息]
        inputDocument.addField("sm_cityName", vo.getUserCityName());
        
        //分类信息
        //视频所属分类Id [一级] [对应工单专业] [对应视频知识分类]
        inputDocument.addField("sm_systemType1", vo.getViedoClassifyId());
        //视频所属分类中文名称 [一级] [对应工单专业] [对应视频知识分类]
        inputDocument.addField("sm_systemType1Name", vo.getViedoClassifyName());
         //视频所属分类Id [二级] [对应工单专业] [对应视频知识分类]
        inputDocument.addField("sm_systemType2", vo.getKnowledgeCategorySystemType2());
        //视频所属分类中文名称 [二级] [对应工单专业] [对应视频知识分类]
        inputDocument.addField("sm_systemType2Name", vo.getKnowledgeCategorySystemType2Name());
        //视频所属分类Id [三级] [对应工单专业] [对应视频知识分类]
        inputDocument.addField("sm_systemType3", vo.getKnowledgeCategorySystemType3());
        //视频所属分类中文名称 [三级] [对应工单专业] [对应视频知识分类]
        inputDocument.addField("sm_systemType3Name", vo.getKnowledgeCategorySystemType3Name());
        
        // documentCategoryId documentCategoryName  documentCategoryWayName
        //视频当前分类id
        inputDocument.addField("sm_documentCategoryId", vo.getViedoClassifyId());
        // KnowledgeCategory demandKnowledgeCategory = knowledgeCategoryService.findKnowledgeCategoryAllById(vo.getKnowledgeCategoryId());
        //视频当前分类中文名称
        
        inputDocument.addField("sm_documentCategoryName", vo.getViedoClassifyName());
        //视频层级路径中文名称
        //String knowledgeCategoryWayName = knowledgeCategoryService.transformWayName(demandKnowledgeCategory);
        
        inputDocument.addField("sm_documentCategoryWayName", vo.getViedoClassifyName());
        //视频层级路径id
        inputDocument.addField("sm_documentCategoryWayId", vo.getViedoClassifyId());
        
        //用户信息
        UserInfo info = userInfoCacheService.getUserByUserName(vo.getUserName());
        //视频创建用户ID[对应工单创建人] [对应视频的创建人]
        inputDocument.addField("sm_createUserId", info.getId());
        //视频创建用户账号[对应工单创建人] [对应视频的创建人]
        inputDocument.addField("sm_createUserAccount", info.getUserName());
        //视频创建用户中文名称[对应工单创建人] [对应视频的创建人]
        inputDocument.addField("sm_createUserNick", info.getUserNick());
        //视频创建用户头像[对应工单创建人] [对应视频的创建人]
        UserInfoCacheBean userInfoById = userInfoCacheService.getUserInfoById(info.getId());
        if (userInfoById != null) {
            inputDocument.addField("sm_createUserMidImage", userInfoById.getUserMidHeaderImage());
        }
        
        //视频信息
        //视频创建时间
        Date date = null;
        try {
            date = format.parse(vo.getCreateTime());
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        inputDocument.addField("sm_documentCreateDate", SolrUtils.UTCDateFormat(date));
        //视频版本
        inputDocument.addField("sm_documentVersion", VERSION);
        //视频的查看次数
        inputDocument.addField("sm_documentViewerCount", vo.getBrowseCount());
         //文件类型
        //default
        if (StringUtils.isNotBlank(vo.getActionRawData())) {
            String postfix = StringUtils.substringAfterLast(vo.getActionRawData(), ".");
            if (postfix.equalsIgnoreCase("doc") || postfix.equalsIgnoreCase("docx")) {
                inputDocument.addField("documentFileType", "word");
            } else if (postfix.equalsIgnoreCase("pdf")) {
                inputDocument.addField("documentFileType", "pdf");
            } else if (postfix.equalsIgnoreCase("xls") || postfix.equalsIgnoreCase("xlsx")) {
                inputDocument.addField("documentFileType", "excel");
            } else if (postfix.equalsIgnoreCase("ppt") || postfix.equalsIgnoreCase("pptx")) {
                inputDocument.addField("documentFileType", "ppt");
            } else if (postfix.equalsIgnoreCase("txt") || postfix.equalsIgnoreCase("sql")) {
                inputDocument.addField("documentFileType", "txt");
            } else if (postfix.equalsIgnoreCase("zip") || postfix.equalsIgnoreCase("rar") || postfix.equalsIgnoreCase("7z")) {
                inputDocument.addField("documentFileType", "zip");
            } else {
                inputDocument.addField("documentFileType", "word");
            }
        } else {
            inputDocument.addField("documentFileType", "word");
        
        }
        
        //视频知识特有的属性
        //课程类型
        inputDocument.addField("sm_viedoType", vo.getViedoType());
        //视频的封面
        inputDocument.addField("sm_imgUrl", vo.getImgUrl());
        //二维码
        inputDocument.addField("sm_codeUrl", vo.getCodeUrl());
        //视频时长
        inputDocument.addField("sm_viedoLength", vo.getViedoLength());
        //视频时长
        inputDocument.addField("sm_pariseCount", vo.getPraiseCount());
        //视频时长
        inputDocument.addField("sm_smallImgUrl", vo.getSmallImgUrl());
        
        try {
            HttpSolrClient solrServer = SolrUtils.getSolrServer("kfsdk");
            solrServer.add(inputDocument);
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    public void deleteViedoDetialToSolr(int viedoId) {
        /* HttpSolrClient solrServer = SolrUtils.getSolrServer("kfsdk");
        String solrPKID = KnowledgeConfigEnum.KnowledgeCategory.microcourse.name() + viedoId;
        try {
            solrServer.deleteById(solrPKID);
            solrServer.commit();
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }

    /**高級搜索
     * @param type
     * @param classIfy
     * @param userName
     * @param startTime
     * @param endTime
     * @param context
     * @return
     */
    public RepData<List<Map<String, Object>>> searchViedoDetial(SearchContent sContent) {
        RepData<List<Map<String, Object>>> repData = new RepData<List<Map<String, Object>>>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(null);
        repData.setCount(1);
        repData.setPageNumber(1);
        repData.setPagesize(1);
        /* if (sContent == null) {
            return null;
        }
        SolrQuery query = new SolrQuery();
        StringBuilder sbBuilder = new StringBuilder();
        sbBuilder.append("sm_category:" + KnowledgeConfigEnum.KnowledgeCategory.microcourse.name() + " AND sm_knowledgeType:" + KnowledgeConfigEnum.KnowledgeValidity.enabled.name());
        //发起日期 : 1 设置起始日期 没有设置结束日期
        //发起日期 : 1 设置起始日期 没有设置结束日期
        String startTime = sContent.getStartTime();
        String endTime = sContent.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isBlank(endTime)) {
            startTime = SolrUtils.dateStringToUTCString(startTime);
            endTime = SolrUtils.UTCDateFormat(new Date());
            sbBuilder.append(" AND sm_documentCreateDate:" + wrapDateValue(startTime + " TO " + endTime));
        } else if (StringUtils.isBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            // 发起日期 : 2 没有设置起始日期 设置了结束日期(结束时间自动往后一天;例如:2017-11-15 23:59:59->2017-11-16 00:00:00)
            endTime = SolrUtils.dateStringToUTCStringAddOneDay(endTime);
            startTime = " * ";
            sbBuilder.append(" AND sm_documentCreateDate:" + wrapDateValue(startTime + " TO " + endTime));
        } else if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            //发起日期 : 3设置了起始日期  设置了结束日期
            startTime = SolrUtils.dateStringToUTCString(startTime);
            endTime = SolrUtils.dateStringToUTCStringAddOneDay(endTime);
            sbBuilder.append(" AND sm_documentCreateDate:" + wrapDateValue(startTime + " TO " + endTime));
        }
        if (StringUtils.isNotBlank(sContent.getUserNick())) {
            sbBuilder.append(" AND (sm_createUserNick  :").append("*" + sContent.getUserNick() + "*");
        }
        if (StringUtils.isNotBlank(sContent.getUserNick())) {
            sbBuilder.append(" OR sm_createUserAccount:").append("*" + sContent.getUserNick() + "* )");
        }
        
        if (StringUtils.isNotBlank(sContent.getViedoClassifId())) {
            sbBuilder.append(" AND sm_systemType1:").append(sContent.getViedoClassifId());
        }
        if (StringUtils.isNotBlank(sContent.getViedoType())) {
            sbBuilder.append(" AND sm_viedoType:").append(sContent.getViedoType());
        }
        if (StringUtils.isNotBlank(sContent.getKeyWord())) {
            String keyWords = sContent.getKeyWord();
            sbBuilder.append(" AND ( ");
            keyWords = keyWords.trim();
            String[] arrKeyWords = keyWords.split(" ");
            for (int i = 0; i < arrKeyWords.length; i++) {
        
                if (arrKeyWords[i].trim().length() > 0) {
                    sbBuilder.append(" sm_documentSearchMash:").append(arrKeyWords[i].trim());
                    if (i < arrKeyWords.length - 1) {
                        sbBuilder.append(" OR ");
                    } else {
                        sbBuilder.append(") ");
                    }
                }
        
            }
            //设置高亮
            query.setHighlight(true);
            query.addHighlightField("sm_documentTitle,sm_documentSummary");
            query.setHighlightSimplePre("<span class='search-highlight'>");
            query.setHighlightSimplePost("</span>"); //渲染标签
        
        } else {
            sbBuilder.append(" AND *:*");
        }
        //4. 搜索结果列表: 封面、标题、简介、上传时间、课程分类、课程类型、播放次数、二维码，上传人
        //检查 是否存储 就是检查  schema.xml 中 stored 是否为true 代表是否存储 若没有存储 则抛出异常
        query.setFields("sm_pkId", "sm_category", "sm_imgUrl", "sm_documentTitle", "sm_documentSummary", "sm_documentCreateDate", "sm_systemType1", "sm_codeUrl", "sm_createUserNick", "sm_codeUrl", "sm_viedoLength", "sm_systemType1Name", "sm_documentViewerCount", "sm_documentViewerCount", "sm_viedoType", "sm_viedoType", "sm_createUserAccount", "sm_pariseCount", "sm_smallImgUrl");
        //设置排序
        if (sbBuilder.toString().equals("sm_category:microcourse AND sm_knowledgeType:enabled AND *:*")) {
            query.addSort("sm_documentCreateDate", ORDER.desc);
                if (sContent.getCreateTimeOrder() > 0) {
                if (sContent.getCreateTimeOrder() == 1) {
                    query.addSort("sm_documentCreateDate", ORDER.desc);
                } else {
                    query.addSort("sm_documentCreateDate", ORDER.asc);
                }
            }
                  if (sContent.getPariseCountOrder() > 0) {
                if (sContent.getCreateTimeOrder() == 1) {
                    query.addSort("sm_pariseCount", ORDER.desc);
                } else {
                    query.addSort("sm_pariseCount", ORDER.asc);
                }
            }
                  if (sContent.getViewerCountOrder() > 0) {
                if (sContent.getCreateTimeOrder() == 1) {
                    query.addSort("sm_documentViewerCount", ORDER.desc);
                } else {
                    query.addSort("sm_documentViewerCount", ORDER.asc);
                }
            }
        }
        
        //设置 分页
        int pageNum = sContent.getPageNum();
        int pageSize = sContent.getPageSize();
        if (sContent.getPageNum() == 0) {
            query.setStart(0);
            query.setRows(10);
        } else {
            query.setStart((pageNum - 1) * pageSize);
            query.setRows(pageSize);
        }
        HttpSolrClient solrServer = SolrUtils.getSolrServer("kfsdk");
        try {
        
            query.setQuery(sbBuilder.toString());
            QueryResponse response = solrServer.query(query);
            SolrDocumentList list = response.getResults();
            Map<String, Map<String, List<String>>> highlightingList = response.getHighlighting();
        
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            for (SolrDocument solrDocument : list) {
                String solrPKId = solrDocument.getFirstValue("sm_pkId").toString();
                String knowlegeType = solrDocument.getFieldValue("sm_category").toString();
                Map<String, List<String>> highlightingMap = null;
                if (highlightingList != null) {
                    //knowlegeType + solrPKId solr 的主键 这个在存的时候设置的
                    highlightingMap = highlightingList.get(knowlegeType + solrPKId);
                }
                Map<String, Object> parameter = new HashMap<String, Object>();
                parameter = transToViedoDetialFromSolrResult(solrDocument, highlightingMap);
                resultList.add(parameter);
            }
            repData.setData(resultList);
            repData.setCount((int) list.getNumFound());
        } catch (Exception e) {
            repData.setCode(ViedoSystemResult.ERROR.getCode());
            repData.setMsg(ViedoSystemResult.ERROR.getMsg());
            e.printStackTrace();
        }*/

        return repData;
    }

    /**
     * @param string
     * @return
     */
    private String wrapDateValue(String value) {
        return "[" + value + "]";
    }

    /**
     * 时间转换为UTC
     * @param dateString
     * @return
     */
    public static String dateStringToUTCString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        try {
            java.util.Date date = sdf.parse(dateString);
            return UTCDateFormat(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new String();
    }

    public static String UTCDateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return format.format(date);
    }

    /**
    * 将 solr 数据转换成视频详情
    * @param solrDocument
    * @param highlightingMap
    * @return
    */
    private Map<String, Object> transToViedoDetialFromSolrResult(SolrDocument solrDocument, Map<String, List<String>> highlightingMap) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        /* //数据的主键 即 视频ID
        
        Object sm_pkId = solrDocument.getFieldValue("sm_pkId");
        parameter.put("viedoId", sm_pkId);
        // 视频的标题 因 标题许高亮显示 highlightingMap 为 该条 数据的高亮数据  因数据 存在 一对多的关系 比如 标签 在入solr 时 可以放入 多条数据
        //即一个集合 所以返回的高亮数据也就可能是多条的
        Object viedoTitle = solrDocument.getFieldValue("sm_documentTitle");
        if (highlightingMap != null) {
            List<String> sm_documentTitleHighlightingList = highlightingMap.get("sm_documentTitle");
            if (sm_documentTitleHighlightingList != null && sm_documentTitleHighlightingList.size() > 0) {
                viedoTitle = sm_documentTitleHighlightingList.get(0);
            }
        }
        parameter.put("viedoTitle", viedoTitle);
        // 描述  同上
        Object viedoDescription = solrDocument.getFieldValue("sm_documentSummary");
        if (highlightingMap != null) {
            List<String> sm_documentSummaryHighlightingList = highlightingMap.get("sm_documentSummary");
            if (sm_documentSummaryHighlightingList != null && sm_documentSummaryHighlightingList.size() > 0) {
                viedoDescription = sm_documentSummaryHighlightingList.get(0);
            }
        }
        parameter.put("viedoDescription", viedoDescription);
        //上传人昵称
        Object viedoCreateNick = solrDocument.getFieldValue("sm_createUserNick");
        parameter.put("viedoCreateNick", viedoCreateNick);
        //视频上传时间
        Object viedoCreateDateTime = solrDocument.getFieldValue("sm_documentCreateDate");
        if (viedoCreateDateTime == null) {
            parameter.put("viedoCreateDateTime", viedoCreateDateTime);
        } else {
            parameter.put("viedoCreateDateTime", SolrUtils.CSTDateFormat(viedoCreateDateTime.toString()));
        }
        
        //视频分类
        Object viedoClassifyName = solrDocument.getFieldValue("sm_systemType1Name");
        parameter.put("viedoClassifyName", viedoClassifyName);
        
        //视频浏览次数
        Object viedoViewerCount = solrDocument.getFieldValue("sm_documentViewerCount");
        parameter.put("viedoViewerCount", viedoViewerCount);
        //视频类型
        Object viedoType = solrDocument.getFieldValue("sm_viedoType");
        parameter.put("viedoType", viedoType);
        //封面路径
        Object imgUrl = solrDocument.getFieldValue("sm_imgUrl");
        parameter.put("imgUrl", Constants.VK_DOWN_URL + imgUrl);
        //二维码路径
        Object codeUrl = solrDocument.getFieldValue("sm_codeUrl");
        parameter.put("codeUrl", Constants.VK_DOWN_URL + codeUrl);
        //视频长度
        Object viedoLength = solrDocument.getFieldValue("sm_viedoLength");
        parameter.put("viedoLength", viedoLength);
        //点赞次数
        Object pariseCount = solrDocument.getFieldValue("sm_pariseCount");
        parameter.put("pariseCount", pariseCount);
        Object smallImgUl = solrDocument.getFieldValue("sm_smallImgUrl");
        parameter.put("smallImgUl", Constants.VK_DOWN_URL + smallImgUl);
            if (!vo.getImgUrl().startsWith(Constants.VK_DOWN_URL)) {
            vo.setImgUrl(Constants.VK_DOWN_URL + vo.getImgUrl());
            vo.setSmallImgUrl(Constants.VK_DOWN_URL + vo.getSmallImgUrl());
            vo.setMaxImgUrl(Constants.VK_DOWN_URL + vo.getMaxImgUrl());
            if (vo.getViedoType() == 1) {
                vo.setCodeUrl(Constants.VK_DOWN_URL + vo.getCodeUrl());
        
            } else {
                vo.setViedoUrl(Constants.VK_DOWN_URL + vo.getViedoUrl());
            }
        }
        //inputDocument.addField("sm_pariseCount", vo.getPraiseCount());
        */ return parameter;
    }

    //设置结束日期(结束时间自动往后一天;例如:2017-11-15 23:59:59->2017-11-16 00:00:00)
    public static String dateStringToUTCStringAddOneDay(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        try {
            java.util.Date beginDate = sdf.parse(dateString);
            Calendar date = Calendar.getInstance();
            date.setTime(beginDate);
            date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
            Date endDate = sdf.parse(sdf.format(date.getTime()));
            return UTCDateFormat(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new String();
    }

    /**
     *
     * @param URL 文件路径
     * @param type  类型 1 图片  2 大图 3 小图  4 二维码  5 视频
     * @return
     * @throws Exception
     */
    public static String getFileUploadPath(String URL, int type) throws Exception {
        String imgResult;

        imgResult = HttpUploadUtil.fileUpload(URL, type);

        System.out.println("imgResult===" + imgResult);
        JSONObject tObject = JSONObject.parseObject(imgResult);
        System.out.println("tObject===" + tObject);
        int one = Integer.parseInt(tObject.get("code").toString());
        System.out.println(one);

        if (one != 200) {
            throw new Exception();
        } else {
            String path = tObject.get("data").toString();
            String[] strings = path.split(Constants.VK_PROJECT_URL);
            //path = Constants.VK_DOWN_URL + strings[1];
            path = strings[1];
            return path;
        }
    }

    public static void main(String[] args) {
        /* String path = "\\opt\\yanfa\\apache-tomcat-yanfa\\webapps\\itHelpService\\uploadfile\\Img\\20171207\\9dea3031-b3b1-401f-a19d-1bc1410a7a90.png";
        String[] strings = path.split("itHelpService");
        System.out.println(strings[1]);*/
        /*  Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println(format.format(ts));*/
        ViedoDetial vo = new ViedoDetial();
        vo.setBrowseCount(0);
        ViedoRecord record = new ViedoRecord();
        try {
            BeanUtils.copyProperties(vo, record);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(record.getBrowseCount());

    }
}
