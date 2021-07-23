package frc.robot;

public class Util {

    //absoluteDifference
    public static boolean eps(double a, double b, double eps) {
        return Math.abs(a - b) < eps;
    }

}
