package earthquake.site.services;

import earthquake.site.forms.Status;
import earthquake.site.repositories.UrlsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.util.HashMap;

/**
 * Created by fly on 2016/10/13.
 */

@Service
public class CrawlerService {

    @Inject
    private UrlsRepository urlsRepository;

    private static final Logger log = LogManager.getLogger();
    private static final Logger schedulingLogger = LogManager.getLogger(log.getName() + ".[scheduling]");

    public int startCrawler() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String crawlerPath = rootPath.substring(1) + "crawler/setupspider.exe";
        try {
            Runtime.getRuntime().exec(crawlerPath);
            return Status.SUCCESS.getValue();
        } catch (Exception e) {
            log.error(e);
            return Status.FAIL.getValue();
        }
    }

    public int killCrawler() {
        try {
            Runtime.getRuntime().exec("TaskKill /f /fi \"IMAGENAME eq setupspider.exe\"");
            return Status.SUCCESS.getValue();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
            return Status.FAIL.getValue();
        }
    }

    public int updateParams(String urls, String keywords, String allwords, String denywords) {
        HashMap<String, String> statusMap = new HashMap<>();
        int keywordStatus = setSetting(keywords, allwords, denywords);
        if (keywordStatus == Status.SUCCESS.getValue()) {
        //todo-fly 加入url仓库读写
            return Status.SUCCESS.getValue();
        } else {
            return Status.FAIL.getValue();
        }
    }

    /**
     * 写入mysql_setting.txt
     *
     * @param host
     * @param port
     * @param user
     * @param password
     * @return
     */
    public int setDatasource(String host, String port, String user, String password) {
        String out = "";
        out += "host=" + host + "\r\n";
        out += "port=" + port + "\r\n";
        out += "user=" + user + "\r\n";
        out += "password=" + password + "\r\n";
        out += "database=earthquake\r\n";
        out += "webpages_table=earthquake_webpages\r\n";
        out += "urls_table=earthquake_url";
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String settingPath = rootPath + "crawler/mysql_setting.txt";
        FileWriter fw;
        try {
            fw = new FileWriter(settingPath);
            fw.write(out);
            fw.close();
            return Status.SUCCESS.getValue();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
            return Status.FAIL.getValue();
        }
    }

    /**
     * 写入setting.txt
     *
     * @param keywords
     * @param allwords
     * @param denywords
     * @return
     */
    public int setSetting(String keywords, String allwords, String denywords) {
        String out = "";
        out += "keywords=" + keywords;
        out += "allwords=" + allwords;
        out += "denywords=" + denywords;
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String settingPath = rootPath + "crawler/setting.txt";
        FileWriter fw;
        try {
            fw = new FileWriter(settingPath);
            fw.write(out);
            fw.close();
            return Status.SUCCESS.getValue();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
            return Status.FAIL.getValue();
        }
    }

    /**
     * 读取setting.txt以键值对返回
     *
     * @return
     */
    public String[] readSetting() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String settingPath = rootPath.substring(1) + "crawler/setting.txt";
        String result = "";
        String[] lines = new String[2];
        try {
            File file = new File(settingPath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf8");
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    result += lineTxt;
                }
                read.close();
                lines = result.split(";");
            } else {
                log.error("找不到指定的文件");
            }
        } catch (Exception e) {
            log.error("读取文件内容出错");
            e.printStackTrace();
        }
        return lines;
    }
}
