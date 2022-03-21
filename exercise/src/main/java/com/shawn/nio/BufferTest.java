package com.shawn.nio;

import org.junit.Test;

import java.nio.CharBuffer;

public class BufferTest {

    @Test
    public void test() {
        CharBuffer charBuffer = CharBuffer.allocate(100);
        while(fillBuffer(charBuffer)) {
            charBuffer.flip();
            drainBuffer(charBuffer);
            charBuffer.clear();
        }

    }
    private void drainBuffer(CharBuffer buffer) {
        char[] chars = new char[10];
        while (buffer.hasRemaining()) {
            int length = Math.min(chars.length, buffer.remaining());
            buffer.get(chars, 0, length);
            processData(chars, length);
        }
        System.out.println("");
    }
    private void processData(char[] chars, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(chars[i]);
        }
    }

    private boolean fillBuffer(CharBuffer charBuffer) {
        if (index >= strings.length) {
            return false;
        }
        for(char c : strings[index].toCharArray()) {
            charBuffer.put(c);
        }
        index++;
        return true;
    }

    private int index = 0;
    private String[] strings = {"A random string value","The product of an infinite number of monkeys"};
}
