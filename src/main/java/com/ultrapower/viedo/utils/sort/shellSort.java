/*
 * FileName: shellSort.java
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
 * 2019年5月24日 下午3:31:25          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class shellSort {
    public void shellSort(int[] list) {

        int gap;
        int lenth = list.length;
        for (gap = lenth / 2; gap > 0; gap /= 2) {
            for (int i = gap; i > 0; gap--) {
                for (int j = i + gap; j < list.length; j += gap) {
                    int temp = list[j];
                    int k = j - gap;
                    while (list[j] < list[k] && k > 0) {
                        list[j] = list[k];
                        k -= gap;
                    }
                    list[k + gap] = temp;
                }

            }

        }
    }

    // 打印完整序列
    public void printAll(int[] list) {
        for (int value : list) {
            System.out.print(value + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = { 9, 1, 2, 5, 7, 4, 8, 6, 3, 5, 0 };

        // 调用希尔排序方法
        shellSort shell = new shellSort();
        System.out.print("排序前:\t\t");
        shell.printAll(array);
        shell.shellSort(array);
        System.out.print("排序后:\t\t");
        shell.printAll(array);
    }

}
