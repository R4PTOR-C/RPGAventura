package main;

import object.OBJ_Boots;
import object.OBJ_key;
import object.OBJ_chest;
import object.OBJ_STDoor;
public class AssetSetter {

    GamePanel gp;

    public AssetSetter (GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){

        gp.obj[0] = new OBJ_key();
        gp.obj[0].worldX = 13 * gp.tileSize;
        gp.obj[0].worldY = 16 * gp.tileSize;

        gp.obj[1] = new OBJ_chest();
        gp.obj[1].worldX = 35 * gp.tileSize;
        gp.obj[1].worldY = 4 * gp.tileSize;

        gp.obj[2] = new OBJ_STDoor();
        gp.obj[2].worldX = 25 * gp.tileSize;
        gp.obj[2].worldY = 40 * gp.tileSize;

        gp.obj[3] = new OBJ_Boots();
        gp.obj[3].worldX = 1 * gp.tileSize;
        gp.obj[3].worldY = 35 * gp.tileSize;
    }
}
