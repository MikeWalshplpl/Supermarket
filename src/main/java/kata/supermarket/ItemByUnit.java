package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;
    private final boolean discountApplied ;

    ItemByUnit(final Product product)
    {
        this.product = product;
        this.discountApplied = false;
    }

    ItemByUnit(final Product product, boolean discountApplied) {
        this.product = product;
        this.discountApplied = discountApplied;
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
    public Item applyDiscount() {
        return new ItemByUnit(this.product, true);
    }
}
