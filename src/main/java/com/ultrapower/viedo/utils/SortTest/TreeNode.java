package com.ultrapower.viedo.utils.SortTest;

public class TreeNode {
 
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public TreeNode getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}
	public TreeNode getRightNode() {
		return rightNode;
	}
	public void setRightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}
	
	private int val;public TreeNode(int val) {
		super();
		this.val = val;
	}

	TreeNode leftNode;
	 TreeNode rightNode;
}
