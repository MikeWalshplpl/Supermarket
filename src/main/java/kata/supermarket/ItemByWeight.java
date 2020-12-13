package kata.supermarket;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;
    private boolean discountApplied = false;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
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
    public void setDiscountApplied(boolean discountApplied) {
        this.discountApplied = discountApplied;
    }

    public BigDecimal getWeightInKilos() {
        return this.weightInKilos;
    }

    public BigDecimal getPricePerKilo() {
        return product.pricePerKilo();
    }
}
