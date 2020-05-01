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
}
