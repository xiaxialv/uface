package com.zaozao;

import com.uniubi.ground.auth.token.AppAuthParam;
import com.uniubi.ground.auth.token.TokenFetcher;
import com.uniubi.ground.entity.ContentResult;
import com.uniubi.ground.entity.DeviceSetting;
import com.uniubi.ground.service.RDeviceService;
import com.uniubi.ground.service.impl.DeviceService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static final String APP_ID = "64CCC7F9ABB44F598DC260A1B8629089";
    private static final String APP_KEY = "D53F039BFA2140FB9840DA1095B3BE0E";
    private static final String APP_SECRET = "3EC20F59D66E40CEA4AF6A66156A9570";
    public static void main(String[] args){


        AppAuthParam appAuthParam = new AppAuthParam(APP_KEY,APP_SECRET,APP_ID);
        TokenFetcher.init(appAuthParam);
        // 此初始化行为主要是为了获取权限相关的信息,如不使用
        // BaseAuthRemoteService(下文将会提到)的子类,可以不进行此权限信息初始化
        // 生产环境下可以将init的行为配置到spring的容器中来进行初始化
        // 初始化成功后,接下来每次调用业务方法,都会自动为发起的请求附上相应的权限信息,而无需手工传入
        RDeviceService deviceService = new DeviceService();
        ContentResult<DeviceSetting> setting = deviceService.getSetting("deviceKey");//原接口需要显示传入权限凭证,而这里不用
        //应用结束时调用,关闭定时自动获取token的线程池,如webApp关闭时
        TokenFetcher.shutdown();
    }
    /**
     * Rigorous Test :-)
     */
    @Test

    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
