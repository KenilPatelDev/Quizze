package com.appspouch.quizze.Model;

public class Student {

    public String name, email, mobile, id;

    public Student(){

    }

    public Student(String Name, String Email, String Mobile, String Id) {
        this.name = Name;
        this.id = Id;
        this.email = Email;
        this.mobile = Mobile;

    }
}
