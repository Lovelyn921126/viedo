package com.ultrapower.viedo.utils.SortTest;

import static org.hamcrest.CoreMatchers.not;

import java.util.Random;

public class Sort {
  public static void main(String[] args) {
	int[] arr=new int[10];
	Random random=new Random();
	for (int i = 0; i < arr.length; i++) {
		 arr[i]=random.nextInt(100);
		
	}
	System.out.println("排序前");
	print(arr,0,arr.length-1);
	//insert(arr, arr.length, 1);
    shell(arr, arr.length);
	//select(arr);
	//nbubble(arr);
	System.out.println("排序后");
	print(arr,0,arr.length-1);
	
}


private static void shell(int[] arr, int length) {
	for (int gap = length/2; gap>0; gap/=2) {
		for (int i = 0; i < gap; i++) {
			insert(arr, length, gap);
		}
	}
	
}


private static void insert(int[] arr, int length, int gap) {
	for (int i = 0; i < arr.length; i++) {
		int tmp = arr[i];
		int j=i-gap;
		while (j>=0&&tmp<arr[j]) {
			arr[j+gap]=arr[j];
			j-=gap;
		}
		arr[j+gap]=tmp;
	}
	
}


private static void select(int[] arr) {
	
	int index;
	for (int i = 0; i < arr.length; i++) {
		index=i;
		for (int j = i+1; j < arr.length; j++) {
			if (arr[index]>arr[j]) {
				index=j;
			}
		}
		int tmp=arr[i];
		arr[i]=arr[index];
		arr[index]=tmp;
	}
	
}


private static void nbubble(int[] arr) {
	// TODO Auto-generated method stub
	for (int i = 0; i < arr.length; i++) {
		for (int j = arr.length-1; j >i; j--) {
			if (arr[j]<arr[j-1]) {
				int tmp=arr[j];
				arr[j]=arr[j-1];
				arr[j-1]=tmp;
			}
		}
	}
}


public static void print(int[] list,int begin,int end) {
	  for (int i = 0; i < begin; i++) {
          System.out.print("\t");
      }
      for (int i = begin; i <= end; i++) {
          System.out.print(list[i] + "\t");
      }
      System.out.println();
}
  
}
