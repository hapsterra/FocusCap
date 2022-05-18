package com.example.focuscap.Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Quote {

    private int id;
    private static final AtomicInteger count = new AtomicInteger(0);
    private String user;
    private String date;
    private String quote;

    public Quote(){
    }

    public String getId() {
        return id+"";
    }

    public Quote(String user, String date, String quote) {
        this.id= count.incrementAndGet();
        this.user = user;
        this.date = date;
        this.quote = quote;
    }

    public Quote(int id,String user, String date, String quote) {
        this.id= id;
        this.user = user;
        this.date = date;
        this.quote = quote;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
