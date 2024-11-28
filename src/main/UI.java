package main;

import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import Object.OBJ_Blob;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage blobImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Blob blob = new OBJ_Blob();
        blobImage = blob.image;

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2){
        if(gameFinished){
            String text;
            int textLength;
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text = "Congrats!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();


            int x = gp.screenWidth/2 - textLength/2;
            int y = gp.screenHeight/2 - (gp.tileSize*2);
            g2.drawString(text, x, y);

        }else{
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(blobImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x "+ gp.player.hasBlob, 74,65);


            // COORDINATES WORLDX AND WORLDY
            if(gp.debug){
                g2.drawString("X ="+ gp.player.worldX/gp.tileSize, 50,100);
                g2.drawString("Y ="+ gp.player.worldY/gp.tileSize, 50,140);
            }

            if(messageOn){
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }





    }

}
