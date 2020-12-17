package kata.supermarket;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;
    private final boolean discountApplied;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
        this.discountApplied = false;
    }

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos, boolean discountApplied) {
        this.product = product;
        this.weightInKilos = weightInKilos;
        this.discountApplied = discountApplied;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
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
        return null;
    }

    public BigDecimal getWeightInKilos() {
        return this.weightInKilos;
    }

    public BigDecimal getPricePerKilo() {
        return product.pricePerKilo();
    }
}
