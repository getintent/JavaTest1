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
}
