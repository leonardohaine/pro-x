package br.com.prox.nfe.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

@Component
public class TesteQuartz {

	private static final Logger log = LoggerFactory.getLogger(TesteQuartz.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ThreadPoolTaskScheduler thread;
    
    //@Scheduled(fixedRate = 60000, initialDelay = 30000, zone = "America/Sao_Paulo")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        thread.setThreadNamePrefix("testando...");
        //thread.getScheduledThreadPoolExecutor().shutdownNow();
    }



	/*@Override
	public void configureTasks(ScheduledTaskRegistrar arg0) {
		arg0.setScheduler(taskExecutor());
	}
	
	@Bean(name = "taskScheduler", destroyMethod = "shutdown")
    public Executor taskExecutor() {
        // use the Spring ThreadPoolTaskScheduler
        ThreadPoolTaskScheduler scheduledExecutorService = new ThreadPoolTaskScheduler();
        // always set the poolsize

.setPoolSize(5);
        // for logging add a threadNamePrefix
        scheduledExecutorService.setThreadNamePrefix("myTaskScheduler-");
        // do not wait for completion of the task
        scheduledExecutorService.setWaitForTasksToCompleteOnShutdown(false);
 
        return scheduledExecutorService;
    }*/
	
}
