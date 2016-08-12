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

import java.io.IOException;

public class JKReconnectTask implements Runnable {
	JKQueryAsync agent;

	protected JKReconnectTask(JKQueryAsync async) {
		agent = async;
	}

	@Override
	public void run() {
		try {
			if (!agent.isConnected()) {
				agent.connect();
			}
        } catch (IOException e) {
        }
	}
}