package NameHere;


public class ItemData {
    public ItemData() throws Exception{
        throw new Exception("Do not create objects of data classes");
    }
    public static Item MoltenGem = new Item(0, 0, "Molten Gem", "A gem made of molten lava", 8, 20, 1, 0);
    public static Item demonSword = new Item(10, 0, "Demon Sword", "Where did it come from?", 6, 90);

    public static Item TougherTimes = new Item(0, 6, "Tougher Times", "Someones old teddy bear, I heard theres a chance of rain today", 8, 25);
    public static Item DullSkull = new Item(0, 0, "Dull Skull", "You really bought a skull from a shop in a graveyard", 1, 5);
    public static Item GlowingSkull = new Item(0, 8, "Glowing Skull", "A skull that glows faintly green, it feels much heavier then it should", 20, 30, 4,3);
    public static Item GraveFlower = new Item(0, 5, "Grave Flower", "A Flower said to cure sickness", 3, 50);
    public static Item tombStone = new Item(1, 2, "Tombstone", "Wait,how does that work?", 1, 25, 1, 0);
    public static Item LavaVial = new Item(2, 0, "Lava Vial", "A vial of molten lava.", 4, 15);
    public static Item swampPot = new Item(2, 2, "Swamp Potion", "A mystery potion created by a witch in the swamp.",
                                           4, 45);
    public static Item toxicFang = new Item(1, 0, "Toxic Fang", "A strange toxic fang of some creature.", 1, 8);
    public static Item doransBlade = new Item(3, 2, "Doran's Blade", "A blade forged in the heart of Doran", 3, 55);
    public static Item woodenSword = new Item(1, 0, "Wooden Sword", "I mean it kinda helps?", 3, 10);
    public static Item warriorSword = new Item(4, 0, "Warrior's Sword", "He's not really a warrior.", 6, 20);
    public static Item bountyHunterSword = new Item(2, 0, "Bounty Hunter's Sword",
                                                    "Why was he hunting you? Don't ask me.", 4, 7);
    public static Item slimeShield = new Item(0, 5, "Slime Shield", "Is it really a shield if it's permeable?", 6, 20);
    public static Item giantSkin = new Item(0, 5, "Giant Skin", "Wait but aren't giants just large humans?", 6, 20);
    public static Item bloodStone = new Item(0, 5, "Blood Stone", "Not made of blood.", 6, 80);
    public static Item skeletonBone = new Item(5, 0, "Skeleton Bone",
                                               "Why does a ribcage deal more damage than a sword?", 7, 35);
    public static Item soul = new Item(1, 20, "Soul", "I-it's just a soul why does it give you health?", 10, 90);
    public static Item empty = new Item(0, 0, "Empty", "Nothing to see here.", 1000000, 0);
    public static Item GhostSpirit = new Item(0,1, "Ghost Spirit in a Bottle", "A bottle with a ghost trapped inside. It seems to be trying to say something",10, 25, 3,0);
    public static Item toxicWaste = new Item(10, 0, "Toxic Waste", "Where did it come from?", 6, 90);
}
