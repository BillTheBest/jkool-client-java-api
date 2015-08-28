package com.nastel.jkool.api.model;

import java.util.*;

import io.swagger.annotations.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nastel.jkool.api.model.Property;


@ApiModel(description = "")
public class Snapshot  {
  
  private String parentId = null;
  private String category = null;
  private String name = null;
  private Integer count = null;
  private String timeUsec = null;
  private String type = null;
  private String typeNo = null;
  private List<Property> properties;
  private String trackId = null;
  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("parent-id")
  public String getParentId() {
    return parentId;
  }
  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("category")
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("count")
  public Integer getCount() {
    return count;
  }
  public void setCount(Integer count) {
    this.count = count;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("time-usec")
  public String getTimeUsec() {
    return timeUsec;
  }
  public void setTimeUsec(String timeUsec) {
    this.timeUsec = timeUsec;
  }
  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("type")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("properties")
  public List<Property> getProperties() {
    return properties;
  }
  public void setProperties(List<Property> properties) {
    this.properties = properties;
  }

  
	/**
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("track-id")
    public String getTrackId() {
    	return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

@Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Snapshot {\n");
    
    sb.append("  parentId: ").append(parentId).append("\n");
    sb.append("  category: ").append(category).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  count: ").append(count).append("\n");
    sb.append("  timeUsec: ").append(timeUsec).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("  properties: ").append(properties).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
