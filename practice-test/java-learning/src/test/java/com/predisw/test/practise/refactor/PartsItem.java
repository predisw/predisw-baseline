package com.predisw.test.practise.refactor;

/**
 * 计件的job 才需要_unitPrice， 所以也抽成一个子类 of jobItem
 */
public class PartsItem  extends JobItem{
    private int _unitPrice;

    public PartsItem(int quantity, int unitPrice) {
        super(quantity);
        this._unitPrice = unitPrice;
    }

    @Override
    public int getUnitPrice() {
        return _unitPrice;
    }

}
