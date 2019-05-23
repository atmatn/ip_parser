package other;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientTest {
    public static void main(String[] args) {
        CloseableHttpClient closeableHttpClient=HttpClients.createDefault();

        String url="https://www.zhihu.com/";

        HttpGet httpGet=new HttpGet(url);

        System.out.println(httpGet);

        HttpPost httpPost=new HttpPost(url);

        System.out.println(httpPost);

        try {
            CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet);
           // System.out.println(closeableHttpResponse);

            int status=closeableHttpResponse.getStatusLine().getStatusCode();
            if(status == 200){
                String entity = EntityUtils.toString(closeableHttpResponse.getEntity());
                System.out.println(entity);
                EntityUtils.consume(closeableHttpResponse.getEntity());
            }else{
                EntityUtils.consume(closeableHttpResponse.getEntity());
            }
            closeableHttpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
