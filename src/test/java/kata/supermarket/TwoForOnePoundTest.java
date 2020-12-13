package kata.supermarket;

import kata.supermarket.discounts.TwoForOnePound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoForOnePoundTest {

    @DisplayName("buy two items for one pound provides testing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void buyTwoItemsForOnePoundProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        DiscountManager manager = new DiscountManager();
        manager.addDiscountGroup(new TwoForOnePound(), aPintOfMilkOnOffer(), aBarOfSoapOnOffer(), aChocolateBarOnOffer(),aDrinksBottleOnOffer());
        basket.setDiscountManager(manager);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> buyTwoItemsForOnePoundProvidesTotalValue() {
        return Stream.of(
                noItemsInDeal(),
                oneItemInDeal(),
                twoItemsInDeal(),
                twoItemsInBadDeal(),
                threeItemsInDeal(),
                fourItemsOneNotInDeal()
        );
    }

    private static Arguments noItemsInDeal() {
        return Arguments.of("no items on offer", "0.59", Collections.singleton(aChocolateBar()));
    }

    private static Arguments oneItemInDeal() {
        return Arguments.of("one item on deal", "0.49", Collections.singletonList(aPintOfMilkOnOffer()));
    }

    private static Arguments twoItemsInDeal() {
        return Arguments.of("two all on deal", "1.00", Arrays.asList(aChocolateBarOnOffer(),aBarOfSoapOnOffer()));
    }

    private static Arguments twoItemsInBadDeal() {
        return Arguments.of("two items that should be less than a pound if bought separate", "1.00", Arrays.asList(aPintOfMilkOnOffer(), aPintOfMilkOnOffer()));
    }

    private static Arguments threeItemsInDeal() {
        return Arguments.of("three items all on offer", "1.99", Arrays.asList(aPintOfMilkOnOffer(), aBarOfSoapOnOffer(), aDrinksBottleOnOffer()));
    }

    private static Arguments fourItemsOneNotInDeal() {
        return Arguments.of("four items but one not in offer", "2.58", Arrays.asList(aChocolateBar(), aDrinksBottleOnOffer(), aBarOfSoapOnOffer(), aPintOfMilkOnOffer()));
    }

    // region products

    private static Item aChocolateBar() {
        return new Product("ChocolateBar", new BigDecimal("0.59")).oneOf();
    }

    private static Item aChocolateBarOnOffer() {
        return new Product("ChocolateBarOnOffer", new BigDecimal("0.59")).oneOf();
    }

    private static Item aPintOfMilkOnOffer() {
        return new Product("PintOfMilkOnOffer", new BigDecimal("0.49")).oneOf();
    }

    private static Item aDrinksBottleOnOffer() {
        return new Product("DrinksBottleOnOffer", new BigDecimal("0.99")).oneOf();
    }

    private static Item aBarOfSoapOnOffer() {
        return new Product("BarOfSoapOnOffer", new BigDecimal("0.80")).oneOf();
    }

    // endregion
}
