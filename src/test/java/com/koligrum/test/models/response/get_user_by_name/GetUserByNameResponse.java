package com.koligrum.test.models.response.get_user_by_name;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUserByNameResponse{

	@JsonProperty("data")
	private List<DataItem> data;

	@JsonProperty("status")
	private String status;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}