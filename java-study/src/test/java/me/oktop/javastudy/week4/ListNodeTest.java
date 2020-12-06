package me.oktop.javastudy.week4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListNodeTest {

    private ListNode head;
    private ListNode first;
    private ListNode second;

    @BeforeEach
    void setUp() {
        this.head = ListNode.of(1);
        this.first = ListNode.of(15);
        this.second = ListNode.of(50);
        head.next(first);
        first.next(second);
    }

    @Test
    void add_listnode_test() {
        ListNode newNode = ListNode.of(30);
        ListNode.add(head, newNode, 2);
        validation(head, 1, 15, 30, 50);
    }

    @Test
    void remove_listnode_test() {
        ListNode.remove(head, 1);
        validation(head, 1, 50);
    }

    @Test
    void contains_listnode_test() {
        ListNode nodeToCheck = ListNode.of(50);
        boolean isContain = ListNode.contains(head, nodeToCheck);
        assertTrue(isContain);
    }

    @Test
    void add_head_test() {
        ListNode newNode = ListNode.of(30);
        newNode.next(head);
        validation(newNode, 30, 1, 15, 50);
    }

    void validation(ListNode node, int... value) {
        for (int i = 0; i < value.length; i++) {
            assertThat(node.getValue()).isEqualTo(value[i]);
            node = node.getNext();
        }
    }

}