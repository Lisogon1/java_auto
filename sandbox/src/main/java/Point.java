public class Point {
    public double x;
    public double y;



public Point(double x, double y){
    this.x = x;
    this.y = y;

}

    public double distanceInPoint(Point p2) {
        double distInPoint;
        distInPoint = Math.sqrt(Math.pow((p2.x - this.x) ,2) + Math.pow((p2.y - this.y) ,2));
        return distInPoint;
    }

}


