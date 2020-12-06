package me.oktop.javastudy.week4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListNodeTest {

    @Test
    void create_listnode_test() {
        ListNode head = ListNode.of(1);
        assertThat(head.getValue()).isEqualTo(1);
    }

    @Test
    void add_listnode_test() {
        ListNode head = ListNode.of(1);
        ListNode first = ListNode.of(15);
        ListNode second = ListNode.of(50);
        head.next(first);
        first.next(second);

        ListNode newNode = ListNode.of(30);
//        ListNode.add(head, newNode, 1);
//        validation(head, 1, 30, 15, 50);
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