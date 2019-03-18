/*
 * FileName: ServerIntfaceRelation.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import java.io.Serializable;
import java.util.List;

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
 * 2018年6月12日 下午9:28:23          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class ServerIntfaceRelation implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7540519656389355579L;
    /**
     * 主键
     */
    private Integer Id;
    /**
     * 系统iD
     */
    private Integer systemId;
    /**
     * 系统名称与编码整合 用于页面展示 例如 CRM系统（10.1001） 就是有系统名称 CRM系统 与 该系统编码 10.1001 组成
     */
    private String systemNameCode;
    /**
     * 客户端的ID
     */
    private Integer senderId;

    /**
     * 客户端的名称
     */
    private String senderName;
    /**
     * 接口的名称
     */
    private String intfaceName;
    /**
     * 服务的编码
     */
    private String serverCode;
    /**
     * 接口返回值对应 映射
     */
    private List<IntfaceResultRelation> list;

    /**
     * @return the id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        Id = id;
    }

    /**
     * @return the systemId
     */
    public Integer getSystemId() {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    /**
     * @return the systemNameCode
     */
    public String getSystemNameCode() {
        return systemNameCode;
    }

    /**
     * @param systemNameCode the systemNameCode to set
     */
    public void setSystemNameCode(String systemNameCode) {
        this.systemNameCode = systemNameCode;
    }

    /**
     * @return the senderId
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * @return the senderIName
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * @param senderIName the senderIName to set
     */
    public void setSenderName(String senderIName) {
        this.senderName = senderIName;
    }

    /**
     * @return the intfaceName
     */
    public String getIntfaceName() {
        return intfaceName;
    }

    /**
     * @param intfaceName the intfaceName to set
     */
    public void setIntfaceName(String intfaceName) {
        this.intfaceName = intfaceName;
    }

    /**
     * @return the list
     */
    public List<IntfaceResultRelation> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<IntfaceResultRelation> list) {
        this.list = list;
    }

    /**
     * @return the serverCode
     */
    public String getServerCode() {
        return serverCode;
    }

    /**
     * @param serverCode the serverCode to set
     */
    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

}
