package com.rest.service;

import com.rest.models.Table;
import com.rest.repository.TableRepository;
import java.util.List;
import java.util.Optional;

public class TableService {
    private final TableRepository repository;

    public TableService() {
        repository = new TableRepository();
    }

    public List<Table> getAllTables() {
        return repository.findAll();
    }

    public boolean occupyTable(int id) {
        Optional<Table> result = repository.findById(id);
        if (result.isEmpty() || result.get().getStatus() == Table.Availability.OCCUPIED) {
            return false;
        }
        result.get().occupyTable();
        return true;
    }

    public boolean freeTable(int id) {
        Optional<Table> result = repository.findById(id);
        if (result.isEmpty() || result.get().getStatus() == Table.Availability.FREE) {
            return false;
        }
        result.get().freeTable();
        return true;
    }
}
