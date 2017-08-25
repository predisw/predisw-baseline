package com.predisw.test.practise.Exception;

/**
 * Created by eggnwwg on 6/15/2017.
 */
public class ExceptionTImpl implements ExceptionT {


    // result:Interface doesn't declare exception then impl can not throw
    @Override
    public void testWithoutException()  {

        try {
            throw new Exception("Interface doesn't declare exception but impl do");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //result:Interface throw exception but impl is allowed not to throw
    @Override
    public void testWithExceptoin()  {

        System.out.println("Interface throw exception but impl doesn't");
    }


}
