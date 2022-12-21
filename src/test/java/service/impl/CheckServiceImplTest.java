package service.impl;

import model.Check;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.CheckRepository;
import service.CheckService;
import service.DiscountCardService;
import service.ProductService;
import util.CSVCheck;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CheckServiceImplTest {
    @Mock
    private final CheckRepository checkRepository = mock(CheckRepository.class);
    @Mock
    private final ProductService productService = mock(ProductService.class);
    @Mock
    private final DiscountCardService discountCardService = mock(DiscountCardService.class);
    @Mock
    private final CSVCheck checkCSV = mock(CSVCheck.class);

    private CheckService checkService;

    private static Check expectedCheck;
    private static List<Check> expectedCheckList;
    private static List<String> expectedStringList;

    @BeforeAll
    public static void init() {
        expectedCheck = new Check();
        expectedCheck.setId(1);
        expectedCheck.setDate(LocalDateTime.now());
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        expectedCheck.setProductIdAndAmount(map);
        expectedCheck.setDiscountCardNumber(1000);
        expectedCheckList = new ArrayList<>();
        expectedCheckList.add(expectedCheck);
        expectedStringList = new ArrayList<>();
        expectedStringList.add("1-1");
        expectedStringList.add("card-1000");
    }

    @BeforeEach
    public void prepareTestData() {
        checkService = new CheckServiceImpl(checkRepository, productService, discountCardService);
    }

    @Test
    void save() {
        when(checkRepository.save(any(Check.class))).thenReturn(expectedCheck);

        Check actualCheck = checkService.save(expectedCheck);

        verify(checkRepository, times(1)).save(expectedCheck);
        assertNotNull(actualCheck);
        assertSame(expectedCheck, actualCheck);
    }

    @Test
    void getAll() {
        when(checkRepository.getAll()).thenReturn(expectedCheckList);

        List<Check> checkList = checkService.getAll();

        assertEquals(1, checkList.size());
        verify(checkRepository, times(1)).getAll();
    }

    @Test
    void getById() {
        when(checkRepository.getById(1)).thenReturn(expectedCheck);

        Check foundCheck = checkService.getById(1);

        assertNotNull(foundCheck);
        assertSame(expectedCheck, foundCheck);
        verify(checkRepository, timeout(1)).getById(1);
    }

    @Test
    void update() {
        when(checkRepository.update(any(Check.class))).thenReturn(expectedCheck);

        expectedCheck.setDate(LocalDateTime.now().minusDays(5));
        expectedCheck.setProductIdAndAmount(new HashMap<>());
        expectedCheck.setDiscountCardNumber(1001);
        Check actualCheck = checkService.update(expectedCheck);

        verify(checkRepository, times(1)).update(expectedCheck);
        assertNotNull(actualCheck);
        assertSame(expectedCheck, actualCheck);
    }

    @Test
    void delete() {
        when(checkRepository.delete(any(Check.class))).thenReturn(true);

        boolean actual = checkService.delete(expectedCheck);

        verify(checkRepository, times(1)).delete(expectedCheck);
        assertTrue(actual);
    }

    @Test
    void createNewCheck() {
        when(checkService.save(any(Check.class))).thenReturn(expectedCheck);

        Check actualCheck = checkService.createNewCheck(expectedStringList);

        assertEquals(actualCheck, expectedCheck);
        assertNotNull(actualCheck);
    }

    @Test
    void exportCheckToFile() {
        when(checkService.buildCheck(any(Check.class))).thenReturn(expectedStringList);
        when(checkCSV.prepareForExportCSV(anyList())).thenReturn(expectedStringList);
        when(checkCSV.writeAll(anyList())).thenReturn(true);

        boolean actual = checkService.exportCheckToFile(expectedCheck);

        assertTrue(actual);
        verify(checkCSV, times(1)).prepareForExportCSV(expectedStringList);
        verify(checkCSV, times(1)).writeAll(expectedStringList);
        verify(checkService, times(1)).buildCheck(expectedCheck);
    }

    @Test
    void importArgsFromFile() {
        when(checkCSV.readAll(anyString())).thenReturn(expectedStringList);

        List<String> actualList = checkService.importArgsFromFile("");

        assertNotNull(actualList);
        assertSame(expectedStringList, actualList);
    }

}
