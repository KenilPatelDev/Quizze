package com.appspouch.quizze.Model;

import android.widget.Spinner;

public class Teacher {

    public String name, email, mobile, designation, department, branch;

    public Teacher() {

    }

    public Teacher(String Name, String Email, String Mobile, String Designation, String Department, String Branch) {
        this.name = Name;
        this.email = Email;
        this.mobile = Mobile;
        this.designation = Designation;
        this.department = Department;
        this.branch = Branch;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }

    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getDesignation() { return designation; }

    public void setDesignation(String designation) { this.designation = designation; }

    public String getDepartment() { return department; }

    public void setDepartment(String department) { this.department = department; }

    public String getBranch() { return branch; }

    public void setBranch(String branch) { this.branch = branch; }
}
