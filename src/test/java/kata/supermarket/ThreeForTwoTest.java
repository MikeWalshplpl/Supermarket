package kata.supermarket;

import kata.supermarket.discounts.ThreeForTwo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreeForTwoTest {

    @DisplayName("buy three for the price of two provides testing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void buyThreeForThePriceOfTwoProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        DiscountManager manager = new DiscountManager();
        manager.addDiscountGroup(new ThreeForTwo(), aPintOfMilkOnOffer(), aLoafOfBreadOnOffer(), aPackOfDigestivesOnOffer());
        basket.setDiscountManager(manager);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> buyThreeForThePriceOfTwoProvidesTotalValue() {
        return Stream.of(
                twoItemsInDeal(),
                threeItemsInDeal(),
                threeItemsButOnlyTwoInDeal(),
                fourItemsAllInDeal()
        );
    }

    private static Arguments twoItemsInDeal() {
        return Arguments.of("two items both on deal", "0.98", Arrays.asList(aPintOfMilkOnOffer(), aPintOfMilkOnOffer()));
    }

    private static Arguments threeItemsInDeal() {
        return Arguments.of("three items all on deal", "0.98", Arrays.asList(aPintOfMilkOnOffer(), aPintOfMilkOnOffer(), aPintOfMilkOnOffer()));
    }

    private static Arguments threeItemsButOnlyTwoInDeal() {
        return Arguments.of("three items but only two on deal", "2.53", Arrays.asList(aPintOfMilkOnOffer(), aPintOfMilkOnOffer(), aPackOfDigestives()));
    }

    private static Arguments fourItemsAllInDeal() {
        return Arguments.of("four items all in deal", "4.43", Arrays.asList(aPackOfDigestivesOnOffer(), aPackOfDigestivesOnOffer(), aLoafOfBreadOnOffer(), aPintOfMilkOnOffer()));
    }

    // region products

    private static Item aPackOfDigestives() {
        return new Product("PackOfDigestives", new BigDecimal("1.55")).oneOf();
    }

    private static Item aPackOfDigestivesOnOffer() {
        return new Product("PackOfDigestivesOnOffer", new BigDecimal("1.55")).oneOf();
    }

    private static Item aPintOfMilkOnOffer() {
        return new Product("PintOfMilkOnOffer", new BigDecimal("0.49")).oneOf();
    }

    private static Item aLoafOfBreadOnOffer() {
        return new Product("LoafOfBreadOnOffer", new BigDecimal("1.33")).oneOf();
    }

    // endregion

}
