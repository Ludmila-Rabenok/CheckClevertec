package dataSource.impl;

import dataSource.CheckDataSource;
import model.Check;

import java.util.ArrayList;

public class CheckDataSourceImpl implements CheckDataSource {
    private static ArrayList<Check> checks = new ArrayList<>();

    @Override
    public ArrayList<Check> getChecks() {
        return checks;
    }

}
