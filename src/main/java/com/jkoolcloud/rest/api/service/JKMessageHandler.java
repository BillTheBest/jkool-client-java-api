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

import javax.websocket.CloseReason;
import javax.websocket.Session;

public interface JKMessageHandler {
	void handle(WebsocketClient client, String message);
	
	void onError(WebsocketClient client, Session userSession, Throwable ex);
	void onOpen(WebsocketClient client, Session userSession);
	void onClose(WebsocketClient client, Session userSession, CloseReason reason);
}
