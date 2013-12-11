package com.getintent.interview;

import java.io.IOException;
import java.io.Writer;

/**
 * Prints a tree to given writer in "indentation form". E.g.
 * tree like: https://drive.google.com/file/d/0B_-3alBCl-MpQlBFUDJmZlhxaTQ/edit?usp=sharing
 * Will become:
 *
 * 6
 *    7
 *       3
 *          4
 *    10
 *    13
 *       16
 *       21
 *
 * In other words, we just lest children of each nodes
 * in a separate lines with tree space indentation relative parent node
 */
public interface TreeWriter {
    public void write(TreeNode node, Writer w) throws IOException;
}
