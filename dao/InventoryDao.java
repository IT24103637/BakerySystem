package com.BakeryOrder.dao;

import com.BakeryOrder.model.InventoryItem;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InventoryDao {
    private static final String FILE_PATH = "inventory_data.txt";
    private Queue<InventoryItem> operationQueue = new ConcurrentLinkedQueue<>();

    // Add operation to queue
    public void enqueueOperation(InventoryItem item) {
        operationQueue.add(item);
    }

    // Process all operations in queue
    public void processQueue() {
        List<InventoryItem> inventory = getAllItems();

        while (!operationQueue.isEmpty()) {
            InventoryItem item = operationQueue.poll();
            boolean exists = false;

            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getId() == item.getId()) {
                    inventory.set(i, item);
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                inventory.add(item);
            }
        }

        saveAllItems(inventory);
    }

    // Get all items from file
    public List<InventoryItem> getAllItems() {
        List<InventoryItem> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                InventoryItem item = InventoryItem.fromString(line);
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, return empty list
        }

        return items;
    }

    // Save all items to file
    private void saveAllItems(List<InventoryItem> items) {
        // Sort items by ID using bubble sort
        bubbleSort(items);

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (InventoryItem item : items) {
                writer.println(item.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bubble sort implementation
    private void bubbleSort(List<InventoryItem> items) {
        int n = items.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (items.get(j).getId() > items.get(j+1).getId()) {
                    // Swap items
                    InventoryItem temp = items.get(j);
                    items.set(j, items.get(j+1));
                    items.set(j+1, temp);
                }
            }
        }
    }

    // Get item by ID
    public InventoryItem getItemById(int id) {
        List<InventoryItem> items = getAllItems();
        for (InventoryItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    // Add new item
    public void addItem(InventoryItem item) {
        enqueueOperation(item);
        processQueue();
    }

    // Update existing item
    public void updateItem(InventoryItem item) {
        enqueueOperation(item);
        processQueue();
    }

    // Delete item
    public void deleteItem(int id) {
        List<InventoryItem> items = getAllItems();
        items.removeIf(item -> item.getId() == id);
        saveAllItems(items);
    }

    // Get next available ID
    public int getNextId() {
        List<InventoryItem> items = getAllItems();
        if (items.isEmpty()) {
            return 1;
        }
        return items.get(items.size() - 1).getId() + 1;
    }

    // Get low stock items
    public List<InventoryItem> getLowStockItems() {
        List<InventoryItem> items = getAllItems();
        List<InventoryItem> lowStockItems = new ArrayList<>();

        for (InventoryItem item : items) {
            if (item.getQuantity() < item.getThreshold()) {
                lowStockItems.add(item);
            }
        }

        return lowStockItems;
    }
}