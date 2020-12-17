package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct implements IProduct {

    private final BigDecimal pricePerKilo;
    private final String name;

    public WeighedProduct(final String name, final BigDecimal pricePerKilo) {
        this.name = name;
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
