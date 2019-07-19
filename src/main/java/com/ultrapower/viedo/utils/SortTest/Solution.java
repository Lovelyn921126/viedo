package com.ultrapower.viedo.utils.SortTest;

public class Solution {
  public static void main(String[] args) {
	  TreeNode node1 = new TreeNode(1);
      TreeNode node2 = new TreeNode(2);
      TreeNode node3 = new TreeNode(3);
      TreeNode node4 = new TreeNode(4);
      TreeNode node5 = new TreeNode(5);
      TreeNode node6 = new TreeNode(6);
      TreeNode node7 = new TreeNode(7);
      node1.leftNode = node2;
      node1.rightNode = node3;
      node2.leftNode = node4;
      node2.rightNode = node5;
      node3.rightNode = node6;
      node5.leftNode = node7;
      Long begintime = System.nanoTime();
   boolean res=  IsBalanced_Solution(node1);
     Long endtime = System.nanoTime();
     System.out.println("该二叉树是否为平衡二叉树："+res+",运行时间："+(endtime - begintime) + "ns");

}
  public static boolean IsBalanced_Solution(TreeNode node) {
	if (node==null) {
		return true;
	}
	return Math.abs(getDepth(node.leftNode)-getDepth(node.rightNode))<=1&&IsBalanced_Solution(node.leftNode)&&IsBalanced_Solution(node.rightNode);
}
  public static int getDepth(TreeNode node) {
	if (node==null) {
		return 0;
	}
	return Math.max(getDepth(node.leftNode), getDepth(node.leftNode))+1;
}
}
