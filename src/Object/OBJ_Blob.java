package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Blob extends SuperObject{
    public OBJ_Blob (){
        name = "Blob";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/blob.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
