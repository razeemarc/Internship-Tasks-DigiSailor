import React, { useState } from 'react';
import '../App.css';

const EmployeeForm = () => {
  const [formData, setFormData] = useState({
    empId: '',
    firstName: '',
    lastName: '',
    email: '',
    startDate: '',
    endDate: '',
    leaveType: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8094/addEmployee', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const data = await response.json();
      console.log('Success:', data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div className="form-container">
      <form onSubmit={handleSubmit} className="employee-form">
        <div className="heading">Employee Leave Form</div>
        <div className="form-group">
          <label className="label">Employee ID:</label>
          <input type="number" name="empId" value={formData.empId} onChange={handleChange} required className="input" />
        </div>
        <div className="form-group">
          <label className="label">First Name:</label>
          <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} required className="input" />
        </div>
        <div className="form-group">
          <label className="label">Last Name:</label>
          <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} required className="input" />
        </div>
        <div className="form-group">
          <label className="label">Email:</label>
          <input type="email" name="email" value={formData.email} onChange={handleChange} required className="input" />
        </div>
        <div className="form-group">
          <label className="label">Start Date:</label>
          <input type="date" name="startDate" value={formData.startDate} onChange={handleChange} required className="input" />
        </div>
        <div className="form-group">
          <label className="label">End Date:</label>
          <input type="date" name="endDate" value={formData.endDate} onChange={handleChange} required className="input" />
        </div>
        <div className="form-group">
          <label className="label">Leave Type:</label>
          <select name="leaveType" value={formData.leaveType} onChange={handleChange} required className="input">
            <option value="">Select Leave Type</option>
            <option value="Sick Leave">Sick Leave</option>
            <option value="Vacation Leave">Vacation Leave</option>
            <option value="Maternity Leave">Maternity Leave</option>
            <option value="Personal Leave">Personal Leave</option>
          </select>
        </div>
        <button type="submit" className="submit-button">Submit</button>
      </form>
    </div>
  );
};

export default EmployeeForm;
