package za.ac.cput.repository;

import za.ac.cput.domain.BoardingPass;
import java.util.*;
import za.ac.cput.util.Helper;

public class BoardingPassRepository implements IRepository<BoardingPass, String> {

    private static BoardingPassRepository repo = null;
    private final Map<String, BoardingPass> bpMap = new HashMap<>();

    private BoardingPassRepository() {}

    public static BoardingPassRepository getRepository() {
        if (repo == null) repo = new BoardingPassRepository();
        return repo;
    }

    @Override
    public BoardingPass create(BoardingPass entity) {
        bpMap.put(entity.getBookingReference(), entity);
        return entity;
    }

    @Override
    public BoardingPass read(String bookingReference) {
        return bpMap.get(bookingReference);
    }

    @Override
    public BoardingPass update(BoardingPass entity) {
        bpMap.put(entity.getBookingReference(), entity);
        return entity;
    }

    @Override
    public BoardingPass delete(String bookingReference) {
        return bpMap.remove(bookingReference);
    }

    @Override
    public List<BoardingPass> getAll() {
        return new ArrayList<>(bpMap.values());
    }
}