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
        frame.add(this
