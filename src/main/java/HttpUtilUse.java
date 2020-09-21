import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
public class HttpUtilUse {
    public static void main(String[] args) throws ClientProtocolException, IOException {
        String URL = "https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2019-12-31&leftTicketDTO.from_station=SHH&leftTicketDTO.to_station=ZZF&purpose_codes=ADULT";
        //URL="http://www.bjtzh.gov.cn/bjtz/home/jrcj/index.shtml";
        //创建模拟一个客户端
        CloseableHttpClient client = HttpClients.createDefault();
        //创建一个网站的连接对象
        HttpGet httpGet = new HttpGet(URL);
        //设置一些Header信息，说是从哪个浏览器访问的
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 SE 2.X MetaSr 1.0");

        httpGet.addHeader(new BasicHeader("Cookie", "JSESSIONID=C2F4A1A80C606DCA65B8C74D24BF159A; BIGipServerotn=888144394.38945.0000; RAIL_EXPIRATION=1577839597915; RAIL_DEVICEID=lD4LABBhtYEuENd3jba4zgGdkg7BqbBM8LVBW1l7YhN1aVy_rEgpLtCM9oZzC0emr6idF83KznKNeI2EcZjhTi7cdysDu3nrWVEuWXc5odHCESJ0AhVI2t6mb6X0K1GvGIsVHgObiff9IqFvHh1l93g4ClCDDBV1; BIGipServerpool_passport=317522442.50215.0000; route=6f50b51faa11b987e576cdb301e545c4; _jc_save_fromStation=%u4E0A%u6D77%2CSHH; _jc_save_toStation=%u90D1%u5DDE%2CZZF; _jc_save_fromDate=2019-12-31; _jc_save_toDate=2019-12-28; _jc_save_wfdc_flag=dc"));
        //让客户端开始访问这个网站
        CloseableHttpResponse response = client.execute(httpGet);
        //获取到了该网站页面的html
        HttpEntity entity = response.getEntity();
        //把html转化成String
        String html = EntityUtils.toString(entity,"utf-8");
        System.out.println(html);
        System.out.println("successful");
    }
}