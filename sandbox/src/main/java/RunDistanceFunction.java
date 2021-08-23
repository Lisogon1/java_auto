public class RunDistanceFunction {
    public static void main(String[] args) {
     Point p = new Point(5,9,4,7);
     double val = distance(p);
     System.out.println("Расстояние между точками = " +val);
     System.out.println("Расстояние между точками = " +p.distanceInPoint());
    }

    private static double distance(Point p) {
            double dist;
        dist = Math.sqrt(Math.pow((p.z - p.x) ,2) + Math.pow((p.v - p.y) ,2));
        return dist;
        }
    }

