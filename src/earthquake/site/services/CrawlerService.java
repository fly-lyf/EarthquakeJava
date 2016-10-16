package earthquake.site.services;

import earthquake.site.forms.CrawlerForm;
import earthquake.site.forms.Status;
import earthquake.site.repositories.UrlsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;

/**
 * Created by fly on 2016/10/13.
 */

@Service
public class CrawlerService {

    private static final Logger log = LogManager.getLogger();

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

    public int updateParams(CrawlerForm form) {
        String time;
        String keywords = "地震";
        if (!form.getTimeSeq().equals("") && form.getTimeStr().equals("")) {
            time = form.getTimeSeq();
        } else {
            String[] splits = form.getTimeStr().split("-");
            if (splits[1].substring(0, 1).equals("0")) {
                splits[1] = splits[1].substring(1, splits[1].length());
            }
            if (splits[2].substring(0, 1).equals("0")) {
                splits[2] = splits[2].substring(1, splits[2].length());
            }
            time = splits[0] + "年" + splits[1] + "月" + splits[2] + "日";
            keywords = form.getKeywords();
        }
        int keywordStatus = setSetting(time, keywords);
        if (keywordStatus == Status.SUCCESS.getValue()) {
            return Status.SUCCESS.getValue();
        } else {
            return Status.FAIL.getValue();
        }
    }

    /**
     * 写入setting.txt
     *
     * @param time
     * @param keywords
     * @return
     */
    public int setSetting(String time, String keywords) {
        String out = time + ";" + keywords;
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String settingPath = rootPath + "crawler/setting.txt";
        try {
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(settingPath), "UTF-8");
            writer.write(out);
            writer.flush();
            writer.close();
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
