package com.example.focuscap.Model;

public class Petition {
    private String name;
    private String mail;
    private String phone;
    private String message;

    public Petition() {
    }

    public Petition(String name, String mail, String phone, String message) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
