package me.oktop.javastudy.week4;

public class ListNodeQueue {
    private ListNode head;

    public ListNodeQueue(int value) {
        this.head = new ListNode(value);
    }

    public void push(int value) {
        ListNode node = new ListNode(value);
        ListNode temp = this.head;
        while (temp.getNext() != null)
            temp = temp.getNext();

        temp.next(node);
    }

    public int pop() {
        int value = this.head.getValue();
        this.head = head.getNext();
        return value;
    }

    public ListNode getHead() {
        return head;
    }
}
