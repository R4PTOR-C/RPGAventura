package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_STDoor extends SuperObject{

    public OBJ_STDoor(){

        name = "STDoor";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/stone_door.png"));
        }catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }
}
