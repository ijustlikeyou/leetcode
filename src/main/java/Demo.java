import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Demo {
    public static void main(String[] args) throws IOException {
        //要爬取的网站
        String url = "https://www.qidian.com/search?kw=完美世界";
        //获得一个和网站的链接，注意是Jsoup的connect
        Connection connect = Jsoup.connect(url);
        //获得该网站的Document对象
        Document document = connect.get();
        int cnt = 1;
        //我们可以通过对Document对象的select方法获得具体的文本内容
        //下面的意思是获得.bool-img-text这个类下的 ul 下的 li
        Elements rootselect = document.select(".book-img-text ul li");
        for(Element ele : rootselect){
            //然后获得a标签里面具体的内容
            Elements novelname = ele.select(".book-mid-info h4 a");
            String name  = novelname.text();

            Elements author = ele.select(".book-mid-info p a");
            String authorname = author.first().text();

            Elements sumadvice = ele.select(".total p");
            String sum = sumadvice.last().text();

            System.out.println("书名:"+name+" 作者:"+authorname+" 推荐量:"+sum);
        }
    }
}
