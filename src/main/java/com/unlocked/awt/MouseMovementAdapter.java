package com.unlocked.awt;

import com.unlocked.Camera;

import javax.media.opengl.awt.GLCanvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by max_tolstykh on 26/08/14.
 */
public class MouseMovementAdapter extends MouseAdapter {

    private GLCanvas glcanvas;

    public MouseMovementAdapter(GLCanvas glcanvas) {
        this.glcanvas = glcanvas;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        float scaleX = scale(glcanvas.getWidth(), e.getX());
        float scaleY = scale(glcanvas.getHeight(), e.getY());

        Camera.x = (float) Math.sin(scaleX) + 1.0f;
        Camera.y = (float) Math.cos(scaleX) + 1.0f;
        Camera.z = (float) Math.sin(scaleY);
    }

    public static float scale(int param, int _x) {
        return (float) (_x - param / 2) * (float) Math.PI / (param / 2);
    }
}
