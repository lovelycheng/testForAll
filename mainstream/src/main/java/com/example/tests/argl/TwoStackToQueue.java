package com.example.tests.argl;

import java.util.Stack;

/**
 * @author chengtong
 * @date 2020/7/11 04:00
 */
public class TwoStackToQueue {
    private int flag = 0;
    private Stack<Integer> insert = new Stack<>();
    private Stack<Integer> read = new Stack<>();

    /**
     * Initialize your data structure here.
     */
    public TwoStackToQueue() {

    }

    public void flip(Stack<Integer> src, Stack<Integer> dust) {
        if (src.isEmpty()) {
            return;
        }
        while (!src.isEmpty()) {
            dust.push(src.pop());
        }
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        flip(read, insert);
        insert.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        flip(insert, read);
        return read.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        flip(insert, read);
        return read.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return insert.isEmpty() && read.isEmpty();
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
    public static void main(String[] args) {
        TwoStackToQueue twoStackToQueue = new TwoStackToQueue();
        twoStackToQueue.push(1);
        twoStackToQueue.push(2);
        int x = twoStackToQueue.peek();
        int x1 = twoStackToQueue.pop();

        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);

        int a = myQueue.peek();

        myQueue.push(3);
        int a3 = myQueue.pop();



    }


    public static class MyQueue {

        private Stack<Integer> numStack;
        private Stack<Integer> helpStack;

        /** Initialize your data structure here. */
        public MyQueue() {
            numStack = new Stack<>();
            helpStack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            numStack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            peek();
            return helpStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (helpStack.isEmpty()) {
                while (!numStack.isEmpty()) {
                    helpStack.push(numStack.pop());
                }
            }

            return helpStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return helpStack.isEmpty() && numStack.isEmpty();
        }
    }


}
