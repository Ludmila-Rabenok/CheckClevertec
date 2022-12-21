package service.impl;

import model.DiscountCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.DiscountCardRepository;
import service.DiscountCardService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DiscountCardServiceImplTest {
    @Mock
    private final DiscountCardRepository discountCardRepository = mock(DiscountCardRepository.class);

    private DiscountCardService discountCardService;

    private static DiscountCard expectedCard;
    private static List<DiscountCard> expectedCardList;

    @BeforeAll
    public static void init() {
        expectedCard = new DiscountCard();
        expectedCard.setNumber(1000);
        expectedCard.setPercentage(5);
        expectedCardList = new ArrayList<>();
        expectedCardList.add(expectedCard);
    }

    @BeforeEach
    public void prepareTestData() {
        discountCardService = new DiscountCardServiceImpl(discountCardRepository);
    }

    @Test
    void save() {
        when(discountCardRepository.save(any(DiscountCard.class))).thenReturn(expectedCard);

        DiscountCard actualCard = discountCardService.save(expectedCard);

        verify(discountCardRepository, times(1)).save(expectedCard);
        assertNotNull(actualCard);
        assertSame(expectedCard, actualCard);
    }

    @Test
    void getAll() {
        when(discountCardRepository.getAll()).thenReturn(expectedCardList);

        List<DiscountCard> cardList = discountCardService.getAll();

        assertEquals(1, cardList.size());
        verify(discountCardRepository, times(1)).getAll();
    }

    @Test
    void getByNumber() {
        when(discountCardRepository.getByNumber(anyInt())).thenReturn(expectedCard);

        DiscountCard foundCard = discountCardService.getByNumber(1000);

        assertNotNull(foundCard);
        assertSame(expectedCard, foundCard);
        verify(discountCardRepository, timeout(1)).getByNumber(1000);
    }

    @Test
    void update() {
        when(discountCardRepository.update(any(DiscountCard.class))).thenReturn(expectedCard);

        expectedCard.setPercentage(4);
        DiscountCard actualCard = discountCardService.update(expectedCard);

        verify(discountCardRepository, times(1)).update(expectedCard);
        assertNotNull(actualCard);
        assertSame(expectedCard, actualCard);
    }

    @Test
    void delete() {
        when(discountCardRepository.delete(any(DiscountCard.class))).thenReturn(true);

        boolean actual = discountCardService.delete(expectedCard);

        verify(discountCardRepository, times(1)).delete(expectedCard);
        assertTrue(actual);
    }

}
