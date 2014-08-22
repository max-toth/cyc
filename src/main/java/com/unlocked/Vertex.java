package com.unlocked;

import javax.media.opengl.GL2;

/**
 * User: mtolstykh
 * Date: 20.08.14
 * Time: 16:25
 */
public class Vertex {
    float x,y,z;

    public Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL2 gl2) {
        gl2.glVertex3f(this.x, this.y, this.z);
    }

    public void update(Vertex vertex) {
        this.x = vertex.x;
        this.y = vertex.y;
        this.z = vertex.z;
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
}