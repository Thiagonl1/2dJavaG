package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sword extends Entity{


    private int attackFrameCounter = 0;
    int effectY;
    int effectX;
    public final int screenX;
    public final int screenY;
    int animationSpeed = 7;
    BufferedImage[] attackRightFrames, attackDownFrames, attackUpFrames, attackLeftFrames;
    public boolean isAttacking = false;

    public Sword(GamePanel gp){
        super(gp);

        solidArea = new Rectangle(effectX, effectY, 14, 32);

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getSwordImage();
        setDefaultValues();
    }

    public void getSwordImage(){

        try{
            attackDownFrames = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordDown/WhooshDown1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordDown/WhooshDown2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordDown/WhooshDown3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordDown/WhooshDown4.png"))
            };
            attackUpFrames = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordUp/WhooshUp1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordUp/WhooshUp2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordUp/WhooshUp3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordUp/WhooshUp4.png"))
            };
            attackRightFrames = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordRight/WhooshRight1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordRight/WhooshRight2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordRight/WhooshRight3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordRight/WhooshRight4.png")),
            };
            attackLeftFrames = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordLeft/WhooshLeft1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordLeft/WhooshLeft2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordLeft/WhooshLeft3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/player/Attack/Sword/SwordLeft/WhooshLeft4.png"))
            };
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        //worldX = gp.tileSize * 23;
        //worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void update(){
        // en teoria tendria que chusmear si colisiona nomas
        if(isAttacking){
            attackFrameCounter++;
            // Check if attack animation has finished
            if (attackFrameCounter >= attackDownFrames.length * animationSpeed) {
                isAttacking = false; // End attack state
                attackFrameCounter = 0; // Reset counter
            }


            int entityIndex = gp.cChecker.checkEntity(this, gp.npc);
            checkEnemy(entityIndex);

            collisionOn = false;


        }
    }

    public void draw(Graphics2D g2){
        if(isAttacking){
            BufferedImage effect = null;
            switch (direction){
                case "up":
                    effect = attackUpFrames[(attackFrameCounter / animationSpeed) % attackUpFrames.length];
                    effectY = screenY - 15;
                    effectX = screenX;
                    adjustSolidArea(4, -10, 40,25);
                    break;

                case "down":
                    effect = attackDownFrames[(attackFrameCounter / animationSpeed) % attackDownFrames.length];
                    effectY = screenY+20;
                    effectX = screenX;
                    adjustSolidArea(4, 40, 40,25);
                    break;

                case "left":
                    effect = attackLeftFrames[(attackFrameCounter / animationSpeed) % attackLeftFrames.length];
                    effectY = screenY;
                    effectX = screenX-23;
                    adjustSolidArea(-20, 0, 25,40);
                    break;

                case "right":
                    effect = attackRightFrames[(attackFrameCounter / animationSpeed) % attackRightFrames.length];
                    effectY = screenY-5;
                    effectX = screenX+23;

                    adjustSolidArea(45, 0, 25,40);
                    break;
            }
            if(effect != null){
                g2.drawImage(effect, effectX, effectY, gp.tileSize, gp.tileSize, null);

                if(gp.debug){
                    g2.setColor(Color.yellow);
                    g2.drawRect(
                            screenX + solidArea.x,
                            screenY + solidArea.y,
                            solidArea.width,
                            solidArea.height
                    );
                }
            }


        }
    }

    public void adjustSolidArea(int x, int y, int width, int height){
        solidArea.x = x;
        solidArea.y = y;
        solidArea.width = width;
        solidArea.height = height;
    }

    public void checkEnemy(int i){

        if (i != 999) {
            System.out.println("asihjsaoijhshdoghjsio");
        }

    }



}
