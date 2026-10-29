package com.rest.repository;

import com.rest.models.MenuItem;
import java.util.*;

public class MenuItemRepository {
    private final List<MenuItem> items = new ArrayList<>();
    public MenuItemRepository() {
        items.add(new MenuItem(1, "Idli", 40));
        items.add(new MenuItem(2, "Dosa", 70));
        items.add(new MenuItem(3, "Veg Meals", 120));
        items.add(new MenuItem(4, "Coffee", 25));
        items.add(new MenuItem(5, "Paneer Fried Rice", 150));
    }
    public List<MenuItem> findAll() { return new ArrayList<>(items); }
    public Optional<MenuItem> findById(int id) { return items.stream().filter(i -> i.getId() == id).findFirst(); }
}
