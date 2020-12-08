package me.oktop.javastudy.week4;

public class ListNodeStack {
    private ListNode head;

    public ListNodeStack() {}

    public ListNodeStack(int value) {
        this.head = new ListNode(value);
    }

    public void push(int data) {
        ListNode node = new ListNode(data);
        ListNode temp = head;
        while (temp.getNext() != null)
            temp = temp.getNext();
        temp.next(node);
    }

    public ListNode getHead() {
        return head;
    }

    public int pop() {
        ListNode temp = head;
        ListNode prev = temp;
        while (temp.getNext() != null) {
            prev = temp;
            temp = temp.getNext();
        }
        prev.next(null);
        return temp.getValue();
    }
}
