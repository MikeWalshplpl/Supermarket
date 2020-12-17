package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final List<Item> items;

    private final DiscountManager discountManager;

    public Basket(DiscountManager manager) {
        this.items = new ArrayList<>();
        this.discountManager = manager;
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

//    public void setDiscountManager(DiscountManager manager) {
//        this.discountManager = manager;
//    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        private BigDecimal discounts() {
            return discountManager == null ? BigDecimal.ZERO : discountManager.getDiscountTotal(this.items);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
