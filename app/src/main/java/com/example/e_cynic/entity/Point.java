package com.example.e_cynic.entity;

import java.util.Date;

public class Point {
    public Integer pointId;
    public Integer userId;
    public Integer pointsEarned;
    public Date date;

    public Point(Integer pointId, Integer userId, Integer pointsEarned, Date date) {
        this.pointId = pointId;
        this.userId = userId;
        this.pointsEarned = pointsEarned;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Point{" +
                "pointId=" + pointId +
                ", pointsEarned=" + pointsEarned +
                ", date=" + date +
                '}';
    }
}