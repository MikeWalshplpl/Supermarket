package kata.supermarket;

import java.math.BigDecimal;

public interface Item {
    BigDecimal price();

    String getProductName();

    boolean isDiscountApplied();

    void setDiscountApplied(boolean val);
}
