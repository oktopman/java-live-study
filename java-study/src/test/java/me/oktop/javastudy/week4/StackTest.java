package me.oktop.javastudy.week4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StackTest {

    @Test
    void push_test() {
        //given
        Stack stack = new Stack();

        //when
        stack.push(1);

        //then
        assertThat(stack.getStack()[0]).isEqualTo(1);
    }

    @Test
    void pop_test() {
        //given
        Stack stack = new Stack();
        stack.push(1);
        stack.push(3);
        stack.push(2);
        stack.push(9);
        //when
        int pop = stack.pop();
        //then
        assertThat(pop).isEqualTo(9);
    }
}