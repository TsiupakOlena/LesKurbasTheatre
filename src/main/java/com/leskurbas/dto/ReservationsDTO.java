package com.leskurbas.dto;

import java.util.List;

public class ReservationsDTO {
    public String name;
    public String date;
    public String time;
    public String price;
    public String picture;
    public List<SeatsDTO> seats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<SeatsDTO> getSeats() {
            return seats;
        }

        public void setSeats(List<SeatsDTO> seats) {
            this.seats = seats;
        }
    }
