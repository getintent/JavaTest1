package com.getintent.interview;

import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Roman Besolov
 */
public class TreeWriterImpl implements TreeWriter {

    private final String LEVEL_SEPARATOR = "   "; // 3 spaces

    @Override
    public void write(TreeNode node, Writer w) {
        try {
            writeRecursive(node, w, 0);
        } catch (IOException e) {
            // Can't do anything...
            System.err.println("Error occurred during writing tree:" + e.getMessage());
        }
    }

    private void writeRecursive(TreeNode root, Writer writer, int level) throws IOException {
        if(root == null) return;

        printNodeAtLevel(root, writer, level);

        for (TreeNode treeNode : root.getChildren()) {
            writeRecursive(treeNode, writer, level + 1);
        }
    }

    protected void printNodeAtLevel(TreeNode node, Writer writer, int level) throws IOException {
        for (int i = 0; i < level; i++) {
            writer.append(getLevelSeparator());
        }

        writer.write(String.valueOf(node.getValue()));

        writer.write('\n');
    }

    protected String getLevelSeparator() {
        return LEVEL_SEPARATOR;
    }
}
