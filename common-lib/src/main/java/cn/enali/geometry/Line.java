package cn.enali.geometry;

import cn.enali.geometry.Point;

public class Line {
    Point p1;
    Point p2;

    public Line(Point _p1, Point _p2) {
        this.p1 = _p1;
        this.p2 = _p2;
    }

    public Line(double p1x, double p1y, double p2x, double p2y) {
        this.p1 = new Point(p1x, p1y);
        this.p2 = new Point(p2x, p2y);
    }
}
