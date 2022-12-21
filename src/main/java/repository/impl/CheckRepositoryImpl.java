package repository.impl;

import dataSource.CheckDataSource;
import exceptions.EntityNotFoundException;
import model.Check;
import repository.CheckRepository;
import util.IdGenerator;

import java.util.List;

public class CheckRepositoryImpl implements CheckRepository {
    private final CheckDataSource checkDataSource;

    public CheckRepositoryImpl(CheckDataSource checkDataSource) {
        this.checkDataSource = checkDataSource;
    }

    @Override
    public Check save(Check check) {
        check.setId(IdGenerator.generateCheckId());
        getAll().add(check);
        return check;
    }

    @Override
    public List<Check> getAll() {
        return checkDataSource.getChecks();
    }

    @Override
    public Check getById(Integer id) throws EntityNotFoundException {
        Check check = getAll().stream()
                .filter(x -> id.equals(x.getId()))
                .findFirst()
                .orElse(null);
        if (check == null) {
            throw new EntityNotFoundException("No such check found");
        }
        return check;
    }

    @Override
    public Check update(Check check) {
        Check checkForUpdate = getById(check.getId());
        checkForUpdate.setDate(check.getDate());
        checkForUpdate.setDiscountCardNumber(check.getDiscountCardNumber());
        checkForUpdate.setProductIdAndAmount(check.getProductIdAndAmount());
        return checkForUpdate;
    }

    @Override
    public boolean delete(Check check) {
        return getAll().remove(check);
    }

}
