package elementarySorts;

import java.util.Comparator;

/**
 * @author Lea
 * @file Point.java
 * @date 7. 5. 2014.
 * @time 14:49:49
 * @description 
 */
public class Point implements Comparable<Point>{
    
    public static final Comparator<Point> XY = new XYComparator();
    private final double x;
    private final double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double x() {
        return x;
    }
    
    public double y() {
        return y;
    }
    
    // don't need this actually, since I made it the default
    private static class XYComparator implements Comparator<Point> {
    
        public int compare(Point a, Point b) {
            if (a.x < b.x) return -1;
            else if (a.x > b.x) return 1;
            else if (a.y < b.y) return -1;
            else if (a.y > b.y) return 1;
            else return 0;
        }
    }


    /* 
     * @description order by x coordinates, when x are the same, order by y
     */
    public int compareTo(Point a) {
        if (this.x < a.x) return -1;
        else if (this.x > a.x) return 1;
        else if (this.x > a.x) return 1;
        else if (this.y < a.y) return -1;
        else return 0;
    }

}
