package com.epic.project.phonebook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.NonNull;

@Entity

public class MobileSubscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique=true, nullable = false)
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number should be in E-164 format")
    private String msisdn;

    @NonNull
    @Column(nullable = false)
    private Long ownerId;
    
    @NonNull
    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(nullable = false)
    private Type serviceType;
    
    @NonNull
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Long serviceStartDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    
    public Long getOwner() {
        return ownerId;
    }

    public void setOwner(Long owner) {
        this.ownerId = owner;
    }
    
    public Long getUser() {
        return userId;
    }

    public void setUser(Long user) {
        this.userId = user;
    }
    
    public Type getType() {
		return serviceType;
	}

	public void setType(Type serviceType) {
		this.serviceType = serviceType;
	}

    public Long getServiceStartDate() {
        return this.serviceStartDate;
        		}

    public void setServiceStartDate(Long serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }
    
    protected MobileSubscriber() {
    	// Default constructor
    }

    public MobileSubscriber(String msisdn, Long ownerId, Long userId, Type serviceType, Long serviceStartDate) {
        this.msisdn = msisdn;
        this.ownerId = ownerId;
        this.userId = userId;
        this.serviceType = serviceType;
        this.serviceStartDate = serviceStartDate;
    }
}