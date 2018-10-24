package com.ruoyi.schedule;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sxx
 */
public class SimpeTestNode_2 {

	public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext/applicationContext1.xml");
        Thread.sleep(Long.MAX_VALUE);
    }

}
