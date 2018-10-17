package com.assessment.it.itskillsassessment.entity;

public class User {

    private String fullname;
    private String school;
    private String username;
    private String password;
    private int isadmin;


    public User(String fullname, String school, String username, String password, int isadmin)
    {
        this.fullname = fullname;
        this.school = school;
        this.username = username;
        this.password = password;
        this.isadmin = isadmin;
    }

    public String getFullname()
    {
        return this.fullname;
    }

    public String getSchool()
    {
        return this.school;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public int getIsadmin()
    {
        return this.isadmin;
    }

}
