package NameHere;

import java.lang.reflect.Executable;

public class ItemData {
    public ItemData() throws Exception{
        throw new Exception("Do not create objects of this classs");
    }
    public static Item GraveFlower = new Item(0, 5,"Grave Flower", "A Flower said to cure sickness", 30, 50);
    public static Item tombStone = new Item(1, 0,"Tombstone", "Wait,how does that work?", 15, 25);
    public static Item LavaVial = new Item(3, 0, "Lava Vial", "A vial of molten lava.", 15, 15);
    public static Item swampPot = new Item(2, 2, "Swamp Potion","A mystery potion created by a witch in the swamp.", 40, 45);
    public static Item toxicFang = new Item(1, 0, "Toxic Fang", "A strange toxic fang of some creature.", 10, 15);
    public static Item doransBlade = new Item(3, 2, "Doran's Blade", "A blade forged in the heart of Doran", 80, 55);
    public static Item woodenSword = new Item(1,0,"Wooden Sword", "I mean it kinda helps?", 3, 10);
    public static Item warriorSword = new Item(4, 0, "Warrior's Sword", "He's not really a warrior.", 6, 20);
    public static Item bountyHunterSword = new Item(2,0,"Bounty Hunter's Sword", "Why was he hunting you? Don't ask me.", 4, 7);
    public static Item slimeShield = new Item(0,5,"Slime Shield", "Is it really a shield if it's permeable?", 6, 20);
    public static Item giantSkin = new Item(0,5,"Giant Skin", "Wait but aren't giants just large humans?", 6, 20);
    public static Item bloodStone = new Item(0,5,"Blood Stone", "Not made of blood.", 10, 80);
    public static Item demonSword = new Item(10,0,"Demon Sword", "Where did it come from?", 10, 90);
    public static Item skeletonBone = new Item(5,0,"Skeleton Bone", "Why does a ribcage deal more damage than a sword?", 7, 35);
    public static Item soul = new Item(1,20,"Soul", "I-it's just a soul why does it give you health?", 10, 90);

}
