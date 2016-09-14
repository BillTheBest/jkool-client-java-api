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
package com.jkoolcloud.client.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class defines a snapshot, which is a collection of properties at a
 * particular moment in time.
 * 
 * @author cathy
 */
@ApiModel(description = "")
public class Snapshot implements Validated {

	private String category;
	private String name;
	private long timeUsec;
	private List<Property> properties;
	private EventTypes type = EventTypes.SNAPSHOT;

	/**
	 * Create a snapshot
	 * 
	 * @param category
	 *            snapshot category
	 * @param name
	 *            associated with the snapshot
	 */
	public Snapshot(String category, String name) {
		this(category, name, System.currentTimeMillis());
	}

	/**
	 * Create a snapshot
	 * 
	 * @param category
	 *            snapshot category
	 * @param name
	 *            associated with the snapshot
	 * @param timeMs
	 *            timestamp associated with the entity
	 */
	public Snapshot(String category, String name, long timeMs) {
		this.category = category;
		this.name = name;
		this.timeUsec = timeMs * 1000;
	}

	/**
	 * Create a snapshot
	 * 
	 * @param category
	 *            snapshot category
	 * @param name
	 *            associated with the snapshot
	 * @param timeMs
	 *            timestamp associated with the entity
	 * @param props
	 *            a variable list of properties
	 */
	public Snapshot(String category, String name, List<Property> props) {
		this(category, name, System.currentTimeMillis());
		setProperties(props);
	}

	/**
	 * Create a snapshot
	 * 
	 * @param category
	 *            snapshot category
	 * @param name
	 *            associated with the snapshot
	 * @param timeMs
	 *            timestamp associated with the entity
	 * @param props
	 *            a variable list of properties
	 */
	public Snapshot(String category, String name, Property... props) {
		this(category, name, System.currentTimeMillis());
		setProperties(props);
	}

	/**
	 * Create a snapshot
	 * 
	 * @param category
	 *            snapshot category
	 * @param name
	 *            associated with the snapshot
	 * @param timeMs
	 *            timestamp associated with the entity
	 * @param props
	 *            list of properties
	 */
	public Snapshot(String category, String name, long timeMs, List<Property> props) {
		this(category, name, timeMs);
		setProperties(props);
	}

	/**
	 * Create a snapshot
	 * 
	 * @param category
	 *            snapshot category
	 * @param name
	 *            associated with the snapshot
	 * @param timeMs
	 *            timestamp associated with the entity
	 * @param props
	 *            a variable list of properties
	 */
	public Snapshot(String category, String name, long timeMs, Property... props) {
		this(category, name, timeMs);
		setProperties(props);
	}

	/**
	 * Create a snapshot
	 * 
	 * @param category
	 *            snapshot category
	 * @param name
	 *            associated with the snapshot
	 * @param time
	 *            timestamp associated with the entity
	 * @param properties
	 *            a list of properties
	 */
	public Snapshot(String category, String name, Date time, List<Property> properties) {
		this(category, name, time.getTime());
		this.properties = properties;
	}

	/**
	 * Create a snapshot
	 * 
	 * @param category
	 *            snapshot category
	 * @param name
	 *            associated with the snapshot
	 * @param time
	 *            timestamp associated with the entity
	 * @param props
	 *            a variable list of properties
	 */
	public Snapshot(String category, String name, Date time, Property... props) {
		this(category, name, time.getTime());
		setProperties(props);
	}

	/**
	 * Validate fields of this entity
	 *
	 * @return true if valid, false otherwise
	 */
	public boolean isValid() {
		return category != null && name != null && (timeUsec > 0) && (properties != null && properties.size() > 0);
	}

	/**
	 * /**
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("category")
	public String getCategory() {
		return category;
	}

	/**
	 * Assign snapshot category
	 *
	 * @param category
	 *            snapshot category
	 * @return self
	 */
	public Snapshot setCategory(String category) {
		this.category = category;
		return this;
	}

	/**
	**/
	@ApiModelProperty(value = "")
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * Assign snapshot name
	 *
	 * @param name
	 *            snapshot name
	 * @return self
	 */
	public Snapshot setName(String name) {
		this.name = name;
		return this;
	}

	/**
	**/
	@ApiModelProperty(value = "")
	@JsonProperty("time-usec")
	public long getTimeUsec() {
		return timeUsec;
	}

	public Snapshot setTimeUsec(Date timeUsec) {
		this.timeUsec = timeUsec.getTime() * 1000;
		return this;
	}

	/**
	**/
	@ApiModelProperty(value = "")
	@JsonProperty("type")
	public EventTypes getType() {
		return type;
	}

	/**
	**/
	@ApiModelProperty(value = "")
	@JsonProperty("properties")
	public List<Property> getProperties() {
		return properties;
	}

	/**
	 * Assign snapshot properties
	 *
	 * @param properties
	 *            list of properties
	 * @return self
	 */
	public Snapshot setProperties(List<Property> properties) {
		this.properties = new ArrayList<Property>(properties);
		return this;
	}

	/**
	 * Assign snapshot properties
	 *
	 * @param properties
	 *            list of properties
	 * @return self
	 */
	public Snapshot setProperties(Property... props) {
		if (props != null) {
			this.properties = Arrays.asList(props);
		}
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Snapshot {\n");
		sb.append("  type: ").append(type).append("\n");
		sb.append("  category: ").append(category).append("\n");
		sb.append("  name: ").append(name).append("\n");
		sb.append("  timeUsec: ").append(timeUsec).append("\n");
		sb.append("  properties: ").append(properties).append("\n");
		sb.append("}\n");
		return sb.toString();
	}
}
