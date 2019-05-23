package other;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCrawler {
    static final Logger logger = LoggerFactory.getLogger(BookCrawler.class);

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        //创建HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //要爬取的URL
        String url = "http://search.jd.com/Search?keyword=Python&enc=utf-8&qrst=1&rt=1&stop=1&book=y&pt=1&vt=2&cid2=3287&stock=1&click=3";

        //以Get方法请求页面内容
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        //爬取的图书列表
        List<Book> books = new ArrayList<Book>();

        //获取响应码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if(statusCode == 200){
            String entity = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

            //采用Jsoup解析抓取到的网页
            Document doc = Jsoup.parse(entity);

            //获取HTML标签中的内容
            Elements elements = doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
            for (Element ele : elements){
                String bookID = ele.attr("data-sku");
                String bookPrice = ele.select("div[class=p-price]").select("strong").select("i").text();
                String bookName = ele.select("div[class=p-name]").select("em").text();

                //从中提取出书籍对象
                Book book = new Book();
                book.setBookID(bookID);
                book.setBookName(bookName);
                book.setBookPrice(bookPrice);
                books.add(book);
            }
        }
        EntityUtils.consume(httpResponse.getEntity());

        for(int i=0;i<books.size();i++){
            System.out.println(books.get(i));
        }
        httpResponse.close();
    }
}