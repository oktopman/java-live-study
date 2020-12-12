package me.oktop.javastudy.week4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListNodeQueueTest {
    ListNodeQueue listNodeQueue;

    @BeforeEach
    void setUp() {
        listNodeQueue = new ListNodeQueue(1);
        listNodeQueue.push(2);
    }

    @Test
    void push_test() {
        assertThat(listNodeQueue.getHead().getValue()).isEqualTo(1);
        assertThat(listNodeQueue.getHead().getNext().getValue()).isEqualTo(2);
    }

    @Test
    void pop_test() {
        assertThat(listNodeQueue.pop()).isEqualTo(1);
        assertThat(listNodeQueue.pop()).isEqualTo(2);
    }

}