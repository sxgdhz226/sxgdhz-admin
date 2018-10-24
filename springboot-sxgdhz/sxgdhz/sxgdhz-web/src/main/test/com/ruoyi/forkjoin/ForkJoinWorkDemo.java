package com.ruoyi.forkjoin;

import com.ruoyi.common.forkjoin.ForkJoinWork;
import org.junit.Test;

import javax.persistence.Temporal;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Java 8 中将并行流进行了优化，我们可以很容易的对数据进行并行流的操作，
 * Stream API可以声明性的通过parallel()与sequential()在并行流与穿行流中随意切换！
 * Java 8引入了三个原始类型特化流接口来解决这个问题： IntStream 、 DoubleStream 和 LongStream
 * 映射到数值流：将流转换为特化版本的常用方法是mapToInt、mapToDouble和mapToLong
 */
public class ForkJoinWorkDemo {
    @Test
    public  void test() {
        //ForkJoin实现
        long l = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();//实现ForkJoin 就必须有ForkJoinPool的支持
        ForkJoinTask<Long> task = new ForkJoinWork(0L,10000000000L);//参数为起始值与结束值
        Long invoke = forkJoinPool.invoke(task);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + invoke+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 76474
    }

    @Test
    public void test2(){
        //普通线程实现
        Long x = 0L;
        Long y = 10000000000L;
        long l = System.currentTimeMillis();
        for (Long i = 0L; i <= y; i++) {
            x+=i;
        }
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + x+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 160939
    }

    /**
     * reduce  归约 将流规约成一个值
     * reduce(0, Long::sum)
     * 0 是初始值  Long::sum是一个新值
     * parallel（）并行
     */
    @Test
    public void test3(){
        //Java 8 并行流的实现
        long l = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, Long::sum);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + reduce+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 15531
    }
}
