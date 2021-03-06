package com.zaozao.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zaozao.util.EncryptUtil;
import com.zaozao.util.HttpRequestUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sidney 2019-01-08.
 */
@Service
@EnableScheduling
public class TokenService {
    private String token;

    private static final String APP_ID = "64CCC7F9ABB44F598DC260A1B8629089";
    private static final String APP_KEY = "D53F039BFA2140FB9840DA1095B3BE0E";
    private static final String APP_SECRET = "3EC20F59D66E40CEA4AF6A66156A9570";

    @PostConstruct
    @Scheduled(cron = "0 0 */10 * * ?")
    void init() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        long timestamp = System.currentTimeMillis();
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
        JSONObject jsonObject = JSON.parseObject(result);
        token = jsonObject.getString("data");
    }

    public String getToken() {
        return token;
    }
}
