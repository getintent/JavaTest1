package com.getintent.interview;

import com.google.common.base.Strings;
import com.google.common.collect.TreeTraverser;

import java.io.IOException;
import java.io.Writer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rindon on 12.12.13.
 */
public class IterativeTreeWriter implements TreeWriter {

    private static final TreeTraverser<TreeNode> ADAPTER = new TreeTraverser<TreeNode>() {
        @Override
        public Iterable<TreeNode> children(TreeNode node) {
            return node.getChildren();
        }
    };

    /**
     * Writes given tree to specified writer. Uses guava {@code TreeTraverser} internally.
     * @param node root of the given tree.
     * @param w writer to write the tree.
     * @throws IOException
     */
    @Override
    public void write(TreeNode node, Writer w) throws IOException {
        checkNotNull(node); // fail fast
        checkNotNull(w);
        for (TreeNode treeNode : ADAPTER.preOrderTraversal(node)) {
            w.append(getOffset(treeNode.getLevel()))
                    .append(String.valueOf(treeNode.getValue()))
                    .append('\n');
        }
        w.flush();
    }

    private static String getOffset(int level) {
        return Strings.repeat("   ", level);
    }
}
