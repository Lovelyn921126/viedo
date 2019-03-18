/*
 * FileName: ViedoRecord.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

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
 * 2017年12月19日 上午9:37:35          dell        4.1         To create
 * </p>
 *
 * @since 
 * @see     
 */
public class ViedoRecord extends ViedoDetial {

    /**
     * 
     */
    private static final long serialVersionUID = -2167636698628510420L;
    /**
     * 視頻ID
     */
    private Integer viedoId;

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
}
