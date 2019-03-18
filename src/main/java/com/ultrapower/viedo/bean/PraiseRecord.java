/*
 * FileName: PraiseRecord.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * Description:  点赞记录
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2017年11月29日 下午2:46:55          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@ApiModel(value = "PraiseRecord对象", description = "点赞记录")
public class PraiseRecord implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1358798888165533813L;
    @ApiModelProperty(value = "点赞记录ID", name = "id", example = "128")
    private Integer id;
    /**
     * 浏览的用户
     */
    @ApiModelProperty(value = "浏览的用户", name = "userName", example = "hebin")
    private String userName;
    /**
     * 浏览的时间
     */
    @ApiModelProperty(value = "浏览的时间", name = "praiseTime", example = "2017-12-21 23:00:24")
    private Timestamp praiseTime;
    /**
     * 浏览的视频ID
     */
    @ApiModelProperty(value = "浏览的视频ID", name = "viedoId", example = "215")
    private Integer viedoId;
    /**
     * 是否删除（0删除，1正常）
     */
    @ApiModelProperty(value = "是否删除（0删除，1正常）", name = "vaildStatus", example = "1")
    private Integer vaildStatus;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the praiseTime
     */
    public Timestamp getPraiseTime() {
        return praiseTime;
    }

    /**
     * @param praiseTime the praiseTime to set
     */
    public void setPraiseTime(Timestamp praiseTime) {
        this.praiseTime = praiseTime;
    }

    /**
     * @return the viedoId
     */
    public Integer getViedoId() {
        return viedoId;
    }

    /**
     * @param viedoId the viedoId to set
     */
    public void setViedoId(Integer viedoId) {
        this.viedoId = viedoId;
    }

    /**
     * @return the vaildStatus
     */
    public Integer getVaildStatus() {
        return vaildStatus;
    }

    /**
     * @param vaildStatus the vaildStatus to set
     */
    public void setVaildStatus(Integer vaildStatus) {
        this.vaildStatus = vaildStatus;
    }

}
