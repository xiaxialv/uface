package com.zaozao;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = {"com.zaozao"}, exclude = DataSourceAutoConfiguration.class)
@RestController
public class App {

    private static final String APP_ID = "64CCC7F9ABB44F598DC260A1B8629089";
    private static final String APP_KEY = "D53F039BFA2140FB9840DA1095B3BE0E";
    private static final String APP_SECRET = "3EC20F59D66E40CEA4AF6A66156A9570";

    @RequestMapping(value = "/getToken", method = {RequestMethod.GET})
    public String getToken() throws Exception {
        long timestamp = System.currentTimeMillis();
        //文档 : http://developer.uface.uni-ubi.com/document/18011
        Map<String, String> map = new HashMap<>();
        map.put("appId", APP_ID);
        map.put("appKey", APP_KEY);
        map.put("timestamp", timestamp + "");

        //获取sign
        //MD5-32(appKey+timestamp+appSecret)，MD5加密，32位小写
        String sign =
                EncryptUtil.md532(APP_KEY + timestamp + APP_SECRET);

        map.put("sign", sign);

        System.out.println(JSON.toJSONString(map));
        String result =
                HttpRequestUtil.postParamUrl("http://gs-api.uface.uni-ubi.com/v1/" + APP_ID + "/auth", map);
        System.out.println(result);
        return "xiaxia.html";
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(App.class, args);
    }
}
