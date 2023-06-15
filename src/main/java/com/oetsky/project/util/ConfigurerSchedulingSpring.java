package com.oetsky.project.util;

import com.oetsky.project.monitor.job.util.CronUtils;
import java.util.Date;
import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

/**
 * 重写内置定时任务配置类，处理定时任务修改时间后任务不执行
 * @Author xiangzc
 * @Date 2021-12-12
 */
@Configuration
@EnableScheduling
public abstract class ConfigurerSchedulingSpring implements SchedulingConfigurer {

	//定时任务周期表达式
	private String cron;

	/**
	 * @Description: 重写配置定时任务的方法
	 * @param: scheduledTaskRegistrar
	 * @return: void
	 * @Author: xiangzc
	 * @Date: 2021/07/05
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		scheduledTaskRegistrar.setScheduler(taskScheduler());
		scheduledTaskRegistrar.addTriggerTask(
				//执行定时任务
				() -> {
					taskService();
				},
				//设置触发器
				triggerContext -> {
					//获取定时任务周期表达式
					cron = getCron();
					CronTrigger trigger = new CronTrigger(cron);
					// 获取当前系统时间下  cron表达式下次触发时间
					Date nextExecution = CronUtils.getNextExecution(cron);
					// 系统默认中下次触发时间
					Date date = trigger.nextExecutionTime(triggerContext);
					// 判断系统默认下次触发时间与   当前获取当前系统下触发时间是否一致
					if(date.getTime() != nextExecution.getTime()){
						// 不一致则修改成 当前系统下触发时间
						date = nextExecution;
					}
					return date;
				}
		);

	}

	@Bean
	public Executor taskScheduler() {
		//设置线程名称
//		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
//		//创建线程池
//		return Executors.newScheduledThreadPool(5, namedThreadFactory);
		ThreadPoolTaskScheduler taskExecutor = new ThreadPoolTaskScheduler();
		taskExecutor.initialize();
		taskExecutor.setPoolSize(5);
		taskExecutor.setThreadGroupName("builtInThread-");
		taskExecutor.setThreadNamePrefix("builtInThread-");
		return taskExecutor;
	}

	/**
	 * @Description: 获取定时任务周期表达式
	 * @param:
	 * @return: java.lang.String
	 * @Author: xiangzc
	 * @Date: 2021/07/05
	 */
	public abstract String getCron();

	/**
	 * @Description: 执行定时任务
	 * @param:
	 * @return: void
	 * @Author: xiangzc
	 * @Date: 2021/07/05
	 */
	public abstract void taskService();

}
