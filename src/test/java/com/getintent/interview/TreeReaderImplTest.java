package com.getintent.interview;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class TreeReaderImplTest {
    private TreeReader treeReader;

    @Before
    public void setUp() throws Exception {
        treeReader = new TreeReaderImpl();
    }

    @Test
    public void testReadEmptyTree() throws Exception {
        Reader reader = new StringReader("");

        TreeNode root = treeReader.read(reader);

        assertNull("Empty string must be parsed as Null", root);
    }

    @Test
    public void testReadRoot() throws Exception {
        Reader reader = new StringReader("0\n");

        TreeNode expectedRoot = new TreeNode(0);

        TreeNode actualRoot = treeReader.read(reader);

        assertEquals("Error while parsing one element tree", expectedRoot, actualRoot);
    }

    @Test
    public void testReadTreeWriterJavadocExample() throws Exception {
        TreeNode expectedRoot = new TreeNode(6);

        TreeNode treeNode1 = new TreeNode(7);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(4);

        TreeNode treeNode4 = new TreeNode(10);

        TreeNode treeNode5 = new TreeNode(13);
        TreeNode treeNode6 = new TreeNode(16);
        TreeNode treeNode7 = new TreeNode(21);

        treeNode1.addChild(treeNode2);
        treeNode2.addChild(treeNode3);

        treeNode5.addChild(treeNode6);
        treeNode5.addChild(treeNode7);

        expectedRoot.addChild(treeNode1);
        expectedRoot.addChild(treeNode4);
        expectedRoot.addChild(treeNode5);

        final String parseSting =
                "6\n" +
                        "   7\n" +
                        "      3\n" +
                        "         4\n" +
                        "   10\n" +
                        "   13\n" +
                        "      16\n" +
                        "      21\n";

        Reader reader = new StringReader(parseSting);

        TreeNode actualRoot = treeReader.read(reader);

        assertEquals("Error while parsing TreeWriter javadoc example", expectedRoot, actualRoot);
    }

    @Test(expected = TreeFormatException.class)
    public void testReadTreeFormatExceptionOnInvalidNumber() throws Exception {
        Reader reader = new StringReader("asdfdsas");

        treeReader.read(reader);
    }

    @Test(expected = TreeFormatException.class)
    public void testReadTreeFormatExceptionOnInvalidSeparator() throws Exception {
        String exampleString = "9\n" +
                "  0"; // Only 2 spaces

        Reader reader = new StringReader(exampleString);

        treeReader.read(reader);
    }

    @Test(expected = TreeFormatException.class)
    public void testReadTreeFormatExceptionOnTwoRoots() throws Exception {
        String exampleString = "9\n" +
                "9\n";

        Reader reader = new StringReader(exampleString);

        treeReader.read(reader);
    }

    @Test(expected = TreeFormatException.class)
    public void testReadTreeFormatExceptionOnInvalidLevel() throws Exception {
        String exampleString = "9\n" +
                "      9\n"; // Two levels

        Reader reader = new StringReader(exampleString);

        treeReader.read(reader);
    }
}
