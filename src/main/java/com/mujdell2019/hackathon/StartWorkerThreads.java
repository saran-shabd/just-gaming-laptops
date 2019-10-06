package com.mujdell2019.hackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mujdell2019.hackathon.workers.ClearDellProductsAnalyticsFieldsWorderThread;

@Component
public class StartWorkerThreads {

	@Autowired
	private ApplicationContext factory;
	
	@EventListener(ContextRefreshedEvent.class)
	public void startWorkerThreads() {
		
		startClearDellProductsAnalyticsWorkerThread();
	}
	
	private void startClearDellProductsAnalyticsWorkerThread() {
		
		// create class instance
		ClearDellProductsAnalyticsFieldsWorderThread worker = new ClearDellProductsAnalyticsFieldsWorderThread();
		
		// enable auto wiring for the worker thread
		factory.getAutowireCapableBeanFactory().autowireBean(worker);
		
		// start thread
		Thread thread = new Thread(worker);
		thread.start();
	}
}
