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
package com.jkoolcloud.rest.api.utils;

import java.io.PrintStream;

import com.jkoolcloud.rest.api.service.JKQueryAsync;

public class JKCmdOptions {
	public static final String DEFAULT_CMD_NAME = "class";

	public String query;
	public String token;
	public String usage;
	public String appname = DEFAULT_CMD_NAME;
	public String uri = JKQueryAsync.JKOOL_WEBSOCK_URL;
	public boolean trace = false;
	public long waitTimeMs = 20000;
	public int maxRows = 50;

	public JKCmdOptions(String[] args) {
		parseOptions(args);
	}

	public JKCmdOptions(String appName, String[] args) {
		appname = appName;
		parseOptions(args);
	}

	public JKCmdOptions(Class<?> clazz, String[] args) {
		appname = clazz.getSimpleName();
		parseOptions(args);
	}

	private void parseOptions(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if ("-a".equals(arg)) {
				if (i == args.length) {
					usage = "Must specify access token with -a";
					return;
				}
				token = args[++i];
			}
			if ("-q".equals(arg)) {
				if (i == args.length) {
					usage = "Must specify query with -q";
					return;
				}
				query = args[++i];
			}
			if ("-u".equals(arg)) {
				if (i == args.length) {
					usage = "Must specify URI with -u";
					return;
				}
				uri = args[++i];
			}
			if ("-w".equals(arg)) {
				if (i == args.length) {
					usage = "Must specify wait time (ms) -w";
					return;
				}
				waitTimeMs = Long.parseLong(args[++i]);
			}
			if ("-rows".equals(arg)) {
				if (i == args.length) {
					usage = "Must specify maximum rows with -rows";
					return;
				}
				maxRows = Integer.parseInt(args[++i]);
			}
			if ("-trace".equals(arg)) {
				trace = true;
			}
		}
		if (query == null || token == null) {
			usage = String.format("Usage: %s -a token -q query [-u url -w wait-ms -rows max-rows -trace]", appname);
			return;
		}
	}

	public void print() {
		print(System.out);
	}

	public void print(PrintStream out) {
		out.println(this.toString());
	}
	
	@Override
	public String toString() {
		String formatted = String.format("%s: uri=\"%s\", query=\"%s\", wait.ms=%d, max.rows=%d, trace=%b, token=%s", 
				appname, uri, query, waitTimeMs, maxRows, trace, token);
		return formatted;
	}
}