package dataSource.impl;

import dataSource.DiscountCardDataSource;
import model.DiscountCard;
import util.IdGenerator;

import java.util.ArrayList;

public class DiscountCardDataSourceImpl implements DiscountCardDataSource {

    private static ArrayList<DiscountCard> discountCards = new ArrayList<>();

    static {
        DiscountCard discountCard1 = new DiscountCard(1);
        DiscountCard discountCard2 = new DiscountCard(5);
        DiscountCard discountCard3 = new DiscountCard(1);
        DiscountCard discountCard4 = new DiscountCard(5);
        DiscountCard discountCard5 = new DiscountCard(1);
        DiscountCard discountCard6 = new DiscountCard(3);
        DiscountCard discountCard7 = new DiscountCard(3);
        DiscountCard discountCard8 = new DiscountCard(1);
        DiscountCard discountCard9 = new DiscountCard(3);
        DiscountCard discountCard10 = new DiscountCard(1);

        discountCards.add(discountCard1);
        discountCards.add(discountCard2);
        discountCards.add(discountCard3);
        discountCards.add(discountCard4);
        discountCards.add(discountCard5);
        discountCards.add(discountCard6);
        discountCards.add(discountCard7);
        discountCards.add(discountCard8);
        discountCards.add(discountCard9);
        discountCards.add(discountCard10);

        setNumber();
    }

    public static void setNumber() {
        for (DiscountCard card : discountCards) {
            card.setNumber(IdGenerator.generateDiscountCardNumber());
        }
    }

    @Override
    public ArrayList<DiscountCard> getDiscountCards() {
        return discountCards;
    }

}
