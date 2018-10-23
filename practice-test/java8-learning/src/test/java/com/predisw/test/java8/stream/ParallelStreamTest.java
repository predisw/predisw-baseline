package com.predisw.test.java8.stream;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import org.junit.Test;

public class ParallelStreamTest {


    @Test
    public void testParallelPerformanceStream() {

        List<Integer> list = new ArrayList<>();
        // 将10000-1存入list中
        for (int i = 500000; i >= 1; i--) {
            list.add(i);
        }

        //-------------------- parallelStream ---------------

        LocalTime startP = LocalTime.now();

        long sump = list.parallelStream().reduce(0,Integer::sum);

        // 终止时间
        LocalTime endP = LocalTime.now();

        // 时间间隔
        Duration durationP = Duration.between(startP, endP);
        // 输出时间间隔毫秒值
        System.out.println("the parallelStream cost "+durationP.toMillis() + " sum is "+sump);

        //-------------------- stream ---------------

        // 起始时间
        LocalTime start = LocalTime.now();

        long sum = list.stream().reduce(0,Integer::sum);

        // 终止时间
        LocalTime end = LocalTime.now();

        // 时间间隔
        Duration duration = Duration.between(start, end);
        // 输出时间间隔毫秒值
        System.out.println("the stream cost " +duration.toMillis()+ " sum is "+sum);

    }



    @Test
    public  void testTheIssueOfParallelStream() throws InterruptedException {

        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> listOfIntegers =
                new ArrayList<>(Arrays.asList(intArray));
        List<Integer> parallelStorage = new ArrayList<>(); //Collections.synchronizedList(new ArrayList<>());
        listOfIntegers
                .parallelStream()
                // Don't do this! It uses a stateful lambda expression.
                .map(e -> {
                    parallelStorage.add(e);
                    return e;
                })
                .forEachOrdered(e -> System.out.print(e + " "));
        // may output :null 8 7 4 5 1 6 2
        // may output :4 1 5 8 7 3
        System.out.println();
        // the issue: some elements are override. the output will less than original like
        parallelStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();
        System.out.println("Sleep 5 sec");
        TimeUnit.SECONDS.sleep(5); // still the same
        parallelStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));
    }


    @Test
    public void streamParallelIsNotThreadSafe(){
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        Lock lock = new ReentrantLock();

        IntStream.range(0, 10000).forEach(list1::add);
        IntStream.range(0, 10000).parallel().forEach(list2::add);
        IntStream.range(0, 10000).forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            }finally {
                lock.unlock();
            }
        });

        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁并行执行的大小：" + list3.size());

        // output:
        //串行执行的大小：10000
        //并行执行的大小：9729
        //加锁并行执行的大小：10000
    }



}
