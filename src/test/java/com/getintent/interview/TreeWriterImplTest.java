package com.getintent.interview;

import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * @author Roman Besolov
 */
public class TreeWriterImplTest {

    private TreeWriter treeWriter;
    private StringWriter writer;

    @Before
    public void tearUp() {
        treeWriter = new TreeWriterImpl();
        writer = new StringWriter();
    }

    @Test
    public void testWriteEmptyTree(){
        treeWriter.write(null, writer);

        assertEquals("Printing empty tree should return empty string", "", writer.getBuffer().toString());
    }

    @Test
    public void testWriteOneElement(){
        TreeNode root = new TreeNode(0);

        treeWriter.write(root, writer);

        assertEquals("Printing tree with one element should print element value", "0\n", writer.getBuffer().toString());
    }

    @Test
    public void testWriteTreeWriterJavadocExample() throws Exception {
        TreeNode root = new TreeNode(6);

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

        root.addChild(treeNode1);
        root.addChild(treeNode4);
        root.addChild(treeNode5);


        treeWriter.write(root, writer);

        final String expectedString =
                "6\n" +
                "   7\n" +
                "      3\n" +
                "         4\n" +
                "   10\n" +
                "   13\n" +
                "      16\n" +
                "      21\n";

        assertEquals("JavaDoc example fails", expectedString, writer.getBuffer().toString());
    }
}
