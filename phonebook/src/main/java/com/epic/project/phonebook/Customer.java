package com.epic.project.phonebook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NonNull;

@Entity
public class Customer {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @NonNull
	    private Long id;

	    @NonNull
	    @Column(nullable = false)
	    private String firstName;

	    @NonNull
	    @Column(nullable = false)
	    private String lastName;
	    
	    @NonNull
	    @Column(unique=true,nullable = false)
	    private String emailId;
	    
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		
		protected Customer() {
			// Default constructor
	    }

	    public Customer(String firstName, String lastName, String emailId) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.emailId = emailId;
	    }
}
