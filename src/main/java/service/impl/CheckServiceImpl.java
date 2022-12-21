package service.impl;

import exceptions.EntityNotFoundException;
import model.Check;
import model.DiscountCard;
import model.Product;
import repository.CheckRepository;
import service.CheckService;
import service.DiscountCardService;
import service.ProductService;
import util.CSVCheck;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;
    private final ProductService productService;
    private final DiscountCardService discountCardService;
    private final CSVCheck checkCSV = new CSVCheck();
    DecimalFormat formattedDouble = new DecimalFormat("#0.0");

    public CheckServiceImpl(CheckRepository checkRepository, ProductService productService, DiscountCardService discountCardService) {
        this.checkRepository = checkRepository;
        this.productService = productService;
        this.discountCardService = discountCardService;
    }

    @Override
    public Check save(Check check) {
        return checkRepository.save(check);
    }

    @Override
    public List<Check> getAll() {
        return checkRepository.getAll();
    }

    @Override
    public Check getById(Integer id) {
        Check check = null;
        try {
            check = checkRepository.getById(id);
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return check;
    }

    @Override
    public Check update(Check check) {
        return checkRepository.update(check);
    }

    @Override
    public boolean delete(Check check) {
        if (!checkRepository.delete(check)) {
            System.err.println("The check was not deleted");
            return false;
        }
        return true;
    }

    @Override
    public Check createNewCheck(List<String> args) {
        Check check = new Check();
        for (String arg : args) {
            String[] array = arg.split("-");
            try {
                if (array[0].equals("card")) {
                    check.setDiscountCardNumber(Integer.parseInt(array[1]));
                } else
                    check.addProductInHashMap(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            } catch (NumberFormatException e) {
                System.err.println("Invalid arguments");
                e.getStackTrace();
            }
        }
        check.setDate(LocalDateTime.now());
        return save(check);
    }

    @Override
    public boolean exportCheckToFile(Check check) {
        if (!checkCSV.writeAll(checkCSV.prepareForExportCSV(buildCheck(check)))) {
            System.err.println("Error while exporting check to file");
            return false;
        }
        return true;
    }

    @Override
    public List<String> importArgsFromFile(String fileName) {
        List<String> list = checkCSV.readAll(fileName);
        return list;
    }

    @Override
    public List<String> buildCheck(Check check) {
        List<String> list = new ArrayList<>();
        list.add("                Check");
        list.add("                 N" + check.getId());
        list.add("       " + check.getDate());
        list.add("******************************************");
        list.add("Product          Amount     Price    Total");

        HashMap<Integer, Integer> productIdAndAmount = check.getProductIdAndAmount();

        double prices = 0d;
        for (Map.Entry<Integer, Integer> entry : productIdAndAmount.entrySet()) {
            Product product = productService.getById(entry.getKey());
            Integer amount = entry.getValue();
            double price = product.getAuction() && amount > 5 ? product.getPrice() / 100 * 90 : product.getPrice();
            price = price * amount;
            prices = prices + price;
            String line = product.getName() + "          " +
                    amount + "      " +
                    formattedDouble.format(product.getPrice()) + "     " +
                    formattedDouble.format(price);
            list.add(line);
        }

        list.add("******************************************");
        list.add("Price                              " + formattedDouble.format(prices));
        int numberCard = check.getDiscountCardNumber();
        DiscountCard discountCard = discountCardService.getByNumber(numberCard);
        int percentage = discountCard.getPercentage();
        list.add("Discount card N " + numberCard + "                   -" + percentage + "%");
        double totalPrice = prices / 100 * (100 - percentage);
        list.add("Total price                       " + formattedDouble.format(totalPrice));

        return list;
    }

}
