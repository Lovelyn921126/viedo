/*
 * FileName: KMP.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.sort;

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
 * 2019年5月29日 下午4:50:57          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class KMP {
    //前缀后缀最长公共元素长度
    public static int[] getNext(String pattern) {
        int j = 0, k = -1;
        char[] p = pattern.toCharArray();
        int[] next = new int[pattern.length()];
        next[0] = -1;
        while (j < pattern.length() - 1) {
            if (-1 == k || pattern.charAt(j) == pattern.charAt(k)) {
                System.out.println(p);

                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }

        return next;
    }

    public static void main(String[] args) {
        System.out.println(getNext("ABAABAA").toString());

    }
}
