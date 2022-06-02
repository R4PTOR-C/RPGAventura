package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX;
    public int worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp){

        int screenx = worldX - gp.player.worldX + gp.player.screenx;
        int screeny = worldY - gp.player.worldY + gp.player.screeny;


        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenx &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenx &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screeny &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screeny) {

            g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
        }

    }
}
