package com.getintent.interview;

import com.google.common.base.CharMatcher;
import com.google.common.io.LineReader;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by rindon on 10.12.13.
 */
public class StackBasedTreeReader implements TreeReader {

    /**
     * Reads tree from given reader using explicit stack.
     * Closing the reader and maintaining correct input structure is the caller's responsibility.
     * @param reader reader to read from.
     * @return root of the read tree
     * @throws IOException
     */
    @Override
    public TreeNode read(Reader reader) throws IOException {
        LineReader lineReader =  new LineReader(reader);
        Deque<TreeNode> stack = new ArrayDeque<>();
        String line = lineReader.readLine();
        TreeNode root = getNode(line);
        stack.addLast(root);
        while((line = lineReader.readLine()) != null) {
            int currentLevel = getLevel(line);
            TreeNode currentNode = getNode(line);
            if (currentLevel > stack.getLast().getLevel()) {
                stack.getLast().addChild(currentNode);
                stack.addLast(currentNode);
            } else {
                while(stack.removeLast().getLevel() != currentLevel) {}
                stack.getLast().addChild(currentNode);
                stack.addLast(currentNode);
            }
        }
        return root;
    }

    private static TreeNode getNode(String line) {
        int value = Integer.valueOf(line.trim());
        return new TreeNode(value);
    }

    private static int getLevel(String line) {
        return CharMatcher.WHITESPACE.countIn(line)/3;
    }
}
