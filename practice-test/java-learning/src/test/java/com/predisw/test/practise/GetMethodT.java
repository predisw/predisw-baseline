package com.predisw.test.practise;

/**
 * Created by eggnwwg on 2/6/2018.
 */
public class GetMethodT {

    public static void main(String[] args) {
        InnerA aObject = new GetMethodT.InnerA("a","b");

        String c = aObject.getA();
        System.out.println(c);
        c = null;
        System.out.println(c);

        System.out.println(aObject.getA());
    }





    static class InnerA{
        public InnerA(String a,String b){
            this.a = a;
            this.b = b;
        }

        String a;
        String b;

        String getA(){
            return a;
        }
        String getB(){
            return b;
        }
    }

}
