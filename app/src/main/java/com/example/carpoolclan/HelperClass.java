package com.example.carpoolclan;

public class HelperClass {
    String name, email, dob, password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email.replace(',','.');
    }

    public void setEmail(String email) {
        this.email = email.replace('.',',');
    }

    public String getDOB() {
        return dob;
    }

    public void setDOB(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HelperClass(String name, String email, String dob, String password) {
        setName(name);
        setEmail(email);
        setDOB(dob);
        setPassword(password);
    }

    public HelperClass() {
    }
}
