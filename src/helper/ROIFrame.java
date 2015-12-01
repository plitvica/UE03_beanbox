package helper;

import Catalano.Imaging.FastBitmap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by manue on 30.11.2015.
 */
public class ROIFrame extends JDialog {
    private Rectangle rec;
    private Container panel;
    private int x = 0;
    private int y = 55;
    private int height = 80;
    private int width = 420;
    private int minX;
    private int minY;
    BufferedImage bi;

    public ROIFrame(Frame owner, String title, boolean modal, FastBitmap img) {
        super(owner, title, modal);

        bi = img.toBufferedImage();
        this.setSize(img.getWidth(), (img.getHeight()) + 50);
        this.getContentPane().add(new JLabel(new ImageIcon(bi)));
        panel = getContentPane();
        initializeDrag();
        this.pack();
        this.setVisible(true);
    }

    private void initializeDrag() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                minX = Math.min(x, e.getX());
                minY = Math.min(y, e.getY());
                width = Math.max(y,e.getY()) - minY;
                height = Math.max(x, e.getX()) - minX;
                rec = new Rectangle(x, y, width,height);
            }
        });
    }

    public Rectangle getRec() {
        return rec;
    }

//    public void setRec(Rectangle rec) {
//        this.rec = rec;
//    }
}
