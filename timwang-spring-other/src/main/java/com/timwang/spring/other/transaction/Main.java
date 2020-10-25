package com.timwang.spring.other.transaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wangjun
 * @date 2020-10-25
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        StudentService bean = ac.getBean(StudentService.class);
        bean.saveStudent();
    }
}
