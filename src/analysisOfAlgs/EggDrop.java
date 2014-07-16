/**
 * 
 */
package analysisOfAlgs;

/**
 * @author Lea
 * @file EggDrop.java
 * @problem Suppose that you have an N-story building and plenty of eggs. An egg
 *          breaks if it is dropped from floor T or higher and does not break
 *          otherwise. Your goal is to devise a strategy to determine the value
 *          of T given the following limitations on the number of eggs and
 *          tosses:
 * @date 28.02.2014.
 * @modified 06.03.2014.
 * @version 3.1
 */
public class EggDrop {

    private int N;

    public EggDrop(int floorsNum) {
        this.N = floorsNum;
    }

    /**
     * @description 1 egg, <= T tosses -> simple linear search
     */
    public int version0() {
        System.out.println("Version 0 number of eggs: 1");
        Egg egg = new Egg();
        int floor = 0;
        while (!egg.isBroken && floor < N) {
            egg.dropEgg(floor);
            if (!egg.isBroken) {
                floor++;
            }
        }
        if (floor >= N) {
            return -1;
        }
        return floor;
    }

    /**
     * @description logN eggs, logN tosses -> binary search
     */
    public int version1() {

        int eggNum = (int) Math.ceil((Math.log10(N) / Math.log10(2)));
        Egg[] eggs = new Egg[eggNum];
        System.out.println("Version 1 number of eggs: " + eggNum);
        for (int i = 0; i < eggNum; i++) {
            Egg eggNew = new Egg();
            eggs[i] = eggNew;
        }
        return binarySearch(0, N - 1, eggs);
    }

    /**
     * @description logT eggs, 2logT tosses -> find an interval containing T of
     *              size <= T, then do binary search. How to find the interval?
     *              Throw from the 1st, 2nd, 4th, 8th floor, until it breaks for
     *              the first time - that means T is in the interval between 2^x
     *              and 2^(x-1)
     */
    public int version2() {
        Egg egg = new Egg();
        int lo = 0, hi = N - 1;
        int limit = (int) Math.pow(2,
                (int) ((Math.log10(N) / Math.log10(2)) + 1));
        for (int i = 1; i <= limit; i *= 2) {
            egg.dropEgg(i);
            if (egg.isBroken) {
                lo = i / 2;
                hi = i;
                if (hi >= N) {
                    hi = N - 1;
                }
                break;
            }
        }

        if (egg.isBroken) {
            int interval = hi - lo + 1;
            int eggNum = (int) Math
                    .ceil((Math.log10(interval) / (Math.log10(2))));
            Egg[] eggs = new Egg[eggNum];
            System.out.println("Version 2 number of eggs used to determine interval of size < T: 1, number of eggs for binary search: "
                            + eggNum);
            for (int i = 0; i < eggNum; i++) {
                Egg eggNew = new Egg();
                eggs[i] = eggNew;
            }
            int result = binarySearch(lo, hi - 1, eggs);
            if (result == -1) {
                return hi;
            } else {
                return result;
            }

        }
        return -1;
    }

    /**
     * @description 2 eggs, 2*sqrt(N) tosses -> find an interval containing T of
     *              size sqrt(N), then do sequential search. How to find the
     *              interval? Throw from the sqrt(N), 2*sqrt(N), 3*sqrt(N), ...
     *              N floor, until it breaks for the first time - that means T
     *              is in the interval between (n-1)*sqrt(N) and n*sqrt(N) floor
     */
    public int version3() {
        int interval = (int) Math.ceil(Math.sqrt(N));
        int lo = 0, hi = 0;
        Egg firstEgg = new Egg();
        for (int i = 1; i <= interval; i++) {
            int upper = i * interval;
            firstEgg.dropEgg(i * interval);
            if (firstEgg.isBroken) {
                lo = (i - 1) * interval;
                hi = upper;
                if (upper >= N) {
                    hi = N - 1;
                }
                break;
            }
        }
        if (firstEgg.isBroken) {
            Egg secondEgg = new Egg();
            for (int i = lo; i < hi; i++) {
                secondEgg.dropEgg(i);
                if (secondEgg.isBroken) {
                    return i;
                }
            }
            return hi;
        }

        return -1;
    }

    /**
     * @description 2 eggs, <= csqrt(T) tosses -> throw the first egg on the
     *              floor of sequence 1^2, 2^2, 3^2,...(t-1)^2, t^2. On the
     *              floor t^2, the first egg is broken and then we do sequential
     *              search from (t-1)^2 to t^2 by using the second egg. 1 + 2 +
     *              3 + 4 + ...+ (t - 1) + t ~ 1/2 t^2, 1/2 (t-1)^2 < T <= 1/2
     *              t^2 -> t ~ sqrt(2T), total number of tosses = 2t =
     *              2sqrt(2T), c = 2sqrt(2)
     * 
     * @return T
     */
    public int version4() {
        Egg firstEgg = new Egg();
        int lo = 0, hi = 0;
        for (int t = 1; t <= Math.sqrt(N); t++) {
            int upper = (int) Math.pow(t, 2);
            firstEgg.dropEgg(upper);
            if (firstEgg.isBroken) {
                hi = upper;
                lo = (int) Math.pow(t - 1, 2);
                if (hi >= N) {
                    hi = N - 1;
                }
                break;
            }
        }
        if (firstEgg.isBroken) {
            Egg secondEgg = new Egg();
            for (int i = lo; i <= hi; i++) {
                secondEgg.dropEgg(i);
                if (secondEgg.isBroken) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @description binary search for finding T
     * @param lo
     *            lower limit for binary search
     * @param hi
     *            higher limit for binary search
     * @param eggs
     *            used eggs
     * @return T
     */
    private int binarySearch(int lo, int hi, Egg[] eggs) {

        int mid = (hi - lo) / 2 + lo;
        int i = 0;
        while (lo <= hi) {
            if (lo == hi) {
                if (i == 0) {
                    eggs[i].dropEgg(lo);
                    if (!eggs[i].isBroken) {
                        System.out.println("Binary search, number of eggs used: 0");
                        return -1;
                    }
                    i++;
                }
                System.out.println("Binary search, number of eggs used: " + i);
                return lo;
            }
            mid = (hi - lo) / 2 + lo;
            eggs[i].dropEgg(mid);
            if (!eggs[i].isBroken) {
                lo = mid + 1;
            } else {
                i++;
                hi = mid;
            }
        }
        return -1;
    }

    /**
     * @description inner class
     */
    private class Egg {

        private boolean isBroken = false;
        private int     T;

        public Egg() {
            this.T = 49;
        }

        public void dropEgg(int floor) {
            if (floor >= T) {
                isBroken = true;
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int N = 100;
        EggDrop ed = new EggDrop(N);

        System.out.println("Version 0: 1 egg dropped, <= T tosses, floor =  "
                + ed.version0());
        System.out.println();
        System.out
                .println("Version 1: logN eggs dropped, logN tosses, floor =  "
                        + ed.version1());
        System.out.println();
        System.out
                .println("Version 2: logT eggs dropped, 2logT tosses, floor =  "
                        + ed.version2());
        System.out.println();
        System.out
                .println("Version 3: 2 eggs dropped, 2*sqrt(N) tosses, floor =  "
                        + ed.version3());
        System.out.println();
        System.out
                .println("Version 4: 2 eggs dropped, <=c*sqrt(N) tosses, c = 2*sqrt(2), floor =  "
                        + ed.version4());
    }

}
