package com.cj.cga101g1.util;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UnitTestDemoTest {

  static int countAfterEach = 0;
  static int countAfterAll = 0;

    @AfterEach
    public void afterEach(){
        countAfterEach++;
        System.out.println("開始執行@AfterEach");
    }


    @AfterAll
    public static void afterAll(){
        countAfterAll++;
        System.out.println("開始執行@AfterAll");
    }


    @Disabled
    @Test
    public void test2(){
        System.out.println("執行test2");
    }

    @DisplayName("自訂義test()的名稱")
    @Test
    public void test(){
        System.out.println("執行test");
        UnitTestDemo u = new UnitTestDemo();
        int result = u.calculate(3,3);
        assertTrue(result==1);
        assertNotNull(result);
        assertEquals(1,result,"錯誤原因");
        assertThrowsExactly(ArithmeticException.class,()->{
            u.calculate(3,0);
        });
    }

}