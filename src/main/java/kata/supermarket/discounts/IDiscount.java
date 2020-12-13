package kata.supermarket.discounts;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public interface IDiscount {
    BigDecimal processDiscount(List<Item> items);
}
