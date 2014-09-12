package com.company;

public class SimpleStack {
    private int size;
    private int[] data;

    public SimpleStack(int capacity) {
        data = new int[capacity];
    }

    public void push(int value) {
        data[size++] = value;
    }

    public int pop() {
        return data[--size];
    }

    public int length(){
        return size;
    }

    public int get(int index){
        return data[index];
    }

    public String join(String sep) {
        StringBuilder res = new StringBuilder();
        res.append(data[size - 1]);
        for (int i = size - 2; i >= 0; --i)
            res.append(sep).append(data[i]);

        return res.toString();
    }
}
