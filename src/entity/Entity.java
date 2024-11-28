package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public String direction;
    public int spriteCounter = 0;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public BufferedImage[] upFrames, downFrames, leftFrames, rightFrames;
    public int currentFrame = 0;
    public int actionLockCounter;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){ }
    public void update(){

        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if (!collisionOn) {

            switch (direction) {
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


    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                worldY < gp.player.worldY + gp.player.screenY + gp.tileSize){

            switch(direction){
                case "up":
                    image = upFrames[currentFrame];
                    break;
                case "down":
                    image = downFrames[currentFrame];
                    break;
                case "left":
                    image = leftFrames[currentFrame];
                    break;
                case "right":
                    image = rightFrames[currentFrame];
                    break;

            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }


    }

}
