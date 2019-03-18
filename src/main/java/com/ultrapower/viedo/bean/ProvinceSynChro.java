/*
 * FileName: ProvinceSynChro.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ultrapower.viedo.viladate.ValidateParameter;

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
 * 2018年6月26日 下午4:12:43          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@XmlRootElement(name = "processList")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ProvinceSynChro extends ValidateParameter {
    /**
     *
     */
    private static final long serialVersionUID = 8107146225585692409L;

    /**
     * 文件创建时间
     */
    @XmlElement(name = "createDate")
    private String createDate;
    /**
     * 文件的省分编码
     */
    @XmlElement(name = "provinceCode")
    private String provinceCode;
    /**
     *
     */
    @XmlElementWrapper(name = "createProcess")
    @XmlElement(name = "process")
    private List<ProvinceSynchroDemand> createProcess;
    /**
    *
    */
    @XmlElementWrapper(name = "endProcess")
    @XmlElement(name = "process")
    private List<ProvinceSynchroDemand> endProcess;

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the provinceCode
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * @param provinceCode the provinceCode to set
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * @return the createProcess
     */
    public List<ProvinceSynchroDemand> getCreateProcess() {
        return createProcess;
    }

    /**
     * @param createProcess the createProcess to set
     */
    public void setCreateProcess(List<ProvinceSynchroDemand> createProcess) {
        this.createProcess = createProcess;
    }

    /**
     * @return the endProcess
     */
    public List<ProvinceSynchroDemand> getEndProcess() {
        return endProcess;
    }

    /**
     * @param endProcess the endProcess to set
     */
    public void setEndProcess(List<ProvinceSynchroDemand> endProcess) {
        this.endProcess = endProcess;
    }
}
