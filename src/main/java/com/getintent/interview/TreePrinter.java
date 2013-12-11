package com.getintent.interview;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

/**
 * Created by Kraynov M. on 11/12/13.
 */
public class TreePrinter implements TreeWriter {

    private static void write(TreeNode node, String spaceNumber, Writer w) throws IOException {
        if (node == null) {
            return;
        }
        w.append(spaceNumber + node.getValue() + "\n");
        for (Iterator<TreeNode> iter = node.getChildren().iterator(); iter.hasNext(); ) {
            write(iter.next(), spaceNumber + " ", w);
        }
    }

    @Override
    public void write(TreeNode node, Writer w) {
        try {
            write(node, "", w);
        } catch (IOException e) {
            System.out.println("Error was occurred while writing tree" + e.getMessage());
        }
    }
}
