import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class MineSweeper extends JFrame {
    private Game game;
    private int COLS = 9;
    private int ROWS = 9;
    private int BOMBS = 10;
    private int IMAGE_SIZE = 32;
    private JPanel panel;
    private JLabel label;

    public static void main(String[] args) {
        new MineSweeper();
    }

    private MineSweeper(){
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel(){
        label = new JLabel("Добро пожаловать!");
        add(label, BorderLayout.SOUTH);
    }
    private void initPanel() {
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE,this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if(e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if(e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if(e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint();

            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE,Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private String getMessage(){
        return switch (game.getState()) {
            case PLAYED -> "Не подорвись!";
            case BOMBED -> "Ты проиграл!";
            case WINNER -> "Поздравляю, ты победил!";
            default -> "";
        };
    }
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Сапёр");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    private void setImages(){
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage(String name){
        String fileName = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(fileName)));
        return icon.getImage();
    }
}
