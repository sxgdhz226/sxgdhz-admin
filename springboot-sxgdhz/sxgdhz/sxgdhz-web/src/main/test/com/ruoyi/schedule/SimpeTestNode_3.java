package com.ruoyi.schedule;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sxx
 */
public class SimpeTestNode_3 {

	public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        Thread.sleep(Long.MAX_VALUE);
    }

}
