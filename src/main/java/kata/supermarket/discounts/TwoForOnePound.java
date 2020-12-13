package kata.supermarket.discounts;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TwoForOnePound implements IDiscount {
    @Override
    public BigDecimal processDiscount(List<Item> items) {
        // if anything is excluded from the deal, make it the most expensive item
        List<Item> sortedItems = items.stream().sorted(Comparator.comparing(Item::price).reversed()).collect(Collectors.toList());
        int itemsPurchased = sortedItems.size();
        int itemsNotInDiscountGroup = itemsPurchased % 2;

        // calculate what we paid for the items on offer
        BigDecimal costForAllItemsInGroups = sortedItems.stream().skip(itemsNotInDiscountGroup).map(Item::price).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        // integer division will calculate the number of pairs - each pair is £1
        BigDecimal amountWeActuallyPayForGroups = new BigDecimal(itemsPurchased / 2);

        return costForAllItemsInGroups.subtract(amountWeActuallyPayForGroups);

    }
}
