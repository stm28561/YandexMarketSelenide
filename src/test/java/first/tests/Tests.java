package first.tests;

import org.junit.jupiter.api.*;

public class Tests {

    @BeforeAll
    public static void ball(){
        System.out.println("Before ALL");
    }

    @BeforeEach
    public void beac(){
        System.out.println("BeforeEach");
    }

    @Test
    public void firstTest(){
        System.out.println("StartFirstTst");
        Assertions.assertEquals(1,1,"1 не равно 1");
    }

    @Test
    public void secondTest(){
        System.out.println("secondTest");
        Assertions.assertEquals(1,2,"1 не равно 2");
    }

    @AfterEach
    public void aEAC(){
        System.out.println("AfterEach");
    }

    @AfterAll
    public static void aall(){
        System.out.println("AfterAll");
    }
}
