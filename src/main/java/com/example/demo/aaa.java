/**
 * @program: demo
 * @description:
 * @author: KaiTao.wu
 * @create: 2019-09-01 20:46
 **/
package com.example.demo;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class aaa {
    AtomicInteger  atomicInteger = new AtomicInteger();
    public static   Map<String,String> map = new ConcurrentHashMap<>();
    public static   boolean is = false;



    public static void main(String[] args) throws IOException {
        b();
    }

    public static void a() throws IOException {
        System.in.read();

        long t = System.currentTimeMillis();
        for (int i = 0; i < Long.MAX_VALUE/10000; i++) {
            new aaa();
        }
        System.out.print((System.currentTimeMillis()-t)+"ms");
        System.in.read();
    }
    public static void b(){
        map.put("2","2");
        new Thread(()->{
//
            while (!is){
                System.out.println(is);
            }
            System.out.println(is);
        }).start();
        try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        new Thread(()->{
            Map<String, String> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
            objectObjectConcurrentHashMap.put("1","1");
            map = objectObjectConcurrentHashMap;
            is = true;
            System.out.print("done");
        }).start();
    }

}
