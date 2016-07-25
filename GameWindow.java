/**
 * Created by Viet on 7/24/2016.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class GameWindow  extends Frame implements Runnable {
    Image background;
    Image planeImage1;
    Image planeImage2;
    int planeX = 250;
    int planeY = 650;
    int planeX2 = 250;
    int planeY2 = 649;
    Thread thread;
    BufferedImage bufferedImage;
    Graphics bufferedImageGraphic;

    public GameWindow() {
        System.out.println("Game Window contructor");
        this.setTitle("VietNQ-techkids");
        this.setVisible(true);
        this.setSize(600, 800);
        setLocation(0, 0);

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("Mouse");
            }
        });
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                System.out.println("windowClosing");

            }

            @Override
            public void windowClosed(WindowEvent e) {


            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");

            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowDeiconified");

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }


            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        try {
            background = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            planeImage1 = ImageIO.read(new File("resources/plane4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            planeImage2 = ImageIO.read(new File("resources/plane1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.repaint();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        planeX -= 10;
                        break;
                    case KeyEvent.VK_RIGHT:
                        planeX += 10;
                        break;
                    case KeyEvent.VK_UP:
                        planeY -= 10;
                        break;
                    case KeyEvent.VK_DOWN:
                        planeY += 10;
                        break;
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.bufferedImage = new BufferedImage(600, 800, BufferedImage.TYPE_INT_ARGB);
        this.bufferedImageGraphic = bufferedImage.getGraphics();

        thread = new Thread(this);
        thread.start();


    }
    @Override



    public void update(Graphics g) {
        bufferedImageGraphic.drawImage(background, 0, 0, null);
        bufferedImageGraphic.drawImage(planeImage1, planeX, planeY, null);
        bufferedImageGraphic.drawImage(planeImage2,planeX2, planeY2, null);
        g.drawImage(bufferedImage, 0, 0, null);
        System.out.println("Paint");
    }

    public void run() {
        while (true) {
            try {
                Point mouseInfo= MouseInfo.getPointerInfo().getLocation();
                mouseInfo.x -= getLocationOnScreen().x;
                mouseInfo.y -= getLocationOnScreen().y;
                planeX2 = mouseInfo.x;
                planeY2 = mouseInfo.y;
                Thread.sleep(27);
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
