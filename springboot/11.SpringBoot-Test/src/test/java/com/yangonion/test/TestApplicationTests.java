package com.yangonion.test;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

	@BeforeClass
	public static void beforeClassTest(){
		System.out.println("before class test");
	}

	@Before
	public  void beforeTest(){
		System.out.println("before test");
	}

	@After
	public  void afterTest(){
		System.out.println("after test");
	}

	@Test
	public void test1() {
		Assert.assertEquals(4,2+2);
		System.out.println("2+2=4");
	}
	@Test
	public  void test2(){
		Assert.assertTrue(2>0);
		System.out.println(" 2 is bigger than 0");
	}

	@AfterClass
	public static   void afterClasTest(){
		System.out.println("after class test");
	}
}
