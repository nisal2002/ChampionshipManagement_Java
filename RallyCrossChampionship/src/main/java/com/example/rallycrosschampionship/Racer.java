package com.example.rallycrosschampionship;

import java.time.LocalDate;

public class Racer extends Driver {
  private LocalDate date;
  private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
