/*
 * FileName: ViedoDetial.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//
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
 * 2017年11月29日 下午2:33:52          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "ViedoDetialS")
@XmlType(name = "ViedoDetialTest", propOrder = { "id", "createTime", "userName", "vaildStatus", "updateTime", "browseCount", "praiseCount", "viedoName", "viedoTitle", "viedoDescription", "viedoSize", "viedoUrl", "viedoLength", "viedoType", "viedoClassifyId", "viedoClassifyName", "viedoFormat", "imgName", "imgSize", "imgUrl", "smallImgUrl", "maxImgUrl", "imgFormat", "codeUrl", "codeName", "codeFormat", "viedoMd5", "userProvinceId", "userProvinceName", "userCityId", "userCityName" }, namespace = "")
@ApiModel(value = "ViedoDetial对象", description = "视频详情")
public class ViedoDetial implements Serializable {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     *
     */
    private static final long serialVersionUID = -4846018275282682759L;
    /**
     * 视频ID
     */
    @XmlAttribute(name = "ID")
    @XmlElement(name = "id")
    @ApiModelProperty(value = "视频ID", name = "id", example = "255")
    private int id;

    /**
     * 创建时间
     */
    @XmlElement(name = "createTime")
    @ApiModelProperty(value = "创建时间", name = "createTime", example = "2018-03-26 15:37:11")
    private String createTime;

    /**
     * 上传用户的用户名
     */
    @XmlElement(name = "userName")
    @ApiModelProperty(value = "上传用户的用户名", name = "userName", example = "gun7")
    private String userName;
    /**
     * 是否删除(0删除，1未删除)
     */
    @XmlElement(name = "vaildStatus")
    @ApiModelProperty(value = "是否删除(0删除，1未删除)", name = "vaildStatus", example = "1")
    private int vaildStatus;
    /**
     * 跟新时间
     */
    @XmlElement(name = "updateTime")
    @ApiModelProperty(value = "跟新时间", name = "updateTime", example = "2018-03-26 15:37:11")
    private String updateTime;
    /**
     * 浏览次数
     */
    @XmlElement(name = "browseCount")
    @ApiModelProperty(value = "浏览次数", name = "browseCount", example = "51")
    private int browseCount;
    /**
     * 点赞次数
     */
    @XmlElement(name = "praiseCount")
    @ApiModelProperty(value = "点赞次数", name = "praiseCount", example = "2")
    private int praiseCount;
    /**
     * 视频名称
     */
    @XmlElement(name = "viedoName")
    @ApiModelProperty(value = "视频名称", name = "viedoName", example = "cBSS现场拍照操作.mp4")
    private String viedoName;
    /**
     * 视频标题
     */
    @XmlElement(name = "viedoTitle")
    @ApiModelProperty(value = "视频标题", name = "viedoTitle", example = "cBSS现场拍照操作")
    private String viedoTitle;

    /**
     * 视频描述
     */
    @XmlElement(name = "viedoDescription")
    @ApiModelProperty(value = "视频描述", name = "viedoDescription", example = "cBSS现场拍照操作")
    private String viedoDescription;
    /**
     * 视频大小
     */
    @XmlElement(name = "viedoSize")
    @ApiModelProperty(value = "视频大小", name = "viedoSize", example = "41629")
    private String viedoSize;
    /**
     * 视频存储路径
     */
    @XmlElement(name = "viedoUrl")
    @ApiModelProperty(value = "视频存储路径", name = "viedoUrl", example = "/uploadfile/Viedo/20180326/cc506dc6-152c-4aff-87bf-612d3544cffa.mp4")
    private String viedoUrl;
    /**
     * 视频长度
     */
    @XmlElement(name = "viedoLength")
    @ApiModelProperty(value = "视频长度", name = "viedoLength", example = "00:03:55")
    private String viedoLength;
    /**
     * 视频类型(0视频课程，1H5课程)
     */
    @XmlElement(name = "viedoType")
    @ApiModelProperty(value = "视频类型(0视频课程，1H5课程)", name = "viedoType", example = "0")
    private int viedoType;
    /**
     * 视频分类
     */
    @XmlElement(name = "viedoClassifyId")
    @ApiModelProperty(value = "视频分类", name = "viedoClassifyId", example = "2")
    private int viedoClassifyId;
    /**
     * 视频分类名称
     */
    @XmlElement(name = "viedoClassifyName")
    @ApiModelProperty(value = "视频分类名称", name = "viedoClassifyName", example = "操作讲堂")
    private String viedoClassifyName;
    /**
     * 视频格式
     */
    @XmlElement(name = "viedoFormat")
    @ApiModelProperty(value = "视频格式", name = "viedoFormat", example = ".mp4")
    private String viedoFormat;
    /**
     * 封面名称
     */
    @XmlElement(name = "imgName")
    @ApiModelProperty(value = "封面名称", name = "imgName ", example = "cBSS现场拍照操作.png")
    private String imgName;
    /**
     * 封面大小
     */
    @XmlElement(name = "imgSize")
    @ApiModelProperty(value = "封面大小", name = "封面大小 ", example = "23")
    private String imgSize;
    /**
     * 封面存储路径
     */
    @XmlElement(name = "imgUrl")
    @ApiModelProperty(value = "封面存储路径", name = "imgUrl", example = "/uploadfile/Img/20180326/75f927f2-c4e0-40f1-9649-6cc1872eaff4.png")
    private String imgUrl;
    /**
     * 封面存储路径--首页 大小120*90
     */
    @XmlElement(name = "smallImgUrl")
    @ApiModelProperty(value = "封面存储路径--首页 大小120*90", name = "smallImgUrl", example = "/uploadfile/SmallImg/20180326/368dcbc2-3ecb-4532-a1b0-2677649ea282.png")
    private String smallImgUrl;
    /**
     * 封面存储路径--相似推荐240*180
     */
    @XmlElement(name = "maxImgUrl")
    @ApiModelProperty(value = "封面存储路径--相似推荐240*180", name = "maxImgUrl", example = "/uploadfile/MaxImg/20180326/ba774aa3-b94a-449b-8b71-3a566ccb03fd.png")
    private String maxImgUrl;
    /**
     * 封面格式
     */
    @XmlElement(name = "imgFormat")
    @ApiModelProperty(value = "封面格式", name = "imgFormat", example = ".png")
    private String imgFormat;
    /**
     * 二维码存储路径
     */
    @XmlElement(name = "codeUrl")
    @ApiModelProperty(value = "二维码存储路径", name = "codeUrl", example = "")
    private String codeUrl;

    /**
     * 二维码文件名称
     */
    @XmlElement(name = "codeName")
    @ApiModelProperty(value = "二维码文件名称", name = "codeName", example = "")
    private String codeName;
    /**
     * 二维码的格式
     */
    @XmlElement(name = "codeFormat")
    @ApiModelProperty(value = " 二维码的格式", name = "codeFormat", example = "")
    private String codeFormat;
    /**
     * 普通视频的MD5 值 封面 二维码 H5 没有
     */
    @XmlElement(name = "viedoMd5")
    @ApiModelProperty(value = " 普通视频的MD5 值 封面 二维码 H5 没有", name = "viedoMd5", example = "ad95ddcd564271e5fa522dbd6520ea99")
    private String viedoMd5;
    /**
     * 用户所属省ID
     */
    @XmlElement(name = "userProvinceId")
    @ApiModelProperty(value = " 用户所属省ID", name = "userProvinceId", example = "10010")
    private int userProvinceId;
    /**
     * 用户所属省名称
     */
    @XmlElement(name = "userProvinceName")
    @ApiModelProperty(value = " 用户所属省名称", name = "userProvinceName", example = "总部")
    private String userProvinceName;
    /**
     * 用户所属城市ID
     */
    @XmlElement(name = "userCityId")
    @ApiModelProperty(value = " 用户所属城市ID", name = "userCityId", example = "")
    private int userCityId;
    /**
     * 用户所属城市名称
     */
    @XmlElement(name = "userCityName")
    @ApiModelProperty(value = " 用户所属城市名称", name = "userCityName", example = "")
    private String userCityName;

    /**
     * @return the userProvinceId
     */
    public int getUserProvinceId() {
        return userProvinceId;
    }

    /**
     * @param userProvinceId the userProvinceId to set
     */
    public void setUserProvinceId(int userProvinceId) {
        this.userProvinceId = userProvinceId;
    }

    /**
     * @return the userProvinceName
     */
    public String getUserProvinceName() {
        return userProvinceName;
    }

    /**
     * @param userProvinceName the userProvinceName to set
     */
    public void setUserProvinceName(String userProvinceName) {
        this.userProvinceName = userProvinceName;
    }

    /**
     * @return the userCityId
     */
    public int getUserCityId() {
        return userCityId;
    }

    /**
     * @param userCityId the userCityId to set
     */
    public void setUserCityId(int userCityId) {
        this.userCityId = userCityId;
    }

    /**
     * @return the userCityName
     */
    public String getUserCityName() {
        return userCityName;
    }

    /**
     * @param userCityName the userCityName to set
     */
    public void setUserCityName(String userCityName) {
        this.userCityName = userCityName;
    }

    /**
     * @return the viedoMd5
     */
    public String getViedoMd5() {
        return viedoMd5;
    }

    /**
     * @param viedoMd5 the viedoMd5 to set
     */
    public void setViedoMd5(String viedoMd5) {
        this.viedoMd5 = viedoMd5;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the codeUrl
     */
    public String getCodeUrl() {
        return codeUrl;
    }

    /**
     * @param codeUrl the codeUrl to set
     */
    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the vaildStatus
     */
    public int getVaildStatus() {
        return vaildStatus;
    }

    /**
     * @param vaildStatus the vaildStatus to set
     */
    public void setVaildStatus(int vaildStatus) {
        this.vaildStatus = vaildStatus;
    }

    /**
     * @return the updateTime
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the browseCount
     */
    public int getBrowseCount() {
        return browseCount;
    }

    /**
     * @param browseCount the browseCount to set
     */
    public void setBrowseCount(int browseCount) {
        this.browseCount = browseCount;
    }

    /**
     * @return the praiseCount
     */
    public int getPraiseCount() {
        return praiseCount;
    }

    /**
     * @param praiseCount the praiseCount to set
     */
    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    /**
     * @return the viedoName
     */
    public String getViedoName() {
        return viedoName;
    }

    /**
     * @param viedoName the viedoName to set
     */
    public void setViedoName(String viedoName) {
        this.viedoName = viedoName;
    }

    /**
     * @return the viedoDescription
     */
    public String getViedoDescription() {
        return viedoDescription;
    }

    /**
     * @param viedoDescription the viedoDescription to set
     */
    public void setViedoDescription(String viedoDescription) {
        this.viedoDescription = viedoDescription;
    }

    /**
     * @return the viedoSize
     */
    public String getViedoSize() {
        return viedoSize;
    }

    /**
     * @param viedoSize the viedoSize to set
     */
    public void setViedoSize(String viedoSize) {
        this.viedoSize = viedoSize;
    }

    /**
     * @return the viedoUrl
     */
    public String getViedoUrl() {
        return viedoUrl;
    }

    /**
     * @param viedoUrl the viedoUrl to set
     */
    public void setViedoUrl(String viedoUrl) {
        this.viedoUrl = viedoUrl;
    }

    /**
     * @return the viedoLength
     */
    public String getViedoLength() {
        return viedoLength;
    }

    /**
     * @param viedoLength the viedoLength to set
     */
    public void setViedoLength(String viedoLength) {
        this.viedoLength = viedoLength;
    }

    /**
     * @return the viedoType
     */
    public int getViedoType() {
        return viedoType;
    }

    /**
     * @param viedoType the viedoType to set
     */
    public void setViedoType(int viedoType) {
        this.viedoType = viedoType;
    }

    /**
     * @return the viedoClassifyId
     */
    public int getViedoClassifyId() {
        return viedoClassifyId;
    }

    /**
     * @param viedoClassifyId the viedoClassifyId to set
     */
    public void setViedoClassifyId(int viedoClassifyId) {
        this.viedoClassifyId = viedoClassifyId;
    }

    /**
     * @return the viedoClassifyName
     */
    public String getViedoClassifyName() {
        return viedoClassifyName;
    }

    /**
     * @param viedoClassifyName the viedoClassifyName to set
     */
    public void setViedoClassifyName(String viedoClassifyName) {
        this.viedoClassifyName = viedoClassifyName;
    }

    /**
     * @return the viedoFormat
     */
    public String getViedoFormat() {
        return viedoFormat;
    }

    /**
     * @param viedoFormat the viedoFormat to set
     */
    public void setViedoFormat(String viedoFormat) {
        this.viedoFormat = viedoFormat;
    }

    /**
     * @return the imgName
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * @param imgName the imgName to set
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    /**
     * @return the imgSize
     */
    public String getImgSize() {
        return imgSize;
    }

    /**
     * @param imgSize the imgSize to set
     */
    public void setImgSize(String imgSize) {
        this.imgSize = imgSize;
    }

    /**
     * @return the imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl the imgUrl to set
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return the imgFormat
     */
    public String getImgFormat() {
        return imgFormat;
    }

    /**
     * @param imgFormat the imgFormat to set
     */
    public void setImgFormat(String imgFormat) {
        this.imgFormat = imgFormat;
    }

    /**
     * @return the createTime
     */
    public String getCreateTime() {
        Date cDate = null;
        try {
            cDate = format.parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format.format(cDate);
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the viedoTitle
     */
    public String getViedoTitle() {
        return viedoTitle;
    }

    /**
     * @param viedoTitle the viedoTitle to set
     */
    public void setViedoTitle(String viedoTitle) {
        this.viedoTitle = viedoTitle;
    }

    /**
     * @return the smallImgUrl
     */
    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    /**
     * @param smallImgUrl the smallImgUrl to set
     */
    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    /**
     * @return the maxImgUrl
     */
    public String getMaxImgUrl() {
        return maxImgUrl;
    }

    /**
     * @param maxImgUrl the maxImgUrl to set
     */
    public void setMaxImgUrl(String maxImgUrl) {
        this.maxImgUrl = maxImgUrl;
    }

    /**
     * @return the codeName
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * @param codeName the codeName to set
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * @return the codeFormat
     */
    public String getCodeFormat() {
        return codeFormat;
    }

    /**
     * @param codeFormat the codeFormat to set
     */
    public void setCodeFormat(String codeFormat) {
        this.codeFormat = codeFormat;
    }

}
