package com.zaozao.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zaozao.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sidney 2019-01-08.
 */
@Service
public class PersonService {
    @Autowired
    private TokenService tokenService;
    private static final String APP_ID = "64CCC7F9ABB44F598DC260A1B8629089";

    public String getPerson(String guid) {
        Map<String, String> map = new HashMap<>();
        String token = tokenService.getToken();
        map.put("appId", APP_ID);
        map.put("token", token);
        if (guid!=null||!guid.equals("")) {
            map.put("guid",guid);
        }
        System.out.println(JSON.toJSONString(map));
        //URL：http://gs-api.uface.uni-ubi.com/v1/{appId}/person/{guid}，method：GET
        String result =
                HttpRequestUtil.get("http://gs-api.uface.uni-ubi.com/v1/" + APP_ID + "/person/"+guid+"?token="+token);
        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println(jsonObject);
        return result;
    }

}
