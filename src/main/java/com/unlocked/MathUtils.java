package com.unlocked;

import com.unlocked.objects.Vertex;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by max_tolstykh on 31/08/14.
 */
public class MathUtils {
    public static void rotate(Vertex v, Vertex vector, float alpha) {
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

    public static void multiply(Vertex vertex, float matrix[][]) {
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
}
