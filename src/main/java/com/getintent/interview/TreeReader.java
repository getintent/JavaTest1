package com.getintent.interview;

import java.io.IOException;
import java.io.Reader;

/**
 * Reads a tree from a given writer in "indentation form". E.g.
 * tree like: https://drive.google.com/file/d/0B_-3alBCl-MpQlBFUDJmZlhxaTQ/edit?usp=sharing
 * should be read from input:
 *
 * 6
 *    7
 *       3
 *          4
 *    10
 *    13
 *       16
 *       21
 */
public interface TreeReader {
    public TreeNode read(Reader reader) throws IOException;
}
