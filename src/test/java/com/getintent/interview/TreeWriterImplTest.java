/*
 * Copyright (c) 2013
 * Kozlov Nikita
 */
package com.getintent.interview;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * @author Kozlov Nikita
 */
public class TreeWriterImplTest {

    private Writer writer;
    private TreeNode rootNode;
    private final static String FILE_PATH_ACTUAL = "src/test/java/tree-out-actual.TXT";
    private final static String FILE_PATH_EXPECTED = "src/test/java/tree-out-expected.TXT";

    @Before
    public void setUp() throws Exception {
        this.rootNode = new TreeNode(6);
        TreeNode treeNode10 = new TreeNode(10);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode13 = new TreeNode(13);
        TreeNode treeNode16 = new TreeNode(16);
        TreeNode treeNode21 = new TreeNode(21);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);

        rootNode.addChild(treeNode7);
        rootNode.addChild(treeNode10);
        rootNode.addChild(treeNode13);
        treeNode13.addChild(treeNode16);
        treeNode13.addChild(treeNode21);
        treeNode7.addChild(treeNode3);
        treeNode3.addChild(treeNode4);
        File file = new File(FILE_PATH_ACTUAL);
        this.writer = new FileWriter(file);
    }

    @After
    public void tearDown() throws Exception {
        if (this.writer != null) {
            this.writer.close();
        }
    }

    @Test
    public void testWrite() throws Exception {
        TreeWriter treeWriter = new TreeWriterImpl();
        treeWriter.write(this.rootNode, this.writer);
        writer.close();
        String expectedCheckSum = this.getCheckSumFile(FILE_PATH_EXPECTED);
        String actualCheckSum = this.getCheckSumFile(FILE_PATH_ACTUAL);
        Assert.assertEquals(expectedCheckSum, actualCheckSum);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private String getCheckSumFile(String filePath) {
        String checksum = null;
        try {
            checksum = DigestUtils.md5Hex(new FileInputStream(filePath));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return checksum;
    }
}
