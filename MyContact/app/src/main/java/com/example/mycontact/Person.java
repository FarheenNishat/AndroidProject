package com.example.mycontact;

public class Person {
    private String name;
    private String telno;

    public Person(String name, String telno) {
        this.name = name;
        this.telno = telno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }
}
