import com.getintent.interview.TreeNode;
import com.getintent.interview.TreeParser;
import com.getintent.interview.TreePrinter;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Kraynov M. on 11/12/13.
 */
@RunWith(value = Parameterized.class)
public class TestTreeParserAndWriter {
    private static String tree1 = "6\n" +
            " 7\n" +
            "  3\n" +
            "   4\n" +
            " 10\n" +
            " 13\n" +
            "  16\n" +
            "  21\n";
    private static String tree2 = "6\n" +
            " 7\n" +
            " 8\n";
    private static String tree3 = "6\n" +
            " 7\n" +
            " 8\n" +
            "  9\n" +
            " 10\n";
    private String treeString;

    public TestTreeParserAndWriter(String treeString) {
        this.treeString = treeString;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{tree1}, {tree2}, {tree3}};
        return Arrays.asList(data);
    }

    @Test
    public void testParseAndWrite() {
        TreeParser parser = new TreeParser();
        TreeNode parsedTree = parser.parse(treeString);
        TreePrinter printer = new TreePrinter();
        StringWriter writer = new StringWriter();
        printer.write(parsedTree, writer);
        Assert.assertEquals("Parser or Writer is not correct!", treeString, writer.toString());
    }
}
