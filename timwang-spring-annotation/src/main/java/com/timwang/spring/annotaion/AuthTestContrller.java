package com.timwang.spring.annotaion;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

/**
 * @author wangjun
 * @date 2019-12-13
 */
public class AuthTestContrller {
    @EnableAuth
    @RequestMapping("/userCollectCityInfo")
    public Response getUserCollectCityInfos(HttpServletRequest request) {
        //..
        return null;
    }
}
