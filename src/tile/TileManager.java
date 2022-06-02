package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[] [];

    public TileManager (GamePanel gp){

        this.gp = gp;

        tile = new Tile [10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/WorldMap");
    }

    public void getTileImage(){

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile [1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
            tile[2].collision = true;

            //tile[3] = new Tile();
            //tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chest02.png"));
            //tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[5].collision = true;

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col ++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2) {

        int worldcol = 0;
        int worldrow = 0;

        while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldcol] [worldrow];

            int worldX = worldcol * gp.tileSize;
            int worldY = worldrow * gp.tileSize;
            int screenx = worldX - gp.player.worldX + gp.player.screenx;
            int screeny = worldY - gp.player.worldY + gp.player.screeny;


            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenx &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenx &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screeny &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screeny) {

                g2.drawImage(tile[tileNum].image, screenx, screeny, gp.tileSize, gp.tileSize, null);
            }
            worldcol++;


            if (worldcol == gp.maxWorldCol){
                worldcol = 0;
                worldrow++;

            }
        }
    }
}
