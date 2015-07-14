package com.unlocked.objects;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author max_tolstykh
 * @since 15/07/15
 */
public class Landscape {

    private int width;
    private int height;
    private List<Short> heights = new ArrayList<Short>();
    private static final int BMP_HEADER_OFFSET = 1146;
    private static final int PNG_HEADER_OFFSET = 261;

    public Landscape(int width, int height) {
        this.width = width;
        this.height = height;

        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource("landscape2.png");
        File file = new File(resource.getFile());

        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int read = fileInputStream.read(bFile);
            int square = width * height;
            if (read - PNG_HEADER_OFFSET != square) {
                throw new Exception("Landscape heights count not equal size of landscape square.");
            }
            System.out.printf(read  + " bytes read\n");
            for ( int i =  0; i < square; i++ ) {
                byte b = bFile[i + PNG_HEADER_OFFSET];
                short e = Short.parseShort(Byte.toString(b));
                if (e < 0) e = (short) (e*(-1));
                heights.add(e);
                System.out.print(e + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Short> getHeights() {
        return heights;
    }

    public void setHeights(List<Short> heights) {
        this.heights = heights;
    }
}
