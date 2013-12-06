package com.getintent.interview;

import java.io.IOException;
import java.io.Writer;

public class TextTreeWriter implements TreeWriter {
    @Override
    public void write(TreeNode node, Writer w) {
        try {
            node.write(w, 0);
        } catch (IOException e) {
            throw new Error(e);
        }
    }
}
