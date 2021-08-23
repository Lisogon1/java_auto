public class Point {
    public double x;
    public double y;
    public double z;
    public double v;



public Point(double x, double y,double z,double v){
    this.x = x;
    this.y = y;
    this.y = z;
    this.y = v;


}

    public double distanceInPoint() {
        double distInPoint;
        distInPoint = Math.sqrt(Math.pow((this.z - this.x) ,2) + Math.pow((this.v - this.y) ,2));
        return distInPoint;
    }

}


