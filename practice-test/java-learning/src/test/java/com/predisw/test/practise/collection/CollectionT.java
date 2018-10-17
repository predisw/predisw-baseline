package com.predisw.test.practise.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CollectionT {


    @Test
    public void subClassIfInstanceOfCollection(){

        List<String> stringList = new ArrayList<>();

        Assertions.assertThat(stringList instanceof Collection).isTrue();

    }


    @Test
    public void retainAllTest(){
        List<Long> longList = Lists.newArrayList(1L,2L,30000L,1L,2L);
        List<Long> longList2 = Lists.newArrayList(2L,30000L);
        longList.retainAll(longList2);

        System.out.println(Arrays.toString(longList.toArray()));

    }



    @Test
    public void removeAllTest(){
        List<Long> longList = Lists.newArrayList(1L,2L,30000L,30000L,1L);
        Set<Long> longSet = new HashSet<>(longList);
        longList.removeAll(longSet);

        System.out.println(Arrays.toString(longList.toArray()));

    }

    @Test
    public void getDuplicateElementsTest(){
        List<Long> longList = Lists.newArrayList(1L,2L,30000L,30000L,1L);
        List<Long> repeatLong = getDuplicateElements(longList);
        System.out.println(repeatLong);
    }



    @Test
    public void getDuplicateElementsWithMultiSetTest(){
        List<Long> longList = Lists.newArrayList(1L,2L,30000L,30000L,1L);
        List<Long> repeatLong = getDuplicateElementsWithMultiSet(longList);
        System.out.println(repeatLong);
        System.out.println(repeatLong);
    }




    @Test
    public void getDuplicateElementsPerformanceTest(){
        long before = System.currentTimeMillis();
        List<Long> longList = getLongListWithValue(1000000);
        long after = System.currentTimeMillis();

        System.out.println("generate list time : "+(after - before));

//        longList.add(3L);
//        longList.add(3000L);
//        longList.add(30000L);
//        longList.set(40000,300000L);
//        longList.add(400000,300000L);
////        longList.add(4000000,100000L);
//


        long beforeGetDuplicated = System.currentTimeMillis();
        List<Long> repeatLong = getDuplicateElements(longList);
        long afterGetDuplicated = System.currentTimeMillis();
        System.out.println("generate list time for JDK implement : "+(afterGetDuplicated - beforeGetDuplicated));
        System.out.println(repeatLong);


        long beforeGetDuplicatedWithMultiSet = System.currentTimeMillis();
        repeatLong = getDuplicateElementsWithMultiSet(longList);
        long afterGetDuplicatedWithMultiSet = System.currentTimeMillis();
        System.out.println("generate list time for HashMultiset implement: "+(afterGetDuplicatedWithMultiSet - beforeGetDuplicatedWithMultiSet));

        System.out.println(repeatLong);
    }



    /**
     * better performance than HashMultiset implement
     * @param list
     * @param <E>
     * @return
     */
    public static <E> List<E> getDuplicateElements(List<E> list) {
        return list.stream()                              // list 对应的 Stream
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream()                   // 所有 entry 对应的 Stream
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey())          // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList());         // 转化为 List
    }

    public static <E> List<E> getDuplicateElementsWithMultiSet(List<E> list) {
        List<E> results = HashMultiset.create(list)
                .entrySet().stream()
                .filter(w -> w.getCount() > 1)
                .map(entry -> entry.getElement())
                .collect(Collectors.toList());
        return results;
    }

    private List<Long> getLongListWithValue(int capacity){

        List<Long> longList = new ArrayList<>(capacity);
        for(int i =0;i < capacity;i++){
            longList.add(Long.valueOf(i));
        }
        return longList;

    }

}
