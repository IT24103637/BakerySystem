package lk.sliit.eleven.bakeryorder.dao;

import lk.sliit.eleven.bakeryorder.model.Order;
import lk.sliit.eleven.bakeryorder.model.OrderStatus;

import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private static OrderQueue instance;
    private Queue<Order> queue;

    private OrderQueue() {
        queue = new LinkedList<>();
    }

    public static synchronized OrderQueue getInstance() {
        if (instance == null) {
            instance = new OrderQueue();
        }
        return instance;
    }

    public synchronized void enqueue(Order order) {
        queue.add(order);
    }

    public synchronized Order dequeue() {
        return queue.poll();
    }

    public synchronized Order peek() {
        return queue.peek();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized void processNextOrder() {
        Order order = dequeue();
        if (order != null) {
            order.setStatus(OrderStatus.PROCESSING);
            // Here you would typically send to bakery processing
            // For simulation, we'll just print
            System.out.println("Processing order: " + order.getId());
        }
    }
}