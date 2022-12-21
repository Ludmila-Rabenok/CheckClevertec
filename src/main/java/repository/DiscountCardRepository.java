package repository;

import model.DiscountCard;

import java.util.List;


public interface DiscountCardRepository {

    DiscountCard save(DiscountCard discountCard);

    List<DiscountCard> getAll();

    DiscountCard getByNumber(Integer number);

    DiscountCard update(DiscountCard discountCard);

    boolean delete(DiscountCard discountCard);

}
