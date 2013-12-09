package com.getintent.interview;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private int value;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(int value) {
        this.value = value;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}
