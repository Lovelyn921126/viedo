/*
 * FileName: fastSort.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.sort;

import java.util.Random;

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
 * 2019年5月27日 下午2:00:16          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class fastSort {
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
        fastSort quick = new fastSort();
        System.out.print("排序前:\t\t");
        quick.printPart(array, 0, array.length - 1);
        quick.quickSort(array, 0, array.length - 1);
        System.out.print("排序后:\t\t");
        quick.printPart(array, 0, array.length - 1);
    }

    private void quickSort(int[] list, int left, int right) {
        if (left < right) {
            int base = division(list, left, right);
            System.out.format("base = %d:\t", left);
            printPart(list, left, right);
            // 对“基准标号“左侧的一组数值进行递归的切割，以至于将这些数值完整的排序
            // quickSort(list, left, base - 1);

            // 对“基准标号“右侧的一组数值进行递归的切割，以至于将这些数值完整的排序
            // quickSort(list, base + 1, right);
        }

    }

    /**
     * @param array  快速排序
     */
    private int division(int[] array, int begin, int end) {
        int left = begin;
        int right = end;
        int base = array[begin];
        while (left < right) {
            while (left < right && array[right] >= base) {
                right--;
            }

            array[left] = array[right];

            while (left < right && array[left] <= base) {
                left++;
            }

            array[right] = array[left];

        }
        if (left > begin)
            quickSort(array, begin, left - 1);
        if (right < end)
            quickSort(array, right + 1, end);

        array[left] = base;

        return left;

    }
}
