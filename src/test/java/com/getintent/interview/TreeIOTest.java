package com.getintent.interview;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by rindon on 11.12.13.
 */
public class TreeIOTest {

    private TreeReader treeReader;
    private TreeWriter treeWriter;
    public static final String INDENTED_TREE = "6\n" +
            "   7\n" +
            "      3\n" +
            "         4\n" +
            "   10\n" +
            "   13\n" +
            "      16\n" +
            "      21\n";

    @Before
    public void setUp() throws Exception {
        treeReader = new StackBasedTreeReader();
        treeWriter = new IterativeTreeWriter();
    }

    @Test
    public void shouldReadOneNodeTree() throws Exception {
        String input = "6";
        StringReader sr = new StringReader(input);
        TreeNode node = treeReader.read(sr);
        assertThat(node.getLevel(), is(0));
        assertThat(node.getValue(), is(6));
        assertThat(node.getChildren(), is(empty()));
    }

    @Test
    public void shouldWriteOneNodeTree() throws Exception {
        StringWriter sw = new StringWriter();
        treeWriter.write(new TreeNode(4), sw);
        assertThat(sw.toString(), is(equalTo("4\n")));
    }

    //        6
    //       /|\
    //      / | \
    //     7  10 13
    //    /      /\
    //   3      16 21
    //  /
    // 4
    private static final TreeNode four = createTree(4);
    private static final TreeNode sixteen = createTree(16);
    private static final TreeNode jackpot = createTree(21);
    private static final TreeNode ten = createTree(10);
    private static final TreeNode three = createTree(3, four);
    private static final TreeNode damned = createTree(13, sixteen, jackpot);
    private static final TreeNode seven = createTree(7, three);
    private static final TreeNode six = createTree(6, seven, ten, damned);

    @Test
    public void shouldReadMultiLevelTree() throws Exception {
        StringReader sr = new StringReader(INDENTED_TREE);
        TreeNode node = treeReader.read(sr);
        assertThat(node, is(equalTo(six)));
    }

    @Test
    public void shouldWriteMultiNodeTree() throws Exception {
        StringWriter sw = new StringWriter();
        treeWriter.write(six, sw);
        assertThat(sw.toString(), is(equalTo(INDENTED_TREE)));
    }

    @Test
    public void shouldWriteMultiNodeTreeAndReadItBack() throws Exception {
        StringWriter sw = new StringWriter();
        treeWriter.write(six, sw);
        TreeNode read = treeReader.read(new StringReader(sw.toString()));
        assertThat(read, is(equalTo(six)));
    }

    private static TreeNode createTree(int value, TreeNode... children) {
        TreeNode root = new TreeNode(value);
        for (TreeNode child : children) {
            root.addChild(child);
        }
        return root;
    }
}
