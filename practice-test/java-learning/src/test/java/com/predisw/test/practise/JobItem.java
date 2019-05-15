package com.predisw.test.practise;


/**
 * return (_isLabor) ? _employee.getRate() : _unitPrice;
 * 条件判断就意味着有多态的可能。可以考虑将这种和本类关联不大的抽取出来。
 *
 * 只有employee 的job才需要_employee类
 * 只有计件的job 才需要 才需要_unitPrice
 * 所以可以分别拆成两个子类
 *
 * 这样比较符合开闭原则
 */
public class JobItem {

    private int _unitPrice;
    private int _quantity;
    private Employee _employee;
    private boolean _isLabor;

    public JobItem(int unitPrice, int quantity, boolean isLabor, Employee employee) {
        _unitPrice = unitPrice;
        _quantity = quantity;
        _isLabor = isLabor;
        _employee = employee;
    }

    public int getTotalPrice() {
        return getUnitPrice() * _quantity;
    }

    public int getUnitPrice() {
        return (_isLabor) ? _employee.getRate() : _unitPrice;
    }

    public int getQuantity() {
        return _quantity;
    }

    public Employee getEmployee() {
        return _employee;
    }
}
