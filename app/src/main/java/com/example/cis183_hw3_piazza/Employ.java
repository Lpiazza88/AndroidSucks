package com.example.cis183_hw3_piazza;

import java.io.Serializable;

public class Employ implements Serializable {
    String fname;
    String lname;
    String uname;
    String pass;
    String email;
    String age;

    public Employ(String u, String f, String l, String p, String e, String a){
        uname=u;
        fname=f;
        lname=l;
        pass=p;
        email=e;
        age=a;
    }

    public Employ(){

    }

    public String getFname(){
        return fname;
    }
    public void setFname(String fname){
        this.fname=fname;
    }

    public String getLname(){
        return lname;
    }
    public void setLname(String lname){
        this.lname=lname;
    }

    public String getUname(){
        return uname;
    }
    public void setUname(String uname){
        this.uname=uname;
    }

    public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass=pass;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getAge(){
        return age;
    }
    public void setAge(String age){
        this.age=age;
    }
}
