package com.getintent.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode {
    private int value;
    private int level;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(int value) {
        this.value = value;
        this.level = 0;
    }

    public void addChild(TreeNode child) {
        child.setLevel(level + 1);
        children.add(child);
    }

    public List<TreeNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public int getValue() {
        return this.value;
    }

    private void setLevel(int level) {
        this.level = level;
        for (TreeNode child : children) {
            child.setLevel(level + 1);
        }
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode treeNode = (TreeNode) o;

        if (level != treeNode.level) return false;
        if (value != treeNode.value) return false;
        if (!children.equals(treeNode.children)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + level;
        result = 31 * result + children.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", level=" + level +
                ", children=" + children +
                '}';
    }
}
