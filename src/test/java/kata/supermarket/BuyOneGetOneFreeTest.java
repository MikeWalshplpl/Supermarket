package kata.supermarket;

import kata.supermarket.discounts.BuyOneGetOneFree;
import kata.supermarket.discounts.IDiscount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyOneGetOneFreeTest {

    @DisplayName("buy one get one free provides testing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void buyOneGetOneFreeProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        DiscountManager manager = new DiscountManager();
        manager.addDiscountGroup(new BuyOneGetOneFree(), aPintOfMilkOnOffer(), aLoafOfBreadOnOffer());
        basket.setDiscountManager(manager);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> buyOneGetOneFreeProvidesTotalValue() {
        return Stream.of(
                singleItem(),
                twoItemsNoDeal(),
                twoItemsOnDeal(),
                threeItemsOnDeal(),
                oneItemOnDealTwoNotOnDeal(),
                twoItemsOnDealWithSeveralIntermediateItems()
        );
    }

    private static Arguments singleItem() {
        return Arguments.of("one item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments twoItemsNoDeal() {
        return Arguments.of("two items neither on deal", "0.98", Arrays.asList(aPintOfMilk(), aPintOfMilk()));
    }

    private static Arguments twoItemsOnDeal() {
        return Arguments.of("two items both on deal", "1.33", Arrays.asList(aPintOfMilkOnOffer(), aLoafOfBreadOnOffer()));
    }

    private static Arguments threeItemsOnDeal() {
        return Arguments.of("three items all on deal", "0.98", Arrays.asList(aPintOfMilkOnOffer(), aPintOfMilkOnOffer(), aPintOfMilkOnOffer()));
    }

    private static Arguments oneItemOnDealTwoNotOnDeal() {
        return Arguments.of("one item on deal all others not on deal", "3.59", Arrays.asList(aPintOfMilkOnOffer(), aPackOfDigestives(), aPackOfDigestives()));
    }

    private static Arguments twoItemsOnDealWithSeveralIntermediateItems() {
        return Arguments.of("two item on deal at opposite ends of the input list", "8.24", Arrays.asList(aPintOfMilkOnOffer(), aPackOfDigestives(), aPackOfDigestives(),
                aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives(), aPintOfMilkOnOffer()));
    }

    // region products

    private static Item aPintOfMilk() {
        return new Product("PintOfMilk", new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product("PackOfDigestives", new BigDecimal("1.55")).oneOf();
    }

    private static Item aPintOfMilkOnOffer() {
        return new Product("PintOfMilkOnOffer", new BigDecimal("0.49")).oneOf();
    }

    private static Item aLoafOfBreadOnOffer() {
        return new Product("LoafOfBreadOnOffer", new BigDecimal("1.33")).oneOf();
    }

    // endregion
}
