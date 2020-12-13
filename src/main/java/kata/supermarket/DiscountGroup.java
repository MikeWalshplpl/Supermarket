package kata.supermarket;

import kata.supermarket.discounts.IDiscount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Groups items to a specific discount.
 * This is one way we can group items.
 * The storage method is to map the discount to a product name key
 */
public class DiscountGroup {

    private List<String> productsInGroup = new ArrayList<>();
    private IDiscount discount;

    public DiscountGroup(IDiscount discount, Item... items) {
        Arrays.stream(items).forEach(item -> productsInGroup.add(item.getProductName()));
        this.discount = discount;
    }

    public DiscountGroup(IDiscount discount, IProduct... products) {
        Arrays.stream(products).forEach(product -> productsInGroup.add(product.getName()));
        this.discount = discount;
    }

    public BigDecimal processDiscount(List<Item> items) {
        List<Item> validItems = items.stream().filter(i -> productsInGroup.contains(i.getProductName())).collect(Collectors.toList());
        BigDecimal discountTotal = discount.processDiscount(validItems);
        // ensure the discount can only be applied once - this can be extended later to have some form of group ordering, eg buy 1 get one free could rank higher than two for £1 if the product is in both groups
        validItems.forEach(i -> i.setDiscountApplied(true));
        return discountTotal;
    }
}
