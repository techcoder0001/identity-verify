package com.forgot.app.identityverify.model;

public class IdentityVerifyResponse {

    private String profileRefId;
    private String fullName;
    private String address;
    private long phoneNumber;

    public String getProfileRefId() {
        return profileRefId;
    }

    public void setProfileRefId(String profileRefId) {
        this.profileRefId = profileRefId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
