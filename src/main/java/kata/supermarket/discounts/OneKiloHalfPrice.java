package kata.supermarket.discounts;

import kata.supermarket.Item;
import kata.supermarket.ItemByWeight;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OneKiloHalfPrice implements IDiscount {

    // process discount - unlike the item by unit discounts it feels this class should not mix items
    @Override
    public BigDecimal processDiscount(List<Item> items) {
        /*
            TODO: This grouping is probably not needed as the Basket class doesn't return the correct values in the present state so the discount is wrong
            As an example a £4.99 / kg product passed in as two 500g products should result in a saving of £4.99 / 2 = £2.49. making the cost £4.99 - £2.49 = £2.50
            However the basket will return £4.99 / 2 = £2.495 which is rounded up to £2.50 per half kg, which raises the price to £5.00
         */
        Map<String, List<Item>> groupedItems = items.stream().collect(Collectors.groupingBy(Item::getProductName));
        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (Map.Entry<String, List<Item>> entry : groupedItems.entrySet()) {
            BigDecimal totalWeight = BigDecimal.ZERO;

            for (Item item : entry.getValue()) {
                if (!(item instanceof ItemByWeight))
                    throw new RuntimeException("Discount type OneKiloHalfPrice is only valid for ItemByWeight items");
                ItemByWeight itemByWeight = (ItemByWeight) item;
                totalWeight = totalWeight.add(itemByWeight.getWeightInKilos());
            }
            // get first item - there should be at least one because of the grouping method (or there would be no group) and it will be a ItemByWeight because of the above check
            BigDecimal pricePerKilo = ((ItemByWeight) entry.getValue().get(0)).getPricePerKilo();
            BigDecimal cost = totalWeight.setScale(0, RoundingMode.DOWN).multiply(pricePerKilo);
            totalDiscount = totalDiscount.add(cost.divide(BigDecimal.valueOf(2), 2, RoundingMode.DOWN));
        }
        return totalDiscount;
    }
}
