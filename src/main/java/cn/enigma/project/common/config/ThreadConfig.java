package cn.enigma.project.common.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * remark
 * @author luzh
 * createTime 2017年6月23日 下午5:10:45
 */
@EnableAsync
@Configuration
public class ThreadConfig extends AsyncConfigurerSupport {

	private final BeanFactory beanFactory;

	public ThreadConfig(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	@Bean
	public Executor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		int corePoolSize = 10;
		executor.setCorePoolSize(corePoolSize);
		int maxPoolSize = 20;
		executor.setMaxPoolSize(maxPoolSize);
		int queueCapacity = 30000;
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("pool-thread-");
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		int awaitTerminationSeconds = 60;
		executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
		// 设置线程池关闭的时候等待所有任务都完成再继续销毁其他的bean
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.initialize();
		Runtime.getRuntime().addShutdownHook(new Thread(executor::shutdown));
		return new LazyTraceExecutor(beanFactory, executor);
	}


	@Override
	public Executor getAsyncExecutor() {
		return executor();
	}
}
