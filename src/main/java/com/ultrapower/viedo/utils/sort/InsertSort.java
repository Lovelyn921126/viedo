/*
 * FileName: InsertSort.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.sort;

import java.util.Random;

/**
 * <p>
 * Description:  直接插入排序
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2019年5月24日 上午10:55:37          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class InsertSort {

    public static void insertSort(int[] list) {
        // 打印第一个元素
        System.out.format("i = %d:\t", 0);
        parint(list, 0, 0);

        // 第1个数肯定是有序的，从第2个数开始遍历，依次插入有序序列
        for (int i = 1; i < list.length; i++) {

            int temp = list[i]; // 取出第i个数，和前i-1个数比较后，插入合适位置

            // 因为前i-1个数都是从小到大的有序序列，所以只要当前比较的数(list[j])比temp大，就把这个数后移一位
            for (int j = i - 1; j >= 0; j--) {
                if (temp < list[j]) {
                    list[j + 1] = list[j];
                    list[j] = temp;
                }

            }

            System.out.format("i = %d:\t", i);
            parint(list, 0, i);
        }
    }

    public static void parint(int[] arrays, int begin, int end) {
        for (int i = 0; i < begin; i++) {
            System.out.print("\t");
        }
        for (int i = begin; i <= end; i++) {
            System.out.print(arrays[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int max_count = 10;
        int[] arrays = new int[10];
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = new Random().nextInt(max_count * 10);
        }
        parint(arrays, 0, arrays.length - 1);
        insertSort(arrays);
        parint(arrays, 0, arrays.length - 1);
    }
}
