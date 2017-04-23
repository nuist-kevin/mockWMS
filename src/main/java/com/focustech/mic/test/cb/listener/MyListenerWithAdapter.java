package com.focustech.mic.test.cb.listener;

/**
 * Created by caiwen on 2017/4/20.
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
