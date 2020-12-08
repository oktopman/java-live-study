package me.oktop.javastudy.week4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListNodeStackTest {

    @Test
    void push_test() {
        ListNodeStack stack = new ListNodeStack(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.getHead().getNext().getValue()).isEqualTo(2);
        assertThat(stack.getHead().getNext().getNext().getValue()).isEqualTo(3);
    }

    @Test
    void pop_test() {
        ListNodeStack stack = new ListNodeStack(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.pop()).isEqualTo(3);
        assertThat(stack.pop()).isEqualTo(2);
    }

}