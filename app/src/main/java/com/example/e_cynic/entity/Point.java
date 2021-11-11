package com.example.e_cynic.entity;

import androidx.annotation.Nullable;

import java.util.Date;

public class Point {
    public Integer pointId;
    public Integer pointsEarned;
    public Date date;

    public Point(@Nullable Integer pointId, Integer pointsEarned, Date date) {
        this.pointId = pointId;
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