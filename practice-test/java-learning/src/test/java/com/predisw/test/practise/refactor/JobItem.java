package com.predisw.test.practise.refactor;


/**
 * 原来的jobItem 变成抽象类
 *
 */
public abstract class JobItem {
    private int _quantity;

    JobItem(int _quantity) {
        this._quantity = _quantity;
    }

    public int getTotalPrice(){
        return getUnitPrice() * getQuantity();
    }

    public abstract int getUnitPrice();

    public int getQuantity(){
        return this._quantity;
    }

}
