package com.getintent.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Roman Besolov
 */
public class TreeReaderImpl implements TreeReader {

    private final String LEVEL_SEPARATOR = "   "; // 3 spaces

    @Override
    public TreeNode read(Reader reader) throws IOException, TreeFormatException {
        TreeNode root = null;

        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        if ((line = bufferedReader.readLine()) != null) {
            try {
                root = new TreeNode(Integer.parseInt(line));

                readTree(bufferedReader, root);
            } catch (NumberFormatException e) {
                return throwTreeFormatException(line, e);
            }
        }

        return root;
    }

    private void readTree(BufferedReader reader, TreeNode root) throws IOException, TreeFormatException {
        String line;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);

        int level = 0;

        while ((line = reader.readLine()) != null) {
            if (lineIsValid(line)) {
                int currentLevel = getCurrentLevel(line);

                if(currentLevel == 0 || currentLevel - level > 1) throwTreeFormatException(line, null);

                TreeNode currentNode = parseNode(line);

                if(currentLevel > level){
                    root = stack.pop();
                }else{
                    for (int i = 0; i < level - currentLevel + 2; i++) {
                        root = stack.pop();
                    }
                }

                root.addChild(currentNode);

                stack.push(root);
                stack.push(currentNode);

                level = currentLevel;
            }else {
                throwTreeFormatException(line, null);
            }
        }
    }

    private TreeNode parseNode(String line) {
        String cleanLine = line.replaceAll(getLevelSeparator(), "");
        Integer value = Integer.valueOf(cleanLine);
        return new TreeNode(value);
    }

    private int getCurrentLevel(String line) {
        return line.lastIndexOf(getLevelSeparator())/getLevelSeparator().length() + 1;
    }

    private boolean lineIsValid(String line) {
        return line.matches("^(" + getLevelSeparator() +")+-?[0-9]+$");
    }

    private TreeNode throwTreeFormatException(String line, NumberFormatException e) throws TreeFormatException {
        throw new TreeFormatException("Wrong format of input data. Wrong string format:'" + line +"'", e);
    }

    protected String getLevelSeparator() {
        return LEVEL_SEPARATOR;
    }
}
