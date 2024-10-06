package org.brentwardindustries.monster;

import org.brentwardindustries.data.Progress;
import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.DoorIronObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.MagicCrystalObject;

import java.util.Random;

public class SkeletonLordMonster extends Entity {
    GamePanel gp;
    public static final String monsterName = "Skeleton Lord";

    public final int imageScale = 5;

    public SkeletonLordMonster(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeMonster;
        boss = true;
        name = monsterName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 10;
        defense = 2;
        exp = 50;
        knockBackPower = 5;
        sleep = true;

        int size = gp.tileSize * imageScale;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - (48 * 2);
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;
        attackWindupDuration = 25;
        attackDuration = 50;

        getImage();
        getAttackImage();
        setDialogue();
    }

    public void getImage() {
        if (!inRage) {
            up1 = setup("/monsters/skeletonlord_up_1", gp.tileSize * imageScale, gp.tileSize * imageScale);
            up2 = setup("/monsters/skeletonlord_up_2", gp.tileSize * imageScale, gp.tileSize * imageScale);
            down1 = setup("/monsters/skeletonlord_down_1", gp.tileSize * imageScale, gp.tileSize * imageScale);
            down2 = setup("/monsters/skeletonlord_down_2", gp.tileSize * imageScale, gp.tileSize * imageScale);
            left1 = setup("/monsters/skeletonlord_left_1", gp.tileSize * imageScale, gp.tileSize * imageScale);
            left2 = setup("/monsters/skeletonlord_left_2", gp.tileSize * imageScale, gp.tileSize * imageScale);
            right1 = setup("/monsters/skeletonlord_right_1", gp.tileSize * imageScale, gp.tileSize * imageScale);
            right2 = setup("/monsters/skeletonlord_right_2", gp.tileSize * imageScale, gp.tileSize * imageScale);
        } else {
            up1 = setup("/monsters/skeletonlord_phase2_up_1", gp.tileSize * imageScale, gp.tileSize * imageScale);
            up2 = setup("/monsters/skeletonlord_phase2_up_2", gp.tileSize * imageScale, gp.tileSize * imageScale);
            down1 = setup("/monsters/skeletonlord_phase2_down_1", gp.tileSize * imageScale, gp.tileSize * imageScale);
            down2 = setup("/monsters/skeletonlord_phase2_down_2", gp.tileSize * imageScale, gp.tileSize * imageScale);
            left1 = setup("/monsters/skeletonlord_phase2_left_1", gp.tileSize * imageScale, gp.tileSize * imageScale);
            left2 = setup("/monsters/skeletonlord_phase2_left_2", gp.tileSize * imageScale, gp.tileSize * imageScale);
            right1 = setup("/monsters/skeletonlord_phase2_right_1", gp.tileSize * imageScale, gp.tileSize * imageScale);
            right2 = setup("/monsters/skeletonlord_phase2_right_2", gp.tileSize * imageScale, gp.tileSize * imageScale);
        }
    }

    public void getAttackImage() {
        if (!inRage) {
            attackUp1 = setup("/monsters/skeletonlord_attack_up_1", gp.tileSize * imageScale, gp.tileSize * 2 * imageScale);
            attackUp2 = setup("/monsters/skeletonlord_attack_up_2", gp.tileSize * imageScale, gp.tileSize * 2 * imageScale);
            attackDown1 = setup("/monsters/skeletonlord_attack_down_1", gp.tileSize * imageScale, gp.tileSize * 2 * imageScale);
            attackDown2 = setup("/monsters/skeletonlord_attack_down_2", gp.tileSize * imageScale, gp.tileSize * 2 * imageScale);
            attackLeft1 = setup("/monsters/skeletonlord_attack_left_1", gp.tileSize * 2 * imageScale, gp.tileSize * imageScale);
            attackLeft2 = setup("/monsters/skeletonlord_attack_left_2", gp.tileSize * 2 * imageScale, gp.tileSize * imageScale);
            attackRight1 = setup("/monsters/skeletonlord_attack_right_1", gp.tileSize * 2 * imageScale, gp.tileSize * imageScale);
            attackRight2 = setup("/monsters/skeletonlord_attack_right_2", gp.tileSize * 2 * imageScale, gp.tileSize * imageScale);
        } else {
            attackUp1 = setup("/monsters/skeletonlord_phase2_attack_up_1", gp.tileSize * imageScale, gp.tileSize * 2 * imageScale);
            attackUp2 = setup("/monsters/skeletonlord_phase2_attack_up_2", gp.tileSize * imageScale, gp.tileSize * 2 * imageScale);
            attackDown1 = setup("/monsters/skeletonlord_phase2_attack_down_1", gp.tileSize * imageScale, gp.tileSize * 2 * imageScale);
            attackDown2 = setup("/monsters/skeletonlord_phase2_attack_down_2", gp.tileSize * imageScale, gp.tileSize * 2 * imageScale);
            attackLeft1 = setup("/monsters/skeletonlord_phase2_attack_left_1", gp.tileSize * 2 * imageScale, gp.tileSize * imageScale);
            attackLeft2 = setup("/monsters/skeletonlord_phase2_attack_left_2", gp.tileSize * 2 * imageScale, gp.tileSize * imageScale);
            attackRight1 = setup("/monsters/skeletonlord_phase2_attack_right_1", gp.tileSize * 2 * imageScale, gp.tileSize * imageScale);
            attackRight2 = setup("/monsters/skeletonlord_phase2_attack_right_2", gp.tileSize * 2 * imageScale, gp.tileSize * imageScale);
        }
    }

    public void setDialogue() {
        dialogues[0][0] = "No one can steal my treasure!";
        dialogues[0][1] = "You will not leave this place alive!";
        dialogues[0][2] = "Welcome to your DOOM!!!";
    }

    public void setAction() {
        if (!inRage && life < maxLife / 2) {
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
        }
        if (getTileDistance(gp.player) < 10) {
            moveTowardPlayer(60);
        } else {
            // Get a random direction
            getRandomDirection(60);
        }

        // Check if attacks
        if (!attacking) {
            checkAttack(60, gp.tileSize * 7, gp.tileSize * 5);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
    }

    public void checkDrop() {
        gp.bossBattleOn = false;
        Progress.skeletonLordDefeated = true;

        // Restore the previous music
        gp.stopMusic();
        gp.playMusic(19);

        // Remove the iron doors
        for (int i = 0; i < gp.objects[gp.currentMap].length; i++) {
            if (gp.objects[gp.currentMap][i] != null
                    && gp.objects[gp.currentMap][i].name.equals(DoorIronObject.objectName)) {
                gp.playSE(21);
                gp.objects[gp.currentMap][i] = null;
            }
        }

        int die = new Random().nextInt(100) + 1;
        if (die < 50) {
            dropItem(new CoinBronzeObject(gp));
        }
        if (die >= 50 && die < 75) {
            dropItem(new HeartObject(gp));
        }
        if (die >= 75 && die < 100) {
            dropItem(new MagicCrystalObject(gp));
        }
    }
}
