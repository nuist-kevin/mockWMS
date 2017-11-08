package com.focustech.mic.test.cb.listener;

/**
 * @author caiwen
 */
public class MyListenerWithAdapter {

  public void handleMessage(String message) {
    try {

      System.out.println(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
