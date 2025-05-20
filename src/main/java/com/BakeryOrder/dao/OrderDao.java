package lk.sliit.eleven.bakeryorder.dao;

import lk.sliit.eleven.bakeryorder.model.Order;
import lk.sliit.eleven.bakeryorder.model.OrderStatus;
import lk.sliit.eleven.bakeryorder.util.FileStorageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDao {
    private final OrderQueue orderQueue = OrderQueue.getInstance();

    private final FileStorageUtil<Order> fileStorageUtil = new FileStorageUtil<Order>("order.json", Order.class);

    public void createOrder(Order order) {
        fileStorageUtil.add(order);
        orderQueue.enqueue(order);
    }

    public List<Order> getAllOrders() {
        return fileStorageUtil.getAll();
    }

    public Order getOrderById(String id) {
        return getAllOrders().stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateOrder(Order updatedOrder) {
        fileStorageUtil.update(o -> o.getId().equals(updatedOrder.getId()), updatedOrder);
    }

    public void deleteOrder(String id) {
        fileStorageUtil.delete(o -> o.getId().equals(id));
    }

    public List<Order> getPendingOrders() {
        return getAllOrders().stream()
                .filter(order -> order.getStatus() == OrderStatus.PENDING)
                .collect(Collectors.toList());
    }
}