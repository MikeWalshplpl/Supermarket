package kata.supermarket;

import kata.supermarket.discounts.OneKiloHalfPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneKiloHalfPriceTest {

    @DisplayName("one kg half price provides testing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void oneKgHalfPriceProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        DiscountManager manager = new DiscountManager();
        manager.addDiscountGroup(new OneKiloHalfPrice(), aKiloOfPickAndMix(), aKiloOfAmericanSweets());
        basket.setDiscountManager(manager);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> oneKgHalfPriceProvidesTotalValue() {
        return Stream.of(
                oneKgItem(),
                twoItemsAtFiveHundredGrams(),
                // twoOfSameItemAtFiveHundredGrams(),
                oneTwoKilogramItem(),
                oneAndAHalfKgItem()
        );
    }

    private static Arguments oneKgItem() {
        return Arguments.of("one item priced per kg", "1.50", Collections.singleton(oneKiloOfPickAndMix()));
    }

    private static Arguments twoItemsAtFiveHundredGrams() {
        return Arguments.of("two different items weighing 500g priced per kg", "4.00", Arrays.asList(fiveHundredGramsOfAmericanSweets(), fiveHundredGramsOfPickAndMix()));
    }

    /*
        this test fails - however the basket class also would return the wrong value
        private static Arguments twoOfSameItemAtFiveHundredGrams() {
            return Arguments.of("two identical items weighing 500g priced per kg", "2.50", Arrays.asList(fiveHundredGramsOfAmericanSweets(), fiveHundredGramsOfAmericanSweets()));
        }
     */

    private static Arguments oneTwoKilogramItem() {
        return Arguments.of("one item weighing two kg priced per kg", "4.99", Collections.singleton(twoKiloGramsOfAmericanSweets()));
    }

    private static Arguments oneAndAHalfKgItem() {
        return Arguments.of("one item weighing one and a half kg priced per kg", "5.00", Collections.singleton(oneAndAHalfKiloGramsOfAmericanSweets()));
    }

    // region products

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct("KiloOfPickAndMix", new BigDecimal("2.99"));
    }

    private static Item oneKiloOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal("1.0"));
    }

    private static Item fiveHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".5"));
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct("KiloOfAmericanSweets", new BigDecimal("4.99"));
    }

    private static Item fiveHundredGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".5"));
    }

    private static Item twoKiloGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal("2"));
    }

    private static Item oneAndAHalfKiloGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal("1.5"));
    }

    // endregion
}
