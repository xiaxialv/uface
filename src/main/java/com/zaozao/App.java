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
    private static final String APP_ID = "64CCC7F9ABB44F598DC260A1B8629089";
    private static final String APP_KEY = "D53F039BFA2140FB9840DA1095B3BE0E";
    private static final String APP_SECRET = "3EC20F59D66E40CEA4AF6A66156A9570";

    @RequestMapping(value = "/getToken", method = {RequestMethod.GET})
    public String getToken() throws Exception {
        return tokenService.getToken();
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(App.class, args);
    }
}
