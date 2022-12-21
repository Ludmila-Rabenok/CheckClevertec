package service;

import model.Check;

import java.util.List;

public interface CheckService {

    Check save(Check check);

    List<Check> getAll();

    Check getById(Integer id);

    Check update(Check check);

    boolean delete(Check check);

    Check createNewCheck(List<String> args);

    boolean exportCheckToFile(Check check);

    List<String> importArgsFromFile(String file);

    List<String> buildCheck(Check check);

}
