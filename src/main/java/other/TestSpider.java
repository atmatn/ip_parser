package other;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import com.google.gson.Gson;
import java.util.List;

@Gecco(matchUrl = "http://analyzer2.corp.youdao.com/detail.html?dataSource=fanyiguan&timeFrom=2019-04-29&timeTo=2019-04-29&uidField=imei&chartType=pv",pipelines = "other.TestSpider")
public class TestSpider implements HtmlBean{
    @HtmlField(cssPath = "a.link-2")
    private List<String> namelist;

    @Href(value="href")
    @HtmlField(cssPath = "a.link-2")
    private List<String> idlist;

    @HtmlField(cssPath = "ul.pagination li")
    private List<IndexPageEntity> pageList;

    @Request
    private HttpRequest request;//请求

    public List<String> getNamelist() {
        return namelist;
    }

    public void setNamelist(List<String> namelist) {
        this.namelist = namelist;
    }

    public List<String> getIdlist() {
        return idlist;
    }

    public void setIdlist(List<String> idlist) {
        this.idlist = idlist;
    }

    public List<IndexPageEntity> getPageList() {
        return pageList;
    }

    public void setPageList(List<IndexPageEntity> pageList) {
        this.pageList = pageList;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }
}
@PipelineName(value="other.TestSpider")
class DoSpider implements Pipeline<TestSpider> {
    public void process(TestSpider testSpider) {
        if(testSpider.getIdlist() != null){
            System.out.println(testSpider.getIdlist().toString());
        }else{
            System.out.println("nothing");
        }
        System.out.println("\n"+"result:"+new Gson().toJson(testSpider.getPageList()));
    }
}
class JustDoIt{
    public static void main(String[] args) {
        DoSpider doSpider=new DoSpider();
        TestSpider testSpider=new TestSpider();
        doSpider.process(testSpider);
    }


}