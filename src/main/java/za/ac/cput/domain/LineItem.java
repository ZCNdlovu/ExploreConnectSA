package za.ac.cput.domain;

public class LineItem {
    private String description;
    private int quantity;
    private double unitPrice;
    private double total;

    private LineItem(Builder builder) {
        this.description = builder.description;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        calculateTotal();
    }

    // Getters
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotal() { return total; }

    // Business methods
    public double calculateTotal() {
        this.total = quantity * unitPrice;
        return total;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "description='" + description + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }

    public static class Builder {
        private String description;
        private int quantity;
        private double unitPrice;
        private double total;

        public Builder(String description, int quantity, double unitPrice) {
            this.description = description;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public Builder copy(LineItem lineItem) {
            this.description = lineItem.description;
            this.quantity = lineItem.quantity;
            this.unitPrice = lineItem.unitPrice;
            this.total = lineItem.total;
            return this;
        }

        public LineItem build() {
            return new LineItem(this);
        }
    }
}
