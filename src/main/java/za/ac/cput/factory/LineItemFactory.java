 package za.ac.cput.factory;

import za.ac.cput.domain.LineItem;
import za.ac.cput.util.Helper;

public class LineItemFactory {

    public static LineItem createLineItem(String description, int quantity, double unitPrice) {
        return new LineItem.Builder(description, quantity, unitPrice).build();
    }
}
