package com.rest.service;

import com.rest.models.MenuItem;
import com.rest.repository.MenuItemRepository;
import java.util.List;
import java.util.Optional;

public class MenuService {
    private final MenuItemRepository repository = new MenuItemRepository();
    public List<MenuItem> getMenu() { return repository.findAll(); }
    public Optional<MenuItem> getItem(int id) { return repository.findById(id); }
}
