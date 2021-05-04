package myt;

import com.chuang.tauceti.httpclient.Https;
import com.chuang.tauceti.httpclient.Request;
import com.chuang.tauceti.httpclient.async.AsyncHttpClient;
import com.chuang.tauceti.httpclient.sync.HttpClient;
import org.junit.Test;

import java.io.IOException;

public class PushTest {

    @Test
    public void testPush() throws IOException {
        Request.Post("https://data.zhanzhang.sm.cn/push?site=www.wangfengji.com&user_name=yongchuangyabo@qq.com&resource_name=mip_add&token=TI_37fa6dd9ca49e30fef58472691b41ef5")
                .header("user-agent", "curl/7.12.1")
                .header("Content-Type", "text/plain")
                .body("http://www.wangfengji.com")
                .build()
                .asyncExecuteAsString()
                .thenAccept(System.out::println)
                .join();



        Request.Get("http://www.baidu.com").build().executeAsString();
    }

    @Test
    public void test302() {
        Request.Get("http://www.gai43.com/")
                .header("user-agent", "Mozilla/4.0 (compatible; Baidu-Spider 8.0; Windows NT 5.1; Trident/4.0)")
                .build().asyncExecuteAsString().thenAccept(System.out::println).join();

    }




}
