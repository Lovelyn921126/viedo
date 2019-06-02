/*
 * FileName: HeepSort.java
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
 * 2019年5月27日 下午3:13:49          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class HeepSort {
    public void adjustDownToUp(int[] array, int k, int length) {
        int temp = array[k];
        for (int i = 2 * k + 1; i < length - 1; i = 2 * i + 1) { //i为初始化为节点k的左孩子，沿节点较大的子节点向下调整
            if (i < length && array[i] < array[i + 1]) { //取节点较大的子节点的下标
                i++; //如果节点的右孩子>左孩子，则取右孩子节点的下标
            }
            if (temp >= array[i]) { //根节点 >=左右子女中关键字较大者，调整结束
                break;
            } else { //根节点 <左右子女中关键字较大者
                array[k] = array[i]; //将左右子结点中较大值array[i]调整到双亲节点上
                k = i; //【关键】修改k值，以便继续向下调整
            }
        }
        array[k] = temp; //被调整的结点的值放人最终位置
    }

    public int[] bulidHeep(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            adjustDownToUp(array, i, array.length);
        }
        return array;
    }

    public void heepSort(int[] array) {
        // 循环建立初始堆
        array = bulidHeep(array);
        for (int i = array.length - 1; i > 1; i--) {
            //将 最后一个元素 与堆顶元素对换
            int temp = array[0];

            array[0] = array[i];
            array[i] = temp;
            adjustDownToUp(array, 0, i);

        }

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

    public static void main(String[] args) {
        int max_count = 10;
        int[] arrays = new int[10];

        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = new Random().nextInt(max_count * 10);
        }
        HeepSort heepSort = new HeepSort();
        heepSort.printPart(arrays, 0, arrays.length - 1);
        heepSort.heepSort(arrays);//建立初始堆
        heepSort.printPart(arrays, 0, arrays.length - 1);
    }
}
