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


/**
 * This class implements a standard jKool client API exception
 * 
 * @author albert
 */
public class JKStreamException extends Exception {
	/**
	 * 
	 */
    private static final long serialVersionUID = 293667154457636080L;
    
	private int code = 0;

	public JKStreamException(String message) {
		super(message);
	}

	public JKStreamException(String message, Throwable e) {
		super(message, e);
	}

	public JKStreamException(int code, String message) {
		super(message);
		this.code = code;
	}

	public JKStreamException(int code, String message, Throwable e) {
		super(message, e);
		this.code = code;
	}

	/**
	 * Get exception code
	 * 
	 * @return exception code, 0 if none
	 */
	public int getCode() {
		return code;
	}
}