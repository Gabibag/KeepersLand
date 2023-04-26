package KeeperLand;


public class ItemData {
    /*
    damage costs 30 per point
    health costs 25 per point
    heal costs 40 per point
    heal variance costs 15 per point
    rarity is cost/100 + 1
    5% discount on higher costed items
    */
    public static Item Revolver = new Item(5, 0, "Revolver", "A huge ancient revolver, still loaded with bullets", 0, 0, true);
    public static Item Meds = new Item(0, 0, "Medication", "A container of Medicine taken from a shop in an destroyed city", 3, 0);
    public static Item RadioactiveRod = new Item(3, 3, "Radioactive Rod", "A rod taken from a reactor an underground room in a empty city", 0, 0);
    public static Item MoltenGem = new Item(0, 5, "Molten Gem", "A gem made of molten lava", 1, 0);
    public static Item demonSword = new Item(10, 0, "Demon Sword", "Where did it come from?", 1, 0);
    public static Item RadiationSuit = new Item(0, 2, "Radiation Suit", "A suit made to combat radiation, protects you", 0, 0);
    public static Item TougherTimes = new Item(0, 6, "Tougher Times", "Someones old teddy bear. I heard theres a chance of rain today", 0, 0);
    public static Item DullSkull = new Item(0, 0, "Dull Skull", "You really bought a skull from a shop in a graveyard", 0, 1);
    public static Item GlowingSkull = new Item(0, 8, "Glowing Skull", "A skull that glows faintly green, it feels much heavier then it should", 0, 0);
    public static Item GraveFlower = new Item(0, 5, "Grave Flower", "A Flower said to cure sickness", 0, 2);
    public static Item tombStone = new Item(1, 2, "Tombstone", "Wait,how does that work?", 1, 0);
    public static Item LavaVial = new Item(2, 0, "Lava Vial", "A vial of molten lava.", 0, 0);
    public static Item swampPot = new Item(2, 2, "Swamp Potion", "A mystery potion created by a witch in the swamp.",
            0, 0);
    public static Item toxicFang = new Item(1, 0, "Toxic Fang", "A strange toxic fang of some creature.", 0, 0);
    public static Item doransBlade = new Item(3, 2, "Doran's Blade", "A blade forged in the heart of Doran", 0, 0);
    public static Item woodenSword = new Item(1, 0, "Wooden Sword", "I mean it kinda helps?", 0, 0);
    public static Item warriorSword = new Item(4, 0, "Warrior's Sword", "He's not really a warrior.", 0, 0);
    public static Item bountyHunterSword = new Item(2, 0, "Bounty Hunter's Sword",
            "Why was he hunting you? Don't ask me.", 0, 0);
    public static Item slimeShield = new Item(0, 7, "Slime Shield", "Is it really a shield if it's permeable?", 0, 0);
    public static Item giantSkin = new Item(0, 15, "Giant Skin", "Wait but aren't giants just large humans?", 0, 0);
    public static Item bloodStone = new Item(0, 5, "Blood Stone", "Not made of blood.", 6, 8);
    public static Item skeletonBone = new Item(5, 0, "Skeleton Bone",
            "Why does a ribcage deal more damage than a sword?", 0, 0);
    public static Item soul = new Item(1, 20, "Soul", "I-it's just a soul why does it give you health?", 0, 0);
    public static Item empty = new Item(69420, 69420, "Empty", "Nothing to see here.", 69420, 69420);
    public static Item GhostSpirit = new Item(0, 3, "Ghost Spirit in a Bottle", "Really a skill issue if you get stuck in a bottle imo", 3, 0);
    public static Item toxicWaste = new Item(8, 2, "Toxic Waste", "Where did it come from?", 0, 0);
    public static Item starDust = new Item(10, 0, "Star Dust", "Similar to toxic waste", 0, 0);
    public static Item barFromHell = new Item(7, 0, "Bar From Hell", "Did you take that from the gates of hell?");
    public static Item Petal = new Item(0, 6, "Petal", "I mean its a petal, it can't be that good.", 1, 0);
    public static Item CherryBlossom = new Item(0, 8, "Cherry Blossom", "Hey... Isn't that from a location?", 2, 0);
    public static Item CherryBark = new Item(0, 12, "Cherry Bark", "How and where did you get this?", 0, 1);
    public static Item Clover = new Item(5, 0, "Clover", "How am I going to implement this theres no luck in this game.", 0, 0);
    public static Item Charm = new Item(20, 12, "Charm", "This is a bit... overpowered?", 1, 0);
    public static Item Grass = new Item(1, 1, "Grass", "Sometimes grass is sharp.");
    public static Item Rock = new Item(1, 0, "Rock", "Found on mountains.");
    public static Item StoneShield = new Item(1, 8, "Stone Shield", "Its a shield... made of stone. I don't think that would be easy to hold.");
    public static Item StoneSword = new Item(4, 0, "Stone Sword", "Good luck swinging with this.");
    public static Item HealingShard = new Item(0, 0, "Healing Shard", "Said to only be dropped by the Angel. 1 of 7 Shards.", 7, 0, 2, 10);
    public static Item GlitchedShard = new Item(2, 2, "glitched Shard", "Said to only be dropped by the Glitch. 1 of 7 Shards.", 2, 2, 2, 10);
    public static Item DeathShard = new Item(7, 0, "Death Shard", "Said to only be dropped by Death. 1 of 7 Shards.", 0, 0, 2, 10);
    public static Item ShatteredShard = new Item(7, 0, "Shattered Shard", "Said to only be dropped by the Mega Lava Slime. 1 of 7 Shards.", 0, 0, 2, 10);
    public static Item SpriteShard = new Item(0, 0, "Sprite Shard", "Said to only be dropped by the Sprite Lord. 1 of 7 Shards.", 0, 7, 2, 10);
    public static Item HellShard = new Item(7, 0, "Hell Shard", "Said to only be dropped by the Demon Lord. 1 of 7 Shards.", 0, 0, 2, 10);
    public static Item OmegaShard = new Item(10, 20, "Omega Shard", "The most rare shard, and yet, the least rare shard. 1 of 7 Shards.", 9, 6, 100, 10);
    public static Item KeeperCrystal = new Item(0, 1000, "Keeper Crystal", "Powerful. But if you live you won't be needing it.", 0, 0, Integer.MIN_VALUE / 1000, Integer.MIN_VALUE / 100000);
    public static Item LockedItem = new Item(0, 0, "Locked Item", "Sorry, you can't buy this yet.", 0, 0);
    public static Item SubspaceOrb = new Item(1, 1, "Subspace Orb", "Kinda small don't you think? Wait, you can't see it yet.", 1, 0, true);
    public static Item glitch = new Item(-2, 4, "Glitch", "DO NOT USE.", -5, 99, true);
    public static Item unstableSword = new Item((int) (Math.random() * 15) - 5, 0, "Unstable Sword", "Maybe reload your game?", 0, 0, true);
    public static Item unstableRing = new Item(0, (int) (Math.random() * 20) - 7, "Unstable Ring", "Maybe reload your game?", -5, 0, true);
    public ItemData() throws Exception {
        throw new Exception("Do not create objects of data classes");
    }


}
