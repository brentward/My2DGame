package org.brentwardindustries.main;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.object.AxeObject;
import org.brentwardindustries.object.BootsObject;
import org.brentwardindustries.object.ChestObject;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.DoorObject;
import org.brentwardindustries.object.FireballObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.KeyObject;
import org.brentwardindustries.object.LanternObject;
import org.brentwardindustries.object.MagicCrystalObject;
import org.brentwardindustries.object.PotionRedObject;
import org.brentwardindustries.object.RockObject;
import org.brentwardindustries.object.ShieldBlueObject;
import org.brentwardindustries.object.ShieldWoodObject;
import org.brentwardindustries.object.SwordMagicObject;
import org.brentwardindustries.object.SwordNormalObject;
import org.brentwardindustries.object.TentObject;

public class EntityGenerator {
    GamePanel gp;

    public EntityGenerator(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {
        Entity object = null;

        switch (itemName) {
            case AxeObject.objectName -> object = new AxeObject(gp);
            case BootsObject.objectName -> object = new BootsObject(gp);
            case ChestObject.objectName -> object = new ChestObject(gp);
            case CoinBronzeObject.objectName -> object = new CoinBronzeObject(gp);
            case DoorObject.objectName -> object = new DoorObject(gp);
            case FireballObject.objectName -> object = new FireballObject(gp);
            case HeartObject.objectName -> object = new HeartObject(gp);
            case KeyObject.objectName -> object = new KeyObject(gp);
            case LanternObject.objectName -> object = new LanternObject(gp);
            case MagicCrystalObject.objectName -> object = new MagicCrystalObject(gp);
            case PotionRedObject.objectName -> object = new PotionRedObject(gp);
            case RockObject.objectName -> object = new RockObject(gp);
            case ShieldBlueObject.objectName -> object = new ShieldBlueObject(gp);
            case ShieldWoodObject.objectName -> object = new ShieldWoodObject(gp);
            case SwordMagicObject.objectName -> object = new SwordMagicObject(gp);
            case SwordNormalObject.objectName -> object = new SwordNormalObject(gp);
            case TentObject.objectName -> object = new TentObject(gp);
        }
        return object;
    }
}
