package com.predisw.test.practise;


import java.util.HashMap;

public class TestAbstract extends Plugin {

  public static void main(String[] args) {

    TestAbstract test_Abstract = new TestAbstract();
    test_Abstract.start(new HashMap<>());
    int _abc = 0;


  }

  public void init(HashMap<String, String> parameters) {
    System.out.println("INIT " + parameters);
  }

}
