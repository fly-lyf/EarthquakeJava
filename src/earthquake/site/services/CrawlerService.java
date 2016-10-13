package earthquake.site.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by fly on 2016/10/13.
 */

@Service
public class CrawlerService {

    private String rootPath;
    private static final Logger log = LogManager.getLogger();
    private static final Logger schedulingLogger = LogManager.getLogger(log.getName() + ".[scheduling]");

    public String startCrawler() {
//        判断是否有进程在执行，写入数据库文件

        rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String crawlerPath = rootPath.substring(1) + "crawler/setupspider.exe";
        try {
            Runtime.getRuntime().exec(crawlerPath);
            return "success";
        } catch (Exception e) {
            log.error(e);
            return "fail";
        }
    }

    public String killCrawler() {
        try {
            Runtime.getRuntime().exec("TaskKill /f /fi \"IMAGENAME eq setupspider.exe\"");
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
            return "fail";
        }
    }

    public String setDatasource(String host, String port, String user, String password) {
        String out = "";
        out += "host="+host+"\r\n";
        out += "port="+port+"\r\n";
        out += "user="+user+"\r\n";
        out += "password="+password+"\r\n";
        out += "database=earthquake\r\n";
        out += "webpages_table=earthquake_webpages\r\n";
        out += "urls_table=earthquake_url";
        rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String settingPath = rootPath + "crawler/mysql_setting.txt";
        FileWriter fw;
        try {
            fw = new FileWriter(settingPath);
            fw.write(out);
            fw.close();
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
            return "fail";
        }
    }

    public String setKeywords(String keywords, String allwords, String denywords) {
        String out = "";
        out += "keywords="+keywords;
        out += "allwords="+allwords;
        out += "denywords="+denywords;
        rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String settingPath = rootPath + "crawler/setting.txt";
        FileWriter fw;
        try {
            fw = new FileWriter(settingPath);
            fw.write(out);
            fw.close();
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
            return "fail";
        }
    }
}
