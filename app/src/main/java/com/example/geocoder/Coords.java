package com.example.geocoder;

import com.yandex.mapkit.geometry.Point;

public class Coords {
    private double x;
    private double y;

    public Coords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coords(String cords) {
        String[] tmp = cords.split(" ");
        this.x = Double.parseDouble(tmp[0]);
        this.y = Double.parseDouble(tmp[1]);
    }

    public Point getPoint() {
        Point point = new Point(this.y, this.x);
        return point;
    }

}
