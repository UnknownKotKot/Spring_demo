package com.Spring.stud.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class SpyTest {
    @Spy
    private List<Integer> integerList = new ArrayList<>();

    @Test
    public void spyTest() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        Mockito.verify(integerList).add(1);
        Mockito.verify(integerList).add(2);
        Mockito.verify(integerList).add(3);

        assertEquals(3, integerList.size());

        Mockito.doReturn(100).when(integerList).size();

        assertEquals(100, integerList.size());

        System.out.println(integerList.getClass().getName());
    }
}
