package com.ftflproject.ftflicareapplication.model;

public class ProfileModel {

	public int mId;
	
	public String mName;
	public String mGender;
	
	public String mAge;
	public String mWeight;
	public String mHeight;

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmGender() {
		return mGender;
	}

	public void setmGender(String mGender) {
		this.mGender = mGender;
	}

	

	public String getmAge() {
		return mAge;
	}

	public void setmAge(String mAge) {
		this.mAge = mAge;
	}

	public String getmWeight() {
		return mWeight;
	}

	public void setmWeight(String mWeight) {
		this.mWeight = mWeight;
	}

	public String getmHeight() {
		return mHeight;
	}

	public void setmHeight(String mHeight) {
		this.mHeight = mHeight;
	}

	

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	

	
	public ProfileModel(String mName, String mGender,
			String mAge, String mWeight, String mHeight) {
		super();
		this.mName = mName;
		this.mGender = mGender;
	
		this.mAge = mAge;
		this.mWeight = mWeight;
		this.mHeight = mHeight;
	}

	public ProfileModel() {
		super();
	}

}