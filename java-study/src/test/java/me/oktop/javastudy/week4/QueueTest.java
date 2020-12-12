package me.oktop.javastudy.week4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueueTest {

    Queue queue;

    @BeforeEach
    void setUp() {
        int size = 4;
        this.queue = new Queue(size);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
    }

    @Test
    void push_test() {
        assertThat(queue.getQueue()[0]).isEqualTo(1);
        assertThat(queue.getQueue()[1]).isEqualTo(2);
        assertThat(queue.getQueue()[2]).isEqualTo(3);
        assertThat(queue.getQueue()[3]).isEqualTo(4);
    }

    @Test
    void queue_full_test() {
        assertThat(queue.isFull()).isTrue();
    }

    @Test
    void pop_test() {
        assertThat(queue.pop()).isEqualTo(1);
        assertThat(queue.pop()).isEqualTo(2);
        assertThat(queue.pop()).isEqualTo(3);
        assertThat(queue.pop()).isEqualTo(4);
    }

}