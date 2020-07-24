package com.appspouch.quizze.Model;

public class Student {

    public String name, email, mobile, id, department, branch, semester, batch;

    public Student(){

    }

    public Student(String Name, String Email, String Mobile, String Id, String Department, String Branch, String Semester, String Batch) {
        this.name = Name;
        this.id = Id;
        this.email = Email;
        this.mobile = Mobile;
        this.department = Department;
        this.semester = Semester;
        this.branch = Branch;
        this.batch = Batch;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
