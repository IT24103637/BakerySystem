package com.BakeryOrder.services;

import com.BakeryOrder.dao.InventoryDao;
import com.BakeryOrder.model.InventoryItem;
import java.util.List;

public class InventoryService {
    private InventoryDao inventoryDao;

    public InventoryService() {
        this.inventoryDao = new InventoryDao();
    }

    public List<InventoryItem> getAllInventoryItems() {
        return inventoryDao.getAllItems();
    }

    public InventoryItem getInventoryItem(int id) {
        return inventoryDao.getItemById(id);
    }

    public void addInventoryItem(InventoryItem item) {
        item.setId(inventoryDao.getNextId());
        inventoryDao.addItem(item);
    }

    public void updateInventoryItem(InventoryItem item) {
        inventoryDao.updateItem(item);
    }

    public void deleteInventoryItem(int id) {
        inventoryDao.deleteItem(id);
    }

    public List<InventoryItem> getLowStockItems() {
        return inventoryDao.getLowStockItems();
    }

    public int getTotalItemsCount() {
        return inventoryDao.getAllItems().size();
    }

    public int getLowStockItemsCount() {
        return inventoryDao.getLowStockItems().size();
    }

    public int getOutOfStockItemsCount() {
        int count = 0;
        for (InventoryItem item : inventoryDao.getAllItems()) {
            if (item.getQuantity() <= 0) {
                count++;
            }
        }
        return count;
    }
}