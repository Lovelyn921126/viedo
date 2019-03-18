/*
 * FileName: SearchContent.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 2017年12月7日 下午3:43:26          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@ApiModel(value = "SearchContent对象", description = "搜索实体")
public class SearchContent {
    /**
     * 視頻 类型
     */
    @ApiModelProperty(name = "視頻 类型", value = "viedoType", example = "1")
    private String viedoType;
    /**
     * 视频分类
     */
    @ApiModelProperty(name = " 视频分类", value = "viedoClassifId", example = "1")
    private String viedoClassifId;
    /**
     * 搜索的开始时间
     */
    @ApiModelProperty(name = " 搜索的开始时间", value = "startTime", example = "2017-12-21 23:00:24")
    private String startTime;
    /**
     * 搜索的结束时间
     */
    @ApiModelProperty(name = " 搜索的结束时间", value = "endTime", example = "2017-12-21 23:00:24")
    private String endTime;
    /**
     * 视频的用户
     */
    @ApiModelProperty(name = "视频的用户", value = "userNick", example = "系统")
    private String userNick;
    /**
     * 搜索的关键字
     */
    @ApiModelProperty(name = "搜索的关键字", value = "KeyWord", example = "test")
    private String KeyWord;
    /**
     * 分页 数据 页大小
     */
    @ApiModelProperty(name = "分页 数据 页大小", value = "pageSize", example = "10")
    private Integer pageSize;
    /**
     * 分页 数据 页码
     */
    @ApiModelProperty(name = "分页 数据 页码", value = "pageNum", example = "1")
    private Integer pageNum;
    /**
     *  创建时间(如果有值1,则倒序;如果有值2,则正序)
     */
    @ApiModelProperty(name = "创建时间(如果有值1,则倒序;如果有值2,则正序)", value = "createTimeOrder", example = "1")
    private int createTimeOrder;
    /**
     * 点击量(如果有值1,则倒序;如果有值2,则正序)
     */
    @ApiModelProperty(name = "点击量(如果有值1,则倒序;如果有值2,则正序)", value = "viewerCountOrder", example = "1")
    private int viewerCountOrder;
    /**
     * 点赞数(如果有值1,则倒序;如果有值2,则正序)
     */
    @ApiModelProperty(name = "点赞数(如果有值1,则倒序;如果有值2,则正序)", value = "pariseCountOrder", example = "1")
    private int pariseCountOrder;

    /**
     * @return the viedoType
     */
    public String getViedoType() {
        return viedoType;
    }

    /**
     * @param viedoType the viedoType to set
     */
    public void setViedoType(String viedoType) {
        this.viedoType = viedoType;
    }

    /**
     * @return the viedoClassifId
     */
    public String getViedoClassifId() {
        return viedoClassifId;
    }

    /**
     * @param viedoClassifId the viedoClassifId to set
     */
    public void setViedoClassifId(String viedoClassifId) {
        this.viedoClassifId = viedoClassifId;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the userName
     */
    public String getUserNick() {
        return userNick;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    /**
     * @return the keyWord
     */
    public String getKeyWord() {
        return KeyWord;
    }

    /**
     * @param keyWord the keyWord to set
     */
    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum the pageNum to set
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return the createTimeOrder
     */
    public int getCreateTimeOrder() {
        return createTimeOrder;
    }

    /**
     * @param createTimeOrder the createTimeOrder to set
     */
    public void setCreateTimeOrder(int createTimeOrder) {
        this.createTimeOrder = createTimeOrder;
    }

    /**
     * @return the viewerCountOrder
     */
    public int getViewerCountOrder() {
        return viewerCountOrder;
    }

    /**
     * @param viewerCountOrder the viewerCountOrder to set
     */
    public void setViewerCountOrder(int viewerCountOrder) {
        this.viewerCountOrder = viewerCountOrder;
    }

    /**
     * @return the pariseCountOrder
     */
    public int getPariseCountOrder() {
        return pariseCountOrder;
    }

    /**
     * @param pariseCountOrder the pariseCountOrder to set
     */
    public void setPariseCountOrder(int pariseCountOrder) {
        this.pariseCountOrder = pariseCountOrder;
    }

}
