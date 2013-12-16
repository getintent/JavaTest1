package com.getintent.interview;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private int value;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(int value) {
        this.value = value;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public int getValue() {
        return value;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode treeNode = (TreeNode) o;

        return value == treeNode.value && children.equals(treeNode.children);

    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + children.hashCode();
        return result;
    }
}
