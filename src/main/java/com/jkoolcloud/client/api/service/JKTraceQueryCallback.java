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
package com.jkoolcloud.client.api.service;

import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicLong;

import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonValue;

import com.jkoolcloud.client.api.utils.JKUtils;

/**
 * This class implements a simple {@code JKQueryCallback} with
 * trace messages.
 * 
 * @author albert
 */
public class JKTraceQueryCallback implements JKQueryCallback {
	PrintStream out;
	String json_path;
	boolean trace = true;
	Throwable lastError;
	
	AtomicLong msgCount = new AtomicLong(0);
	AtomicLong errCount = new AtomicLong(0);
	
	
	/**
	 * Create a trace query callback instance
	 * 
	 */
	public JKTraceQueryCallback() {
		this(System.out, true);
	}
	
	/**
	 * Create a trace query callback instance
	 * 
	 * @param out output print stream
	 * 
	 */
	public JKTraceQueryCallback(PrintStream out) {
		this(out, true);
	}
	
	/**
	 * Create a trace query callback instance
	 * 
	 * @param out output print stream
	 * @param flag flag
	 * 
	 */
	public JKTraceQueryCallback(PrintStream out, boolean flag) {
		this.out = out;
		setTrace(flag);
	}
	
	/**
	 * Create a trace query callback instance
	 * 
	 * @param out output print stream
	 * @param json_path json path to get from the response
	 * @param flag flag
	 * 
	 */
	public JKTraceQueryCallback(PrintStream out, String jsonPath, boolean flag) {
		this.out = out;
		this.json_path = jsonPath;
		setTrace(flag);
	}
	
	@Override
	public void handle(JKQueryHandle qhandle, JsonObject response, Throwable ex) {
		if (ex != null) {
			lastError = ex;
			errCount.incrementAndGet();
			out.println("Error on handle=" + qhandle + ", error=" + ex.getMessage());
			ex.printStackTrace(out);
		} else {
			msgCount.incrementAndGet();
			if (json_path == null) {
				out.println(JKUtils.prettyPrint(response));
			} else {
				JsonValue jsonPath = JKUtils.getJsonValue(json_path, response);
				if (jsonPath == null) {
					out.print("\"" + json_path + "\" not found");
				} else if (jsonPath instanceof JsonStructure){
					out.println(JKUtils.prettyPrint((JsonStructure)jsonPath));
				} else {
					out.format("%s = %s, %s", json_path, jsonPath, jsonPath.getValueType());
				}
			}
		}
	}
	
	/**
	 * Enable/disable trace mode
	 * 
	 * @param flag trace flag
	 * 
	 */
	public JKTraceQueryCallback setTrace(boolean flag) {
		this.trace = flag;
		return this;
	}

	/**
	 * Get error count
	 * 
	 * @return obtain error count
	 */
	public long getErrorCount() {
		return errCount.get();
	}
	
	/**
	 * Get received message count
	 * 
	 * @return obtain error count
	 */
	public long getMsgCount() {
		return msgCount.get();
	}
	
	/**
	 * Get last error
	 * 
	 * @return last error
	 */
	public Throwable getLastError() {
		return lastError;
	}
	
	@Override
    public void dead(JKQueryHandle qhandle) {
		if (trace) {
			out.println("Dead handle=" + qhandle + ", dead=" + qhandle.isDead());
		}
	}
}