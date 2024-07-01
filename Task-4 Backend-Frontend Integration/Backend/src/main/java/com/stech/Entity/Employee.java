package com.stech.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "employee_leave_form")
public class Employee {


	@Id
	private int empId;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "email", nullable = false, length = 200)
	private String email;
	private Date startDate;
	private Date endDate;
	private String leaveType;

}
