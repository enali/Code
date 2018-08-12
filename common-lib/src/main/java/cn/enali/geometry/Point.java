package cn.enali.geometry;

public class Point {
    double x;
    double y;

    public Point(double _x, double _y) {
        this.x = _x;
        this.y = _y;
    }

    // 求两点间的距离
    public double distance(Point p) {
        double deltaX = x - p.x;
        double deltaY = y - p.y;
        return Math.sqrt( deltaX * deltaX + deltaY * deltaY);
    }

    // 绕某点的旋转
    public Point rotate(Point p, double radian) {
        double r = distance(p);
        double theta = Math.atan((y-p.y) / (x-p.x));
        double newX = Math.cos(theta + radian) * r;
        double newY = Math.sin(theta + radian) * r;
        return new Point(newX, newY);
    }

    @Override
    public String toString() {
        return "Point(" + x + "," + y + ")";
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 0);
        Point org = new Point(0, 0);
        System.out.println(p1.rotate(org, Math.PI / 4));
        System.out.println(org.rotate(p1, Math.PI / 2));
    }
}
