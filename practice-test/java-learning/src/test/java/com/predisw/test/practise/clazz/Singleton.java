package com.predisw.test.practise.clazz;

public class Singleton {
    private volatile Singleton instance;
    private Singleton(){};
    public Singleton getInstance() {
        if(instance == null){  // A
            synchronized (this){
                if(instance == null){ // double check
                    return new Singleton(); // B
                }
            }
        }
        return instance;
    }
}
