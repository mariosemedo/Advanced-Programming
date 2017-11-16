package labs.lab2.bouncingBalls;

//original code by C Horstmann, reorganised by N. Voelker

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BounceFrame extends JFrame {

  private BallPanel panel;

  public static final int DEFAULT_WIDTH = 450;

  public static final int DEFAULT_HEIGHT = 350;

  public static final int STEPS = 1000;

  public static final int DELAY = 3;

  /**
   * Constructs the frame with the panel for showing the bouncing ball and Start
   * and Close buttons
   */
  public BounceFrame() {
    setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setTitle("BounceThread");

    panel = new BallPanel();
    add(panel, BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel();
    addButton(buttonPanel, "Start", new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        addBall();
      }
    });

    addButton(buttonPanel, "Close", new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        System.exit(0);
      }
    });
    add(buttonPanel, BorderLayout.SOUTH);
  }

  public void addButton(Container c, String title, ActionListener listener) {
    JButton button = new JButton(title);
    c.add(button);
    button.addActionListener(listener);
  }

  public void addBall() {
    Ball b = new Ball();
    panel.add(b);
    Runnable r = new BallRunnable(b, panel);
    Thread t = new Thread(r);
    t.start();
  }

  // local class 
  class BallPanel extends JPanel {
   
    public void add(Ball b) {
      balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      for (Ball b : balls) {
        g2.fill(b.getShape());
      }
    }

    private ArrayList<Ball> balls = new ArrayList<Ball>();
  }

  public static void main(String[] args) {
    JFrame frame = new BounceFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

}
