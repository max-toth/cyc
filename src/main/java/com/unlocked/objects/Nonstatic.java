package com.unlocked.objects;

/**
 * Created by max_tolstykh on 25/08/14.
 */
public interface Nonstatic {
    public void move(float x, float y);
    public void moveUp();
    public void moveDown();
    public void brakes();
    public void inertia();
    public float velocity();
    public float direction();
}
