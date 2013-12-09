/*
 * Copyright (c) 2013
 * Kozlov Nikita
 */
package com.getintent.interview;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Kozlov Nikita
 */
public class TreeWriterImpl implements TreeWriter {

    private static final String INDENT = "    ";

    private TreeNode treeNode;

    @Override
    public void write(TreeNode node, Writer w) {
        this.treeNode = node;
        if (treeNode != null) {
            try {
                this.write(w, 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Treenode can not be null.");
        }
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private void write(Writer w, int depth) throws IOException {
        for (int i = 0; i < depth; i++) {
            w.append(INDENT);
        }
        w.append(String.valueOf(this.treeNode.getValue()))
                .append("\n");
        w.flush();
        for (TreeNode node : treeNode.getChildren()) {
            treeNode = node;
            write(w, depth + 1);
        }
    }
}
