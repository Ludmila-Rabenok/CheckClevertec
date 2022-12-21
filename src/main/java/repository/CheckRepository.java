package repository;

import model.Check;

import java.util.List;


public interface CheckRepository {

    Check save(Check check);

    List<Check> getAll();

    Check getById(Integer id);

    Check update(Check check);

    boolean delete(Check check);

}
