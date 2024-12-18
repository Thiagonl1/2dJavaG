package main;

import entity.Entity;
import entity.Player;
import entity.Sword;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public int checkEntity(Entity enabler,Entity[] reciever){

        int index = 999;

        for(int i = 0; i < reciever.length; i++){
            if(reciever[i] != null){
                // Get entity's solid area position
                enabler.solidArea.x = enabler.worldX + enabler.solidArea.x;
                enabler.solidArea.y = enabler.worldY + enabler.solidArea.y;
                // Get the object's solid area position
                reciever[i].solidArea.x = reciever[i].worldX + reciever[i].solidArea.x;
                reciever[i].solidArea.y = reciever[i].worldY + reciever[i].solidArea.y;

                switch(enabler.direction) {

                    case "up":
                        enabler.solidArea.y -= enabler.speed;
                        if(enabler.solidArea.intersects(reciever[i].solidArea)){
                            enabler.collisionOn = true;
                                index = i;
                        }

                        break;

                    case "down":
                        enabler.solidArea.y += enabler.speed;
                        if(enabler.solidArea.intersects(reciever[i].solidArea)){
                            enabler.collisionOn = true;
                            index = i;
                        }

                        break;

                    case "left":
                        enabler.solidArea.x -= enabler.speed;
                        if(enabler.solidArea.intersects(reciever[i].solidArea)){
                            enabler.collisionOn = true;
                                index = i;
                        }
                        break;

                    case "right":
                        enabler.solidArea.x += enabler.speed;
                        if(enabler.solidArea.intersects(reciever[i].solidArea)){
                            enabler.collisionOn = true;
                                index = i;
                        }
                        break;
                }
                enabler.solidArea.x = enabler.solidAreaDefaultX;
                enabler.solidArea.y = enabler.solidAreaDefaultY;
                reciever[i].solidArea.x = reciever[i].solidAreaDefaultX;
                reciever[i].solidArea.y = reciever[i].solidAreaDefaultY;

            }
        }

        return index;

    }


    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottombWorldY = entity.worldY + entity.solidArea.x + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottombWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol] [entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol] [entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottombWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol] [entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol] [entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol] [entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol] [entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }

                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol] [entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol] [entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player ){

        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.direction) {

                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }


                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }

                        break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;

                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    public void checkPlayer(Entity entity){
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        // Get the object's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch(entity.direction) {

            case "up":
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }

                break;

            case "down":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collisionOn = true;
                }

                break;

            case "left":
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collisionOn = true;
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

    }


}

