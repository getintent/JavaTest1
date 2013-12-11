package com.getintent.interview;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Kraynov M. on 11/12/13.
 */
public class TreeParser {
    int lastLine = 1;

    private static TreeNode parseLine(String line) {
        return new TreeNode(Integer.parseInt(line));
    }

    public TreeNode parse(String s) {
        String[] lines = s.split("\\r?\\n");
        String firstLine = lines[0];
        TreeNode root = parseLine(firstLine);
        parse(root, 0, lines);
        return root;
    }

    private void parse(TreeNode parent, int depth, String[] lines) {
        for (; lastLine < lines.length; ) {
            String nextLine = lines[lastLine];
            int spaceNumber = StringUtils.countMatches(nextLine, " ");
            if (spaceNumber < depth) {
                break;
            }
            String value = nextLine.trim();
            TreeNode node = parseLine(value);
            lastLine++;
            if (parent != null) {
                parent.addChild(node);
            }
            parse(node, spaceNumber + 1, lines);
        }
    }
}
