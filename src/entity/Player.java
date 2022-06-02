package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler KeyH;

    public final int screenx;
    public final int screeny;
    int hasKey = 0;
    public Player(GamePanel gp, KeyHandler KeyH){

        this.gp = gp;
        this.KeyH = KeyH;

        screenx = gp.screenwidth/2 - (gp.tileSize/2);
        screeny = gp.screenheight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;


        setDefaultValue();
        getPlayerImage();
    }
    public void setDefaultValue(){

        worldX = gp.tileSize*25;
        worldY=gp.tileSize*23;
        speed=4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_right_2.png"));





        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        if (KeyH.upPressed == true || KeyH.downPressed == true || KeyH.leftPressed == true || KeyH.rightPressed == true) {

            if (KeyH.upPressed == true) {
                direction = "up";

            } else if (KeyH.downPressed == true) {
                direction = "down";

            } else if (KeyH.leftPressed == true) {
                direction = "left";

            } else if (KeyH.rightPressed == true) {
                direction = "right";

            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

        if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }



            spriteCounter++;
            if (spriteCounter > 8) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject (int i){

        if (i != 999){

            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("key:" +hasKey);
                    break;
                case "STDoor":
                    gp.playSE(3);
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("Key:"+hasKey);
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 3;
                    gp.obj[i] = null;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);

    }
}
