package com.zaozao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zaozao.response.CommonReturnType;
import com.zaozao.service.PersonService;
import com.zaozao.service.TokenService;
import com.zaozao.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sidney 2019-01-08.
 */
@Controller("personController")
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private TokenService tokenService;
    private static final String APP_ID = "64CCC7F9ABB44F598DC260A1B8629089";
    private static final String SECRET = "64CCC7F9ABB44F598DC260A1B8629089";


    @RequestMapping(value = "/getPerson", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getPerson(@RequestParam("guid") String guid) throws Exception {
        String result = personService.getPerson(guid);
        return CommonReturnType.create(result);
    }


    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType login(@RequestParam("code") String code) throws Exception {
        Map<String, Object> result = new HashMap<>();

        String s = HttpRequestUtil
                .get("https://api.weixin.qq.com/sns/jscode2session?appid=" + APP_ID + "&secret=" + SECRET + "&js_code="
                        + code + "&grant_type=authorization_code");

        JSONObject jsonObject = JSON.parseObject(s);

        result.put("openid",jsonObject.getString("openid"));
        result.put("session_key",jsonObject.getString("session_key"));
        result.put("token",tokenService.getToken());

        return CommonReturnType.create(result);
    }


}
