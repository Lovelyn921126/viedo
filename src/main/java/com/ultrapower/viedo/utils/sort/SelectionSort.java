/*
 * FileName: selectionSort.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.sort;

import java.util.Random;

/**
 * <p>
 * Description:   简单选择排序
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2019年5月27日 下午2:36:45          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class SelectionSort {
    // 打印完整序列
    public void printPart(int[] list, int begin, int end) {
        for (int i = 0; i < begin; i++) {
            System.out.print("\t");
        }
        for (int i = begin; i <= end; i++) {
            System.out.print(list[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int max_count = 10;
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(max_count * 10);
        }

        // 初始化一个序列

        // 调用快速排序方法
        SelectionSort quick = new SelectionSort();
        System.out.print("排序前:\t\t");
        quick.printPart(array, 0, array.length - 1);
        quick.selectionSort(array);
        System.out.print("排序后:\t\t");
        quick.printPart(array, 0, array.length - 1);
    }

    public void selectionSort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int index = i;
            int temp = 1;
            for (int j = i + 1; j < list.length; j++) {
                if (list[index] > list[j]) {
                    index = j;

                }

            }
            temp = list[index];
            list[index] = list[i];
            list[i] = temp;
        }
    }
}
