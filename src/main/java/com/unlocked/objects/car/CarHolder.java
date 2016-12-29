package com.unlocked.objects.car;

/**
 * @author mtolstyh
 * @since 24.12.2016.
 */
public class CarHolder {

    private static Car car;

    public static Car getCar() {
        return car;
    }

    public static void setCar(Car car) {
        CarHolder.car = car;
    }
}
