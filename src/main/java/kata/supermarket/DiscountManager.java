package kata.supermarket;

import kata.supermarket.discounts.IDiscount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple class to hold the various discounts we have on offer.
 * This is given to a basket to tell the basket how much we want to return to the customer
 */
public class DiscountManager {
    List<DiscountGroup> discountGroups = new ArrayList<>();

    public BigDecimal getDiscountTotal(List<Item> items) {
        BigDecimal discountTotal = BigDecimal.ZERO;
        for (DiscountGroup discountGroup : discountGroups) {
            discountTotal = discountTotal.add(discountGroup.processDiscount(items));
        }
        return discountTotal;
    }

    public void addDiscountGroup(IDiscount discount, Item... items) {
        discountGroups.add(new DiscountGroup(discount, items));
    }

    public void addDiscountGroup(IDiscount discount, IProduct... products) {
        discountGroups.add(new DiscountGroup(discount, products));
    }
}
