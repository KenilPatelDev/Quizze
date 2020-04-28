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
}
