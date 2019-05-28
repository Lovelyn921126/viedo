/*
 * FileName: RadixSort.java
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
 * 2019年5月28日 下午4:01:43          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class RadixSort {
    public static int getDigit(int x, int d) {
        int a[] = { 1, 1, 10, 100 };
        System.out.println(x / a[d]);
        return (x / a[d]) % 10;
    }

    public void radixSort(int[] list, int begin, int end, int digit) {
        if (begin >= end) {
            return;
        }
        final int radix = 10;

        int count[] = new int[radix];
        int bucket[] = new int[end - begin + 1];
        //遍历位数  即 数组中 最大为几位数 就遍历几次
        for (int d = 0; d <= digit; d++) {
            for (int i = 0; i < radix; i++) {
                count[i] = 0;
            }
            // 统计各个桶将要装入的数据个数
            for (int i = 0; i < list.length; i++) {
                int j = getDigit(list[i], d);
                count[j]++;
            }
            // // count[i]表示第i个桶的右边界索引
            for (int i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 将数据依次装入桶中
            // 这里要从右向左扫描，保证排序稳定性
            for (int i = end; i >= begin; i--) {
                int j = getDigit(list[i], d); // 求出关键码的第k位的数字， 例如：576的第3位是5
                bucket[count[j] - 1] = list[i]; // 放入对应的桶中，count[j]-1是第j个桶的右边界索引
                count[j]--; // 对应桶的装入数据索引减一
            }

            // 将已分配好的桶中数据再倒出来，此时已是对应当前位数有序的表
            for (int i = begin, j = 0; i <= end; i++, j++) {
                list[i] = bucket[j];
            }
        }
    }

    public int[] sort(int[] list) {
        radixSort(list, 0, list.length - 1, 3);
        return list;
    }

    // 打印完整序列
    public void printAll(int[] list) {
        for (int value : list) {
            System.out.print(value + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = { 50, 123, 543, 187, 49, 30, 0, 2, 11, 100 };
        RadixSort radix = new RadixSort();
        System.out.print("排序前:\t\t");
        radix.printAll(array);
        radix.sort(array);
        System.out.print("排序后:\t\t");
        radix.printAll(array);
    }
}
