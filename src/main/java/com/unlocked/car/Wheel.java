package com.unlocked.car;

import com.unlocked.Nonstatic;
import com.unlocked.Vertex;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * User: mtolstykh
 * Date: 22.08.14
 * Time: 12:09
 */
public class Wheel implements Nonstatic{
    Vertex center;
    float radius;
    float alfa = 0;

    public float getAlfa() {
        return alfa;
    }

    public void setAlfa(float alfa) {
        this.alfa = alfa;
    }

    public Wheel(Vertex center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public void turn(int direction) {
        switch (direction) {
            case 0: //left
                if (this.alfa <= 0.8f) {
                    this.alfa += 0.03f;
                }
                break;
            case 1: //right
                if (this.alfa >= -0.8f) {
                    this.alfa -= 0.03f;
                }
                break;
        }

    }

    public void rotate(Vertex v, Vertex vector, float alpha) {
        float rotationMatrixX[][] = new float[][]{
                {
                        (float) (cos(alpha) + ((1 - cos(alpha)) * vector.getX() * vector.getX())),
                        (float) (((1 - cos(alpha)) * vector.getX() * vector.getY()) - sin(alpha) * vector.getZ()),
                        (float) ((1 - cos(alpha)) * vector.getX() * vector.getZ() + sin(alpha) * vector.getY())
                },
                {
                        (float) (((1 - cos(alpha)) * vector.getX() * vector.getY()) + sin(alpha) * vector.getZ()),
                        (float) (cos(alpha) + ((1 - cos(alpha)) * vector.getY() * vector.getY())),
                        (float) ((1 - cos(alpha)) * vector.getY() * vector.getZ() - sin(alpha) * vector.getX())
                },
                {
                        (float) (((1 - cos(alpha)) * vector.getX() * vector.getZ()) + sin(alpha) * vector.getY()),
                        (float) ((1 - cos(alpha)) * vector.getY() * vector.getZ() + sin(alpha) * vector.getX()),
                        (float) (cos(alpha) + ((1 - cos(alpha)) * vector.getZ() * vector.getZ()))
                },
        };

        multiply(v, rotationMatrixX);
    }

    public void multiply(Vertex vertex, float matrix[][]) {
        float x = vertex.getX();
        float y = vertex.getY();
        float z = vertex.getZ();
        vertex.setX(
                (x * matrix[0][0]) +
                        (y * matrix[0][1]) +
                        (z * matrix[0][2])
        );
        vertex.setY(
                (x * matrix[1][0]) +
                        (y * matrix[1][1]) +
                        (z * matrix[1][2])
        );
        vertex.setZ(
                (x * matrix[2][0]) +
                        (y * matrix[2][1]) +
                        (z * matrix[2][2])
        );
    }

    public void rotate(Vertex vertex, float alpha) {
        float rotationMatrixX[][] = new float[][]{
                {1, 0, 0},
                {0, (float) cos(alpha), (float) -sin(alpha)},
                {0, (float) sin(alpha), (float) cos(alpha)},
        };

        multiply(vertex, rotationMatrixX);
    }

    public void draw(GL2 gl2) {
        gl2.glBegin(GL2.GL_POLYGON);
        for (double s = 0.0f; s < 1.0f; s += 0.001f) {
            float theta = (float) (2.0f * Math.PI * (s) / 1.0f);
            float x = 0.0f;//(float) (radius * sin(theta));
            float y = (float) (radius * cos(theta));
            float z = (float) (radius * sin(theta));
            Vertex v = new Vertex(x, y, z);
            rotate(v, new Vertex(0, 0, 1), alfa);
            gl2.glVertex3f(v.getX() + center.getX(), v.getY() + center.getY(), v.getZ() + center.getZ());
        }

        gl2.glEnd();
    }

    @Override
    public void move(float x, float y) {
        center.move(x, y);
    }

    @Override
    public void moveUp() {
        center.moveUp();
    }

    @Override
    public void moveDown() {
        center.moveDown();
    }

    @Override
    public void brakes() {
        center.brakes();
    }

    @Override
    public void inertia() {
        center.inertia();
    }

    @Override
    public float velocity() {
        return center.velocity();
    }
}
