package earthquake.site.service;

import earthquake.site.dao.LogRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;


/**
 * Created by fly on 2016/10/20.
 */

@Service
public class LogService {

    @Inject
    private LogRepository logRepository;

    @Scheduled(fixedDelay = 120_000L, initialDelay = 10_000L)
    @Transactional
    public void deleteLogs() {
        logRepository.deleteLogs();
    }
}
