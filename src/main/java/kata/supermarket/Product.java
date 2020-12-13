package kata.supermarket;

import java.math.BigDecimal;

public class Product implements IProduct {

    private final BigDecimal pricePerUnit;
    private final String name;

    public Product(final String name, final BigDecimal pricePerUnit) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    @Override
    public String getName(){
        return this.name;
    }
}
