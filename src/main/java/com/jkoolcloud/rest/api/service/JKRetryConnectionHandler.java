/*
 * Copyright 2014-2015 JKOOL, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jkoolcloud.rest.api.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.websocket.CloseReason;

public class JKRetryConnectionHandler implements JKConnectionHandler {
	ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

	long retryTimeout;
	TimeUnit tunit;

	long errorCount = 0;
	long timeClose, timeOpen, timeError;

	public JKRetryConnectionHandler(long retryTime, TimeUnit unit) {
		retryTimeout = retryTime;
		tunit = unit;
	}

	@Override
	public void open(JKQueryAsync async) {
		long now = System.currentTimeMillis();
		timeOpen = now;
		scheduleResubscribe(async, 10, TimeUnit.MILLISECONDS, now);
	}

	@Override
	public void error(JKQueryAsync async, Throwable ex) {
		timeError = System.currentTimeMillis();
		errorCount++;
		scheduleReconnect(async, new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, ex.getMessage()));
	}

	@Override
	public void close(JKQueryAsync async, CloseReason reason) {
		timeClose = System.currentTimeMillis();
		scheduleReconnect(async, reason);
	}
	
	public long getErrorCount() {
		return errorCount;
	}
	
	private void scheduleReconnect(JKQueryAsync async, CloseReason reason) {
		if (reason.getCloseCode() != CloseReason.CloseCodes.NORMAL_CLOSURE) {
			scheduledThreadPool.schedule(new JKReconnectTask(async), retryTimeout, tunit);
		}		
	}
	
	private void scheduleResubscribe(JKQueryAsync async, long time, TimeUnit unit, long timeOpen) {
		scheduledThreadPool.schedule(new JKRestoreSubscriptionsTask(async, timeOpen), time, tunit);
	}
}
