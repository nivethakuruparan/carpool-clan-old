package com.example.carpoolclan;

public class AccountInfoHelper {
    String name, email, dob, password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email.replaceAll("@", "/");
    }

    public void setEmail(String email) {
        this.email = email.replaceAll("/", "@");
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

    public AccountInfoHelper(String name, String email, String dob, String password) {
        setName(name);
        setEmail(email);
        setDOB(dob);
        setPassword(password);
    }

    public AccountInfoHelper() {
    }
}
