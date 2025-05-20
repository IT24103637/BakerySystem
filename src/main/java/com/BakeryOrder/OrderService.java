package lk.sliit.eleven.bakeryorder.services;

import lk.sliit.eleven.bakeryorder.dao.OrderDao;
import lk.sliit.eleven.bakeryorder.model.Order;
import lk.sliit.eleven.bakeryorder.model.OrderItem;
import lk.sliit.eleven.bakeryorder.model.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final OrderDao orderDao = new OrderDao();

    // Basic CRUD Operations
    public String createOrder(String customerName, String contactNumber, LocalDateTime pickupTime,
                              List<OrderItem> items, String specialInstructions) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setContactNumber(contactNumber);
        order.setPickupTime(pickupTime);
        order.setItems(items);
        order.setSpecialInstructions(specialInstructions);

        orderDao.createOrder(order);
        return order.getId();
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public Order getOrderById(String id) {
        return orderDao.getOrderById(id);
    }

    public void updateOrder(Order updatedOrder) {
        orderDao.updateOrder(updatedOrder);
    }

    public void deleteOrder(String id) {
        orderDao.deleteOrder(id);
    }

    // Status Management
    public void updateOrderStatus(String id, OrderStatus status) {
        Order order = orderDao.getOrderById(id);
        if (order != null) {
            order.setStatus(status);
            orderDao.updateOrder(order);
        }
    }

    public void cancelOrder(String id) {
        updateOrderStatus(id, OrderStatus.CANCELLED);
    }

    public void completeOrder(String id) {
        updateOrderStatus(id, OrderStatus.COMPLETED);
    }

    // Queue Management
    public void processNextOrder() {
        Order order = orderDao.getPendingOrders().stream().findFirst().orElse(null);
        if (order != null) {
            updateOrderStatus(order.getId(), OrderStatus.PROCESSING);
        }
    }

    // Statistics for Dashboard
    public int getAllOrdersCount() {
        return orderDao.getAllOrders().size();
    }

    public int getPendingOrdersCount() {
        return orderDao.getPendingOrders().size();
    }

    public Order getCurrentlyProcessingOrder() {
        return orderDao.getAllOrders().stream()
                .filter(o -> o.getStatus() == OrderStatus.PROCESSING)
                .findFirst()
                .orElse(null);
    }

    public int getCompletedTodayCount() {
        return (int) orderDao.getAllOrders().stream()
                .filter(o -> o.getStatus() == OrderStatus.COMPLETED &&
                        o.getOrderDate().toLocalDate().equals(LocalDate.now()))
                .count();
    }

    // Sorting and Filtering
    public List<Order> getSortedOrders(String sortBy, String filterBy) {
        List<Order> orders = new ArrayList<>(orderDao.getAllOrders());

        // Apply filter
        if (filterBy != null && !filterBy.isEmpty()) {
            try {
                OrderStatus status = OrderStatus.valueOf(filterBy);
                orders = orders.stream()
                        .filter(o -> o.getStatus() == status)
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                // Invalid filter value, ignore it
            }
        }

        // Apply sort
        if (sortBy != null) {
            switch (sortBy) {
                case "date":
                    orders.sort(Comparator.comparing(Order::getOrderDate).reversed());
                    break;
                case "date_oldest":
                    orders.sort(Comparator.comparing(Order::getOrderDate));
                    break;
                case "pickup":
                    orders.sort(Comparator.comparing(Order::getPickupTime));
                    break;
                case "pickup_latest":
                    orders.sort(Comparator.comparing(Order::getPickupTime).reversed());
                    break;
                case "status":
                    orders.sort(Comparator.comparing(Order::getStatus));
                    break;
                default:
                    // Default sorting (by order date, newest first)
                    orders.sort(Comparator.comparing(Order::getOrderDate).reversed());
            }
        } else {
            // Default sorting if no sort specified
            orders.sort(Comparator.comparing(Order::getOrderDate).reversed());
        }

        return orders;
    }

    // Pagination support
    public List<Order> getPaginatedOrders(int page, int pageSize, String sortBy, String filterBy) {
        List<Order> sortedOrders = getSortedOrders(sortBy, filterBy);
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, sortedOrders.size());

        if (start > sortedOrders.size()) {
            return new ArrayList<>();
        }

        return sortedOrders.subList(start, end);
    }

    public int getTotalPages(int pageSize, String filterBy) {
        List<Order> filteredOrders;

        if (filterBy != null && !filterBy.isEmpty()) {
            try {
                OrderStatus status = OrderStatus.valueOf(filterBy);
                filteredOrders = orderDao.getAllOrders().stream()
                        .filter(o -> o.getStatus() == status)
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                filteredOrders = orderDao.getAllOrders();
            }
        } else {
            filteredOrders = orderDao.getAllOrders();
        }

        return (int) Math.ceil((double) filteredOrders.size() / pageSize);
    }

    // Bubble Sort implementation for pickup time (as requested)
    public List<Order> getOrdersSortedByPickupTime() {
        List<Order> orders = new ArrayList<>(orderDao.getAllOrders());

        // Bubble Sort implementation
        int n = orders.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (orders.get(j).getPickupTime().isAfter(orders.get(j+1).getPickupTime())) {
                    // Swap orders
                    Order temp = orders.get(j);
                    orders.set(j, orders.get(j+1));
                    orders.set(j+1, temp);
                }
            }
        }

        return orders;
    }

    // Additional utility methods
    public List<Order> getOrdersByCustomer(String customerName) {
        return orderDao.getAllOrders().stream()
                .filter(o -> o.getCustomerName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }

    public List<Order> getTodayOrders() {
        return orderDao.getAllOrders().stream()
                .filter(o -> o.getOrderDate().toLocalDate().equals(LocalDate.now()))
                .collect(Collectors.toList());
    }
}