package com.liurui;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
   public  void test(){
        final Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("{} {}", "hello", "张三");
    }
}
