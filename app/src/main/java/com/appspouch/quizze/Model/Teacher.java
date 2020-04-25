package com.appspouch.quizze.Model;

public class Teacher {

        public String name, email, phone, designation;

        public Teacher(String name, String email, String mobile, String designation){

        }

        public Teacher(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.designation = designation;
        }
}
