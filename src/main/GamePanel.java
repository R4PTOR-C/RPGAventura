package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //configurações de tela
    final int originalTileSize = 16; //16x16pixels
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenwidth = tileSize * maxScreenCol; //768 pixels
    public final int screenheight = tileSize * maxScreenRow; //576 pixels

    //Configurações de mundo
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int fps = 60;

    //sistema
    TileManager tileM = new TileManager(this);
    KeyHandler KeyH = new KeyHandler();
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;

    //Entidade e objetos
    public Player player = new Player(this, KeyH);
    public SuperObject obj[] = new SuperObject[10];

    

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }


    public void setupGame() {

        aSetter.setObject();

        playMusic(0);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }



    @Override
    public void run() {

        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;

            lastTime = currentTime;

           if(delta >= 1){
            update();
            repaint();
            delta--;
           }

        }
    }

    public void update() {

        player.update();
    }
        public void paintComponent (Graphics g){

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            //tile
            tileM.draw(g2);

            //objeto
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }

            //player
            player.draw(g2);

            g2.dispose();
        }
        public void playMusic(int i){

        sound.setFile(i);
        sound.play();
        sound.loop();
        }
        public void stopMusic(){

             sound.stop();
        }
        public void playSE(int i){

        sound.setFile(i);
        sound.play();
        }

    }
