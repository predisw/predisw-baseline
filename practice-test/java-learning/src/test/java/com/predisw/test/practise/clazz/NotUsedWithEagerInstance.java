package com.predisw.test.practise.clazz;

public class NotUsedWithEagerInstance {

    private static NotUsedWithEagerInstance  notUsed = new NotUsedWithEagerInstance();

    static {
        System.out.println("NotUsedWithEagerInstance static block is loaded");
    }

    private NotUsedWithEagerInstance(){
        System.out.println("NotUsedWithEagerInstance is instanced");
    }


    public static NotUsedWithEagerInstance getNotUsed() {
        return notUsed;
    }
}
