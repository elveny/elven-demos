/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.mockito;

import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyInt;


/**
 * @author qiusheng.wu
 * @Filename MockitoTest1.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/1/25 18:33</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
//@RunWith(MockitoJUnitRunner.class)
public class MockitoTest1 {/*

    @Test
    public void test1(){
        // mock creation
        List mockedList = mock(List.class);

        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();


    }

    @Test
    public void test2(){
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");

        // the following prints "first"
        System.out.println(mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
    }

    @Test
    public void test3(){
        //You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        System.out.println(mockedList.get(0));

//        //following throws runtime exception
//        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

    @Test
    public void test4(){
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
//        when(mockedList.contains(argThat(isValid()))).thenReturn("element");

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());

        //argument matchers can also be written as Java 8 Lambdas
//        verify(mockedList).add(argThat(someString -> someString.length() > 5));
    }

    @Mock
    private List list;

    @Before
    public void init(){
//        list = mock(List.class);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void test5(){

        list.add(1);
        verify(list).add(1);
    }

    @Mock
    private TestController testController;
    @Test
    public void test6(){
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(testController).helloUser1(argument.capture());
        assertEquals("John", argument.getValue().getName());
    }*/

}