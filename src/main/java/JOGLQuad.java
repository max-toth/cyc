import javax.media.opengl.GLProfile;
import javax.swing.*;

public class JOGLQuad {

    public static void main(String[] args) {

        GLProfile.initSingleton();
        GLProfile glp = GLProfile.getMaxFixedFunc(true);

        final JFrame frame = new JFrame("Jogl Quad drawing");
        frame.setSize(200, 75);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}