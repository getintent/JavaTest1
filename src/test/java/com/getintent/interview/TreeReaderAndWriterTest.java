package com.getintent.interview;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class TreeReaderAndWriterTest {

    @Test
    public void testReadThenWrite() throws Exception {
        final String parseSting =
                "6\n" +
                        "   7\n" +
                        "      3\n" +
                        "         4\n" +
                        "   10\n" +
                        "   13\n" +
                        "      16\n" +
                        "      21\n";

        StringReader reader = new StringReader(parseSting);
        TreeReader treeReader = new TreeReaderImpl();
        TreeNode root = treeReader.read(reader);

        StringWriter writer = new StringWriter();
        TreeWriter treeWriter = new TreeWriterImpl();
        treeWriter.write(root, writer);

        assertEquals("Strings before reading should be equal strings after writing", parseSting, writer.getBuffer().toString());
    }

    @Test
    public void testWriteThenRead() throws Exception {
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

        StringWriter writer = new StringWriter();
        TreeWriter treeWriter = new TreeWriterImpl();
        treeWriter.write(expectedRoot, writer);

        StringReader reader = new StringReader(writer.getBuffer().toString());
        TreeReader treeReader = new TreeReaderImpl();
        TreeNode actualRoot = treeReader.read(reader);

        assertEquals("Tree before writing should be equal tree after reading", expectedRoot, actualRoot);
    }
}
