package com.unlocked.objects;

import com.unlocked.PhysicConstants;

import javax.media.opengl.GL2;

/**
 * User: mtolstykh
 * Date: 20.08.14
 * Time: 16:25
 */
public class Vertex implements Nonstatic, Drawable {
    private static final float BRAKES_POWER = 0.0005f;
    float x, y, z;
    float V = 0.0f;
    float direction = 0.0f;
    float a = 0.0f;
    float brakesPower = BRAKES_POWER;
    boolean inertia;

    public Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void draw(GL2 gl2) {
        gl2.glVertex3f(this.x, this.y, this.z);
    }

    public void update(Vertex vertex) {
        this.x = vertex.x;
        this.y = vertex.y;
        this.z = vertex.z;
    }

    @Override
    public void move(float x, float y) {

        if (V > 0.0f && inertia) V -= a;
        if (V < 0.0f && inertia) V += a;
        this.x += x;
        this.y += y;
    }

    @Override
    public void moveDown() {
        inertia = false;
        if (V > -0.003f) {
            a += 0.0001f - PhysicConstants.At;
            V -= a;
        }

        move(direction, V);
    }

    @Override
    public void moveUp() {
        inertia = false;
        if (a < 1.0f) {
            a += 0.0001f - PhysicConstants.At;
            V += a;
        }

        move(direction, V);
    }

    @Override
    public void brakes() {
        if (V > 0) {
            if (V > 0.001) {
                V -= brakesPower;
                brakesPower += 0.0001f;
            } else {
                V = 0;
                brakesPower = BRAKES_POWER;
            }
        }
        if (V < 0) {
            if (V < -0.001f) {
                V += brakesPower;
                brakesPower += 0.0001f;
            } else {
                V = 0;
                brakesPower = BRAKES_POWER;
            }
        }
        inertia = false;
    }

    @Override
    public void inertia() {
        this.a = PhysicConstants.At;
        this.inertia = true;
    }

    @Override
    public float velocity() {
        return V;
    }

    @Override
    public float direction() {
        return x;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}