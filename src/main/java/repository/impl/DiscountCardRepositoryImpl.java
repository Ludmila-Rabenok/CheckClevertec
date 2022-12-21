package repository.impl;

import dataSource.DiscountCardDataSource;
import exceptions.EntityNotFoundException;
import model.DiscountCard;
import repository.DiscountCardRepository;
import util.IdGenerator;

import java.util.List;

public class DiscountCardRepositoryImpl implements DiscountCardRepository {
    private final DiscountCardDataSource discountCardDataSource;

    public DiscountCardRepositoryImpl(DiscountCardDataSource discountCardDataSource) {
        this.discountCardDataSource = discountCardDataSource;
    }

    @Override
    public DiscountCard save(DiscountCard discountCard) {
        discountCard.setNumber(IdGenerator.generateDiscountCardNumber());
        getAll().add(discountCard);
        return discountCard;
    }

    @Override
    public List<DiscountCard> getAll() {
        return discountCardDataSource.getDiscountCards();
    }

    @Override
    public DiscountCard getByNumber(Integer number) throws EntityNotFoundException {
        DiscountCard card = getAll().stream()
                .filter(x -> number.equals(x.getNumber()))
                .findFirst()
                .orElse(null);
        if (card == null) {
            throw new EntityNotFoundException("No such card found");
        }
        return card;
    }

    @Override
    public DiscountCard update(DiscountCard discountCard) {
        DiscountCard cardForUpdate = getByNumber(discountCard.getNumber());
        cardForUpdate.setPercentage(discountCard.getPercentage());
        return cardForUpdate;
    }

    @Override
    public boolean delete(DiscountCard discountCard) {
        return getAll().remove(discountCard);
    }

}
