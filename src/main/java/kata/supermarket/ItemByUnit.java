package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;
    private boolean discountApplied = false;

    ItemByUnit(final Product product) {
        this.product = product;
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }

    @Override
    public String getProductName() {
        return product.getName();
    }

    @Override
    public boolean isDiscountApplied() {
        return discountApplied;
    }

    @Override
    public void setDiscountApplied(boolean val) {
        this.discountApplied = val;
    }
}
