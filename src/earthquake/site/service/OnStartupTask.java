package earthquake.site.service;

import earthquake.site.service.OuterDataService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by fly on 2017/6/19 0019.
 * 这个类想实现启动运行地震台网全文爬取，但是数据库貌似不可用，另外报了容器错误，保留代码，弃用
 */

//@Component
public class OnStartupTask implements ApplicationListener<ContextRefreshedEvent> {

    @Inject
    OuterDataService outerDataService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if(!contextRefreshedEvent.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){
//            try {
//                outerDataService.getAllSeismicNetwork();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        System.out.println(123);
        }
//    }
}
