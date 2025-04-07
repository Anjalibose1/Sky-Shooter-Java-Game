import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SkyShooterGame extends JPanel implements ActionListener, KeyListener {
    Timer timer;
    int shipX = 250;
    int bulletSpeed = 10;
    int enemyY = 0;
    int enemyX = 100;
    int score = 0;

    ArrayList<Bullet> bullets = new ArrayList<>();

    public SkyShooterGame() {
        JFrame frame = new JFrame("Sky Shooter Game");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addKeyListener(this);
        timer = new Timer(30, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(Color.black);
        g.fillRect(0, 0, 600, 600);

        // Spaceship
        g.setColor(Color.green);
        g.fillRect(shipX, 500, 50, 20);

        // Bullets
        g.setColor(Color.yellow);
        for (Bullet b : bullets) {
            g.fillRect(b.x, b.y, 5, 10);
        }

        // Enemy
        g.setColor(Color.red);
        g.fillRect(enemyX, enemyY, 50, 20);

        // Score
        g.setColor(Color.white);
        g.drawString("Score: " + score, 10, 20);
    }

    public void actionPerformed(ActionEvent e) {
        enemyY += 2;
        if (enemyY > 600) {
            enemyY = 0;
            enemyX = (int) (Math.random() * 550);
        }

        // Move bullets
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.y -= bulletSpeed;
            if (b.y < 0) {
                bullets.remove(i);
                i--;
            } else if (b.x >= enemyX && b.x <= enemyX + 50 && b.y >= enemyY && b.y <= enemyY + 20) {
                bullets.remove(i);
                score += 10;
                enemyY = 0;
                enemyX = (int) (Math.random() * 550);
                i--;
            }
        }

        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && shipX > 0) {
            shipX -= 20;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && shipX < 550) {
            shipX += 20;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullets.add(new Bullet(shipX + 22, 490));
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new SkyShooterGame();
    }

    class Bullet {
        int x, y;
        Bullet(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
