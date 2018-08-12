package cn.enali.geometry;

public class Circle {
    Point center;
    double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public double distance(Circle c) {
        return center.distance(c.center);
    }
}
