package kata.supermarket.discounts;

public class BuyOneGetOneFree extends BuySomeGetSomeFree {

    @Override
    protected int getAmountNeededToBuy() {
        return 1;
    }

    @Override
    protected int getAmountFree() {
        return 1;
    }
}
