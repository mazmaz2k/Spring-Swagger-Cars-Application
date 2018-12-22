package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;
import junit.framework.TestSuite;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringSwaggerCarsApplicationTests 
	extends TestCase {

//	@Test
//	public void contextLoads() {
//		
//		
//		
//	}
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SpringSwaggerCarsApplicationTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static TestSuite suite()
    {
        return new TestSuite(SpringSwaggerCarsApplicationTests.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
	

}
