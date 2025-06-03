package com.seleniumframework.test;

import org.testng.annotations.Test;

import com.seleniumframework.base.BaseTest;

public class DummyTest extends BaseTest{
	
	@Test
	public void test1() {
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
	}
	

}
