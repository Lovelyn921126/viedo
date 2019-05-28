/*
 * FileName: MergeSort.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.sort;

import java.util.Random;

/**
 * <p>
 * Description:   归并排序
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2019年5月28日 上午9:55:44          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class MergeSort {
    public void merge(int[] array, int start, int end, int mid) {
        int[] tmp = new int[end - start + 1];
        int i = start; // 第1个有序区的索引
        int j = mid + 1; // 第2个有序区的索引
        int k = 0; // 临时区域的索引
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }
        // 若第一段序列还没扫描完，将其全部复制到合并序列
        while (i <= mid) {
            tmp[k++] = array[i++];
        }

        // 若第二段序列还没扫描完，将其全部复制到合并序列
        while (j <= end) {
            tmp[k++] = array[j++];

        }
        for (i = 0; i < k; i++) {
            array[start + i] = tmp[i];
        }

    }

    public void mergeSort(int a[], int start, int end) {
        if (a == null || start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(a, start, mid);
        mergeSort(a, mid + 1, end);
        merge(a, start, end, mid);

    }

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

    public void MergePass(int[] array, int gap, int length) {

        int i = 0;
        // 归并gap长度的两个相邻子表
        // 不需要对每个子表进行排序 是因为  步长是从   1 开始的   此时就是 有序的  之后每次 进行归并都会在此基础上进行 排序  所以不需要 对子表在进行排序
        for (i = 0; i + 2 * gap - 1 < length; i += 2 * gap) {
            //判断条件为 要归并的的子表 长度要小于 总的长度； i + 2 * gap - 1 即要归并的子表的最后的顺序码
            //  i += 2 * gap 每次循环完成之后 想i 指向 下一对 要归并的子表的 开头
            merge(array, i, i + 2 * gap - 1, i + gap - 1);
        }
        // 余下两个子表，后者长度小于gap
        //此时的 步长 为2 的倍数 若 总长度为 奇数 则会少一个 则测试 在进行一次总的归并
        if (i + gap - 1 < length) {
            merge(array, i, length - 1, i + gap - 1);
        }
    }

    public int[] sort(int[] list) {

        for (int gap = 1; gap < list.length; gap = 2 * gap) {
            MergePass(list, gap, list.length);
            System.out.print("gap = " + gap + ":\t");
            this.printPart(list, 0, list.length - 1);

        }

        return list;

    }

    public static void main(String[] args) {
        int max_count = 10;
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(max_count * 10);
        }

        // 初始化一个序列

        // 调用快速排序方法
        MergeSort quick = new MergeSort();
        System.out.print("排序前:\t\t");
        quick.printPart(array, 0, array.length - 1);
        quick.sort(array);
        System.out.print("排序后:\t\t");
        quick.printPart(array, 0, array.length - 1);
    }
}
