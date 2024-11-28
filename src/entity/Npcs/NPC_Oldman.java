package entity.Npcs;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_Oldman extends Entity {


    public NPC_Oldman(GamePanel gp){

        super(gp);
        speed = 1;
        direction = "down";
        name = "Random";

        getNPCImage();
    }

    public void getNPCImage(){
        try{
            upFrames = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_up1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down3.png"))
            };
            downFrames = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down3.png"))

            };

            leftFrames = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_left1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down1.png"))
            };
            rightFrames = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_right1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Walk/player_down1.png"))
            };
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if ( i <= 25)
                direction = "up";
            if( i > 25 && i<=50)
                direction = "down";

            if( i > 50 && i <= 75)
                direction = "right";
            if( i > 50 && i <= 100)
                direction = "left";

            actionLockCounter = 0;
        }



    }
}
