package za.ac.cput.repository;

import za.ac.cput.domain.Location;
import java.util.*;
import za.ac.cput.util.Helper;

/* LocationRepository.java
Repository implementation for Location
Author: Your Name (Your Student Number)
Date: 26 March 2026
*/
public class LocationRepository implements IRepository<Location, String> {

    private static LocationRepository repo = null;
    private final Map<String, Location> locMap = new HashMap<>();

    private LocationRepository() {}

    public static LocationRepository getRepository() {
        if (repo == null) repo = new LocationRepository();
        return repo;
    }

    @Override
    public Location create(Location entity) {
        locMap.put(entity.getAddress(), entity);
        return entity;
    }

    @Override
    public Location read(String address) {
        return locMap.get(address);
    }

    @Override
    public Location update(Location entity) {
        locMap.put(entity.getAddress(), entity);
        return entity;
    }

    @Override
    public Location delete(String address) {
        return locMap.remove(address);
    }

    @Override
    public List<Location> getAll() {
        return new ArrayList<>(locMap.values());
    }
}