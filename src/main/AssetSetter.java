package main;
import Object.OBJ_Blob;
import Object.OBJ_Door;
import Object.OBJ_Chest;
import entity.Npcs.NPC_Oldman;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Blob();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Blob();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 8 * gp.tileSize;
        gp.obj[3].worldY = 28 * gp.tileSize;

        gp.obj[4] = new OBJ_Chest();
        gp.obj[4].worldX = 10 * gp.tileSize;
        gp.obj[4].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Blob();
        gp.obj[1].worldX = 25 * gp.tileSize;
        gp.obj[1].worldY   = 38 * gp.tileSize;
    }

    public void setNPC(){
        gp.npc[0] = new NPC_Oldman(gp);
        gp.npc[0].worldX = gp.tileSize *21;
        gp.npc[0].worldY = gp.tileSize *21;

    }

}
