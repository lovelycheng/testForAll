package com.example.tests;

/**
 * @author chengtong
 * @date 2020/6/27 22:19
 */
public class SkipLinkedList<T extends Comparable<T>> {


    private static class Entry<T extends Comparable<T>> implements Comparable<Entry<T>> {

        T data;

        Entry<T> prev;

        Entry<T> next;

        Entry<T> down;

        Entry(T t) {
            this.data = t;
        }


        @Override
        public int compareTo(Entry<T> tEntry) {
            return data.compareTo(tEntry.data);
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    private final Entry[] heads = new Entry[32];

    private Entry<T> head;

    private Entry<T> tail;

    private int size;

    public void add(T t) {
        // 初始化head
        if (head == null) {
            head = new Entry<>(t);
            tail = head;

            heads[0] = head;
            size++;

            return;
        }

        // 初始化的一个 entry
        Entry<T> entry = new Entry<>(t);

        if (entry.compareTo(head) < 0) {
            head.prev = entry;
            entry.next = head;
            head = entry;
        } else {

            Entry firstLess = findFirstLess(entry, head);

            firstLess.next.prev = entry;
            entry.next = firstLess.next;

            firstLess.next = entry;
            entry.prev = firstLess;

            if (tail == firstLess) {
                tail = entry;
            }

        }

        size++;

        if (size << 1 == 0) {
            if (heads[1] == null) {
                heads[1] = new Entry<>(t);
                heads[1].down = entry;
            } else {
                Entry<T> index = new Entry<>(t);
                index.down = entry;
                if (index.compareTo(heads[1]) < 0) {
                    heads[1].prev = index;
                    index.next = heads[1];
                    heads[1] = index;
                } else {

                    Entry firstLess = findFirstLess(entry, heads[1]);

                    firstLess.next.prev = index;
                    index.next = firstLess.next;

                    firstLess.next = index;
                    index.prev = firstLess;

                }
            }
        }

        if (heads[1] != null) {
            System.err.print("index:");
            for (Entry next = heads[1].next; next != null; next = next.next) {
                System.err.print(" " + next + " ");
            }
            System.err.println(" ");
        }
        if (heads[0] != null) {
            System.err.print("data :");
            for (Entry next = heads[0].next; next != null; next = next.next) {
                System.err.print(" " + next + " ");
            }
            System.err.println(" ");
        }


    }

    private Entry<T> findFirstLess(Entry<T> entry, Entry<T> head) {

        Entry<T> next = head.next;


        while (next != null && entry.compareTo(next) >= 0) {
            if (next.next == null) {
                return next;
            }
            next = next.next;
        }

        return next.prev;
    }


    public static void main(String[] args) {

        SkipLinkedList<Integer> skipLinkedList = new SkipLinkedList<>();

        skipLinkedList.add(5);
        skipLinkedList.add(4);
        skipLinkedList.add(3);
        skipLinkedList.add(7);
        skipLinkedList.add(8);

    }

}
