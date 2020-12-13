package kata.supermarket.discounts;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BuySomeGetSomeFree implements IDiscount {

    protected abstract int getAmountNeededToBuy();

    protected abstract int getAmountFree();

    @Override
    public BigDecimal processDiscount(List<Item> items) {
        // cheapest item free
        List<Item> sortedItems = items.stream().sorted(Comparator.comparing(Item::price).reversed()).collect(Collectors.toList());
        int itemsPurchased = sortedItems.size();
        int itemsNotInDiscountGroup = itemsPurchased % (getAmountNeededToBuy() + getAmountFree());
        int groupCount = itemsPurchased / (getAmountNeededToBuy() + getAmountFree());
        int itemsToPayFor = groupCount * getAmountNeededToBuy() + itemsNotInDiscountGroup;
        // discount = sum of items we don't have to pay for
        return sortedItems.stream().skip(itemsToPayFor).map(Item::price).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
