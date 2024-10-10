package org.brentwardindustries.main;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.object.AxeObject;
import org.brentwardindustries.object.BlueHeartObject;
import org.brentwardindustries.object.BootsObject;
import org.brentwardindustries.object.ChestObject;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.CoinGoldObject;
import org.brentwardindustries.object.CoinSilverObject;
import org.brentwardindustries.object.DoorIronObject;
import org.brentwardindustries.object.DoorObject;
import org.brentwardindustries.object.FireballObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.KeyObject;
import org.brentwardindustries.object.LanternObject;
import org.brentwardindustries.object.MagicCrystalObject;
import org.brentwardindustries.object.PickaxeObject;
import org.brentwardindustries.object.PotionBlueObject;
import org.brentwardindustries.object.PotionRedObject;
import org.brentwardindustries.object.ReturnOrbObject;
import org.brentwardindustries.object.RockObject;
import org.brentwardindustries.object.ShieldBlueObject;
import org.brentwardindustries.object.ShieldWoodObject;
import org.brentwardindustries.object.SwordBlueObject;
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
            case BlueHeartObject.objectName -> object = new BlueHeartObject(gp);
            case BootsObject.objectName -> object = new BootsObject(gp);
            case ChestObject.objectName -> object = new ChestObject(gp);
            case CoinBronzeObject.objectName -> object = new CoinBronzeObject(gp);
            case CoinGoldObject.objectName -> object = new CoinGoldObject(gp);
            case CoinSilverObject.objectName -> object = new CoinSilverObject(gp);
            case DoorIronObject.objectName -> object = new DoorIronObject(gp);
            case DoorObject.objectName -> object = new DoorObject(gp);
            case FireballObject.objectName -> object = new FireballObject(gp);
            case HeartObject.objectName -> object = new HeartObject(gp);
            case KeyObject.objectName -> object = new KeyObject(gp);
            case LanternObject.objectName -> object = new LanternObject(gp);
            case MagicCrystalObject.objectName -> object = new MagicCrystalObject(gp);
            case PickaxeObject.objectName -> object = new PickaxeObject(gp);
            case PotionBlueObject.objectName -> object = new PotionBlueObject(gp);
            case PotionRedObject.objectName -> object = new PotionRedObject(gp);
            case ReturnOrbObject.objectName -> object = new ReturnOrbObject(gp);
            case RockObject.objectName -> object = new RockObject(gp);
            case ShieldBlueObject.objectName -> object = new ShieldBlueObject(gp);
            case ShieldWoodObject.objectName -> object = new ShieldWoodObject(gp);
            case SwordBlueObject.objectName -> object = new SwordBlueObject(gp);
            case SwordNormalObject.objectName -> object = new SwordNormalObject(gp);
            case TentObject.objectName -> object = new TentObject(gp);
        }
        return object;
    }
}
