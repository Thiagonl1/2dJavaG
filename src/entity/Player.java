package entity;

import javax.imageio.ImageIO;
import main.KeyHandler;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasBlob = 0;
    public boolean debug = false;
    int currentFrame = 0;
    int animationSpeed = 10;
    BufferedImage[] upFrames, downFrames, leftFrames, rightFrames, attackDownFrames, attackUpFrames, attackRightFrames, attackLeftFrames;
    Sword sword;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32); // 8, 16, 32, 32

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        sword = new Sword(gp);

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }


    public void getPlayerImage(){
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

    public void update() {
                /*
        in JAVA the uppder left corner is X = 0  Y = 0
                X values increases to the right
                Y values increases as they go down
                */

        // Attack Animation handler
        if (sword.isAttacking) {
            sword.update();
            return; // Skip normal movement while attacking
        }
        // Movement Handler
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.shiftPressed || keyH.pKeyPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }
            if(keyH.pKeyPressed){
                sword.isAttacking = true;
            }
            if(keyH.shiftPressed){
                debug = true;
                sword.debug = true;
            }

            // Check the colllision
            collisionOn = false;
            gp.cChecker.checkTile(this);


            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

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
            spriteCounter++;
            if (spriteCounter > animationSpeed) {
                currentFrame = (currentFrame + 1) % 3; // Loop through 3 frames
                spriteCounter = 0;
            }
        }else{
            currentFrame = 0;
        }
    }

    public void pickUpObject(int i){
        if( i != 999){
            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Blob":
                    gp.player.gp.obj[i] = null;

                    speed += 3;

                    gp.playSE(1);

                    hasBlob +=1;

                    gp.ui.showMessage("You feel excited!");
                    break;
                case "Chest":

                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(1);
                    // WORLD CHANGE
                    gp.tileM.world =  "/maps/map01.txt";
                    break;

                case "Door":
                    if(hasBlob > 0){
                        gp.playSE(1);

                        hasBlob -=1;

                        gp.player.gp.obj[i] = null;

                    }
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage playerImage = null;

        switch(direction){
            case "up":
                playerImage = upFrames[currentFrame];

                if(sword.isAttacking){
                    sword.draw(g2);
                }

                if (sword.isAttacking){
                    sword.direction = direction;
                    sword.effectY = screenY-20;
                    sword.effectX = screenX;
                }
                break;
            case "down":
                playerImage = downFrames[currentFrame];
                if (sword.isAttacking) {
                    sword.direction = direction;
                    sword.effectY = screenY+20;
                    sword.effectX = screenX;
                }
                break;
            case "left":
                playerImage = leftFrames[currentFrame];
                if (sword.isAttacking) {
                    sword.direction = direction;
                    sword.effectY = screenY-5;
                    sword.effectX = screenX-23;

                }
                break;
            case "right":
                playerImage = rightFrames[currentFrame];

                if (sword.isAttacking) {
                    sword.direction = direction;
                    sword.effectY = screenY-5;
                    sword.effectX = screenX+23;
                }
                break;

        }

        g2.drawImage(playerImage, screenX, screenY, gp.tileSize, gp.tileSize, null);

        if(direction != "up"){
            sword.draw(g2);
        }


        if (debug){
            g2.setColor(Color.RED);
            g2.drawRect(
                    screenX + solidArea.x,
                    screenY + solidArea.y,
                    solidArea.width,
                    solidArea.height
            );
        }


    }
}
