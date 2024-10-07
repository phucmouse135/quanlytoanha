// Java
package com.javaweb.model.response;

import com.javaweb.model.dto.AbstractDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingSearchResponse extends AbstractDTO {
	private Long id;
	private String name;
	private String address;
	private Integer floorArea;
	private String managerName;
	private String managerPhone;
	private String rentArea;
	private String rentPrice;
	private String servicePrice;
	private String brokerageFee;
	private Long numberOfBasement; // Add this property

	private List<Integer> totalPages ; // Add this property

//	public List<Integer> getTotalPages() {
//		return totalPages;
//	}
//
//	public void setTotalPages(List<Integer> totalPages) {
//		this.totalPages = totalPages;
//	}

	private Map<Long,String> staffs = new HashMap<>(); // Add this property

	public Map<Long, String> getStaffs() {
		return staffs;
	}

	public void setStaffs(Map<Long, String> staffs) {
		this.staffs = staffs;
	}

	// Getters and Setters for all properties

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getRentArea() {
		return rentArea;
	}

	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

	public String getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(String rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getBrokerageFee() {
		return brokerageFee;
	}

	public void setBrokerageFee(String brokerageFee) {
		this.brokerageFee = brokerageFee;
	}

	public Long getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	// Other getters and setters
}