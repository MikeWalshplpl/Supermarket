package kata.supermarket.discounts;

public class ThreeForTwo extends BuySomeGetSomeFree {
    @Override
    protected int getAmountNeededToBuy() {
        return 2;
    }

    @Override
    protected int getAmountFree() {
        return 1;
    }
}
