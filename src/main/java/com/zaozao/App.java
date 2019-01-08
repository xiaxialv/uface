package com.zaozao;

import com.zaozao.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = {"com.zaozao"}, exclude = DataSourceAutoConfiguration.class)
@RestController
public class App {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/getToken", method = {RequestMethod.GET})
    public String getToken() throws Exception {
        return tokenService.getToken();
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(App.class, args);
    }
}
