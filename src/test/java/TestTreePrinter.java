import com.getintent.interview.TreeNode;
import com.getintent.interview.TreePrinter;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by Kraynov M. on 11/12/13.
 */
public class TestTreePrinter {
    private static String tree1 = "6\n" +
            " 7\n" +
            "  3\n" +
            "   4\n" +
            " 10\n" +
            " 13\n" +
            "  16\n" +
            "  21\n";
    private TreeNode treeNode1;

    @Before
    public void init() {
        treeNode1 = new TreeNode(6).addChild(new TreeNode(7).addChild(new TreeNode(3).addChild(new TreeNode(4))))
                .addChild(new TreeNode(10))
                .addChild(new TreeNode(13).addChild(new TreeNode(16)).addChild(new TreeNode(21)));
    }

    @Test
    public void testWrite() {
        TreePrinter printer = new TreePrinter();
        StringWriter writer = new StringWriter();
        printer.write(treeNode1, writer);
        Assert.assertEquals("Writer is not correct!", tree1, writer.toString());
    }
}
