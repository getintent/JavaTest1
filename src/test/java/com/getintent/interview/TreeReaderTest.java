package com.getintent.interview;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;

public class TreeReaderTest extends TestCase {
    public TreeReaderTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TreeReaderTest.class);
    }

    public void testTree() throws IOException, URISyntaxException {
        URL url = TreeReaderTest.class.getResource("tree.txt");
        File file = new File(url.toURI());

        TreeReader reader = new TextTreeReader();
        TreeNode node = reader.read(new FileReader(file));

        File tmpFile = File.createTempFile("tree", ".tmp");
        tmpFile.deleteOnExit();
        System.out.println(tmpFile);

        TreeWriter writer = new TextTreeWriter();
        writer.write(node, new PrintWriter(tmpFile));

        TreeNode tmpNode = reader.read(new FileReader(tmpFile));
        writer.write(node, new PrintWriter(System.out));
        assertEquals(node, tmpNode);
    }

    public void testConfluentTree() throws IOException, URISyntaxException {
        URL url = TreeReaderTest.class.getResource("confluent.txt");
        File file = new File(url.toURI());

        TreeReader reader = new TextTreeReader();
        TreeNode node = reader.read(new FileReader(file));

        TreeNode cmpRoot = new TreeNode(6);
        TreeNode cmpNode = cmpRoot.addChild(new TreeNode(7));
        cmpNode = cmpNode.addChild(new TreeNode(3));
        cmpNode = cmpNode.addChild(new TreeNode(4));
        cmpNode = cmpNode.addChild(new TreeNode(10));
        cmpNode = cmpNode.addChild(new TreeNode(13));
        cmpNode = cmpNode.addChild(new TreeNode(16));
        cmpNode.addChild(new TreeNode(21));

        TreeWriter writer = new TextTreeWriter();
        writer.write(node, new PrintWriter(System.out));
        writer.write(cmpRoot, new PrintWriter(System.out));
        assertEquals(node, cmpRoot);
    }
}
