public class RunDistanceFunction {
    public static void main(String[] args) {
     Point p1 = new Point(3,5);
     Point p2 = new Point(6,8);
        double val = distance(p1,p2);
     System.out.println("Расстояние между точками = " +val);
     System.out.println("Расстояние между точками = " +p1.distanceInPoint(p2));
    }

    private static double distance(Point p2, Point p1) {
            double dist;
        dist = Math.sqrt(Math.pow((p2.x - p1.x) ,2) + Math.pow((p2.y - p1.y) ,2));
        return dist;
        }
    }

