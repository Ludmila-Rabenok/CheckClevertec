package service.impl;

import exceptions.EntityNotFoundException;
import model.DiscountCard;
import repository.DiscountCardRepository;
import service.DiscountCardService;

import java.util.List;

public class DiscountCardServiceImpl implements DiscountCardService {
    private final DiscountCardRepository discountCardRepository;

    public DiscountCardServiceImpl(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    @Override
    public DiscountCard save(DiscountCard discountCard) {
        return discountCardRepository.save(discountCard);
    }

    @Override
    public List<DiscountCard> getAll() {
        return discountCardRepository.getAll();
    }

    @Override
    public DiscountCard getByNumber(Integer number) {
        DiscountCard card = null;
        try {
            card = discountCardRepository.getByNumber(number);
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return card;
    }

    @Override
    public DiscountCard update(DiscountCard discountCard) {
        return discountCardRepository.update(discountCard);
    }

    @Override
    public boolean delete(DiscountCard discountCard) {
        if (!discountCardRepository.delete(discountCard)) {
            System.err.println("The card was not deleted");
            return false;
        }
        return true;
    }

}
