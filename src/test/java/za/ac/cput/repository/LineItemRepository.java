package za.ac.cput.repository;

import za.ac.cput.domain.LineItem;
import java.util.*;
import za.ac.cput.util.Helper;


public class LineItemRepository implements IRepository<LineItem, String> {

    private static LineItemRepository repo = null;
    private final Map<String, LineItem> lineItemMap = new HashMap<>();

    private LineItemRepository() {}

    public static LineItemRepository getRepository() {
        if (repo == null) repo = new LineItemRepository();
        return repo;
    }

    @Override
    public LineItem create(LineItem entity) {
        lineItemMap.put(entity.getDescription(), entity);
        return entity;
    }

    @Override
    public LineItem read(String description) {
        return lineItemMap.get(description);
    }

    @Override
    public LineItem update(LineItem entity) {
        lineItemMap.put(entity.getDescription(), entity);
        return entity;
    }

    @Override
    public LineItem delete(String description) {
        return lineItemMap.remove(description);
    }

    @Override
    public List<LineItem> getAll() {
        return new ArrayList<>(lineItemMap.values());
    }
}