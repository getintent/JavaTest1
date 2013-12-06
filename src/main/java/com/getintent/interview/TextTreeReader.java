package com.getintent.interview;

import java.io.IOException;
import java.io.Reader;

public class TextTreeReader implements TreeReader {
    @Override
    public TreeNode read(Reader r) {
        try {
            return TreeNode.read(r);
        } catch (IOException e) {
            throw new Error(e);
        }
    }
}
