package earthquake.test.testCode;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * Created by Administrator on 2017/7/15.
 */
public class Freemark {
    public static void main(String[] args) throws IOException{
        // 绝对路径，相对路径目前有点问题
        Freemark freemark = new Freemark("C:\\Users\\Administrator\\IdeaProjects\\EarthquakeJava\\templates");
        freemark.setTemplateName("earthquake.ftl");
        freemark.setFileName("doc_"+new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date())+".doc");
        freemark.setFilePath("output\\");
        //生成word
        freemark.createWord();
    }
    private void createWord(){

        Template t = null;
        try {
            //获取模板信息
            t = configuration.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File outFile = new File(filePath+fileName);
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map map = new HashMap<String, Object>();
        map.put("info", "info");
        map.put("magnitude", 3);
        try {
            //输出数据到模板中，生成文件。
            t.process(map, out);
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * freemark初始化
     * @param templatePath 模板文件位置
     */
    public Freemark(String templatePath) throws IOException{
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        System.out.println(this.getClass());
    }
    /**
     * freemark模板配置
     */
    private Configuration configuration;
    /**
     * freemark模板的名字
     */
    private String templateName;
    /**
     * 生成文件名
     */
    private String fileName;
    /**
     * 生成文件路径
     */
    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
