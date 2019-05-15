package com.predisw.test.practise.refactor;

import com.predisw.test.practise.Employee;

/**
 * 这是员工类的jobItem
 */
public class LaborItem extends JobItem {

    private Employee _employee;

    public LaborItem(int quantity, Employee employee) {
        super(quantity);
        this._employee = employee;
    }

    @Override
    public int getUnitPrice() {
        return _employee.getRate();
    }

}
