package com.timwang.spring.other.transaction;

import org.springframework.stereotype.Component;

/**
 * @author wangjun
 * @date 2020-10-25
 */
@Component
public class StudentService {
    public void saveStudent() {
        TransactionUtil.execute("INSERT INTO STUDENT(SNO,NAME) VALUES (?, ?)", "10008", "Jack");
        // throw new RuntimeException("...");
    }
}
