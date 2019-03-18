/*
 * FileName: KfsSolrConfig.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.constants;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author lishuai
 * @version 1.0

	* @KXY
 * @EMAIL

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2017年9月14日 上午10:03:25          lishuai        1.0         To create
 * </p>
 *
 * @since
 * @see
 */
public class KnowledgeConfigEnum {

    //标记知识分类
    public static enum KnowledgeCategory {
        demand_, document, microcourse
    }

    //标记知识有效性
    public static enum KnowledgeValidity {
        enabled, disabled
    }

    //沃工单知识库扩展--TFS 对接 -- 处理类型
    public static enum KnowledgeExtendHandleType {
        read, backup
    }

    //沃工单知识来源
    public static enum KnowledgeSource {
        wodemand, tfssync
    }
}
