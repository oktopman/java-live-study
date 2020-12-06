package me.oktop.javastudy.week4;

/*
LinkedList에 대해 공부하세요.
정수를 저장하는 ListNode 클래스를 구현하세요.
ListNode add(ListNode head, ListNode nodeToAdd, int position)를 구현하세요.
ListNode remove(ListNode head, int positionToRemove)를 구현하세요.
boolean contains(ListNode head, ListNode nodeTocheck)를 구현하세요.
*/

public class ListNode {

    private int value;
    private  ListNode next;

    private ListNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ListNode of(int value) {
        return new ListNode(value);
    }

    public void next(ListNode node) {
        this.next = node;
    }

    public ListNode getNext() {
        return next;
    }

    public static void add(ListNode temp, ListNode nodeToAdd, int position) {
        if (position < 1) {
            throw new IllegalArgumentException("position 값이 1보다 작습니다.");
        }
        for (int i = 0; i < position - 1; i++) {
            temp = temp.next;
        }
        nodeToAdd.next = temp.next;
        temp.next = nodeToAdd;
    }

}
