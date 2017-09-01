package earthquake.test.testCode;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 * Created by Administrator on 2017/7/14.
 */
public class baike {
    public  Document getDocument (String url){
                 try {
                         return Jsoup.connect(url).get();
                   } catch (IOException e) {
                        e.printStackTrace();
                     }
               return null;
           }
    public static void main(String[] args){
        baike data = new baike();
        Document doc = data.getDocument("http://baike.baidu.com/item/汶川县");
        // 获取目标HTML代码
        Elements elements1 = doc.select(".level-2");
        // 今天
         Elements elements2 = elements1.get(1).nextElementSibling().select(".para");
        String today = elements2.get(0).text();
        System.out.println(today);
//        // 几号
//        Elements elements3 = elements1.select("h2");
//        String number = elements3.get(0).text();
//        System.out.println(number);
//        // 是否有雨
//         Elements elements4 = elements1.select("[class=wea]");
//        String rain = elements4.get(0).text();
//        System.out.println(rain);
//        // 高的温度
//         Elements elements5 = elements1.select("span");
//        String highTemperature = elements5.get(0).text()+"°C";
//        System.out.println(highTemperature);
//        // 低的温度
//        String lowTemperature = elements5.get(1).text()+"°C";
//        System.out.println(lowTemperature);
//        // 风力
//        Elements elements6 = elements1.select("i");
//        String wind = elements6.get(2).text();
//        System.out.println(wind);
    }
}
