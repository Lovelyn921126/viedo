/*
 * FileName: subring.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package viedo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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
 * 2018年5月22日 下午4:08:46          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class subring {
    public static void main(String[] args) {
        String path = "/kfs/public/uploadify/upload/2018-04-23/5add3b8ff1c5b/集中系统合同代理商详情.png;/kfs/public/uploadify/upload/2018-04-23/5add3b90208da/潘村吴征兵合同正式文件.doc;/kfs/public/uploadify/upload/2018-04-23/5add3b9034838/潘村吴征兵签订盖章信息.docx;/kfs/public/uploadify/upload/2018-04-23/5add3b904da9b/潘村吴征兵签订盖章信息.png;";
        String[] paths = path.split(";");
        String fileId = StringUtils.join(paths, "@!@");
        List<String> pathName = new ArrayList<>();
        for (int i = 0; i < paths.length; i++) {

            pathName.add(paths[i].substring(paths[i].lastIndexOf("/") + 1));
        }
        String fileIdName = StringUtils.join(pathName, "@!@");
        path.lastIndexOf("/");
        System.out.println(fileId);
        System.out.println(fileIdName);
    }
}
