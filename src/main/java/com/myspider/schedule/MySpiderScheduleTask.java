package com.myspider.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySpiderScheduleTask {


    /**
     * 每五称执行一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void getTaobaoBestProductUrl2File() {

        System.out.println("执行");

    }

}
