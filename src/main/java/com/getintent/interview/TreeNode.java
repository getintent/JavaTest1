package com.getintent.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private static final String INDENT = "   ";
    private static final int WORD_SIZE = 3;

    private int value;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode() {}

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode addChild(TreeNode child) {
        return children.add(child) ? child : null;
    }

    public void write(Writer w, int height) throws IOException {
        for (int i = 0; i < height; ++i) {
            w.append(INDENT);
        }
        w.append(String.valueOf(value));
        w.append('\n');
        w.flush();
        for (TreeNode node : children) {
            node.write(w, height + 1);
        }
    }

    public static TreeNode read(Reader r) throws IOException {
        BufferedReader br = new BufferedReader(r);
        String line;
        if ((line = br.readLine()) == null) {
            return null;
        }

        TreeNode root = new TreeNode();
        List<TreeNode> nodes = new ArrayList<>();
        int previousDepth = readNode(root, line);
        nodes.add(root);

        while ((line = br.readLine()) != null) {
            TreeNode node = new TreeNode();
            int depth = readNode(node, line);
            int diff = depth - previousDepth;
            if (diff <= 0) {
                int i = 0;
                while (i < -diff + 1) {
                    nodes.remove(nodes.size() - 1);
                    ++i;
                }
            }
            previousDepth = depth;

            TreeNode parent = nodes.get(nodes.size() - 1);
            parent.addChild(node);
            nodes.add(node);
        }
        return root;
    }

    private static int readNode(TreeNode node, String line) {
        int depth = 0;
        for (int i = 0; i < line.length(); i += WORD_SIZE) {
            String word = line.substring(i, Math.min(i + WORD_SIZE, line.length()));
            if (INDENT.equals(word)) {
                ++depth;
            } else {
                node.value = Integer.parseInt(word);
            }
        }
        return depth;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TreeNode)) {
            return false;
        }
        return equals((TreeNode) obj);
    }

    private boolean equals(TreeNode node) {
        if (this.value != node.value) {
            return false;
        }
        if (this.children.size() != node.children.size()) {
            return false;
        }
        for (int i = 0; i < this.children.size(); ++i) {
            if (!this.children.get(i).equals(node.children.get(i))) {
                return false;
            }
        }
        return true;
    }
}
