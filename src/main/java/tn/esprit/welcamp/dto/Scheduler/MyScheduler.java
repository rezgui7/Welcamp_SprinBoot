package tn.esprit.welcamp.dto.Scheduler;


import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.scheduling.TaskScheduler;
        import org.springframework.scheduling.support.CronTrigger;
        import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

@Component
public class MyScheduler {

    @Autowired
    private TaskScheduler taskScheduler;

    private ScheduledFuture<?> scheduledTask;

    public void changeCronExpression(String cronExpression) {
        if (scheduledTask != null) {
            scheduledTask.cancel(false);
        }
        scheduledTask = taskScheduler.schedule(() -> {
            // task to be executed
            System.out.println("Task executed");
        }, new CronTrigger(cronExpression));
    }
}