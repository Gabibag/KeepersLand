<!-- TOC -->

* [Installation](#installation)
* [How to Play](#how-to-play)
* [Full Game Wiki](#full-game-wiki)
    * [Battles](#battles)
    * [Shops](#shops)
    * [Locations](#locations)
    * [Enemy types](#enemy-types)
    * [Mutations](#mutations)
    * [Status Effects](#status-effects)
    * [Item Types/Tiers](#item-typestiers)
    * [Final Boss - The Keeper](#final-boss---the-keeper)

<!-- TOC -->

# Installation

1. Download the .jar file in releases.
2. Open your terminal
    1. Mac - Open spotlight search(command + space) and type in "terminal"
    2. Windows - Open the start menu and type in "cmd"
3. Type in `java -jar`
4. Drag in the .jar file onto your terminal
5. Hit enter \
   \
   Note: Hitting control c will force close the game without saving.

# How to Play

You play the game by entering a battle. In each battle there will be 3 enemies and a randomized location. You always
start first and have 2 choices, and each choice will cost an action. You have 2 actions. You can heal your self, or
damage an enemy. You can also inspect enemies to see their stats, and it does not take an action.

After your actions are all done, all the enemies attack once. After that, if you are still alive, the current location
you are in may run an effect, which could range drom healing 20% of your hp or dropping your health by 10%. These
effects depend on your current location. They can lower your health, make you deal less damage, lose a turn, swap
enemies, and do a range of stuff. To view a list of locations and their modifications, click [here](#locations).

If you win a stage by killing all enemies and exeting the battle, you will move on to the next stage. If you lose, you
move down a stage. Evey 5 stages, you will encounter a boss. Beating the boss makes sure you can't drop below the boss
stage. Each boss will drop a shard specific to the boss type. If you collect all 7 shards, you will get access to
the [Final Boss](#final-boss---the-keeper).

The shop contains items you can buy to increase your stats. The shop shows location specific items. The token shop,
however, is a seperate shop that is unlocked after finding a shard. You can use tokens, which is a currency obtained by
killing bossess, to buy shards.

If you need more or specific information, check out the full game wiki.

# Full Game Wiki

### Battles

- The player can choose to battle when in "home screen".
- Each turn there will be 3 enemies fighting you, these enemies are in a "party"
- The player has 2 actions, with the available actions being:
    - Attack
    - Heal
- There is a small chance for the enemies to dodge your attack, meaning they take 0 damage (5% chance).
    - The player cannot dodge attacks.
- If an enemy dies, it has a chance to drop an item, which will automatically be added to the player's inventory.
    - Items can be of a random Tier from 1 to 6, with the drop chances being exponentially higher. Stats and cost are
      also increased
- An enemy will always drop coins. The amount of coins scale depending on the stage you are in.
- A boss will appear every 5 rounds and drop tokens when killed(similar to coins but only dropped by bosses).
    - Bosses have a special attack mechanism, in which the player plays a mini-game, and the outcome of it determines
      the damage dealt to the player.
    - Each boss has a chance to drop a shard, which can be used to unlock the final battle.
        - Each boss drops a specific shard
- Once the player completes a battle, they will be taken to the home screen, and a new location + stage

### Shops

- There are 2 different shops, 1 is the regular shop, which allows you to purchase things with coins (◊).
- The other is a token shop, which allows you to purchase shards using tokens (₪). If you have all the shards, you
  unlock the final battle
    - The shop is only accessible after finding a shard.

### Locations

- Locations are randomly determined, and each location has a different modifier.
- Locations:
    - Abandoned City
        - Chance to mutate enemies (split them with half health)
    - Cherry Blossoms
        - Every turn, everything heals 5% of it's hp
    - Gates to Hell
        - Every action the player takes some damage
    - Graveyard
        - Small chance to spawn a severed hand.
    - Keeper's Land
        - Boss Location. Made so other location effects don't mess up your gameplay
    - Lava Zone
        - Every 4 turns everything loses half of the player's current hp.
    - Luck Land
        - Multitude of random mechanics. Some of which are:
        - On attack(player and enemy):
            - Double damage
            - Half damage
        - After player action:
            - Run heal command without using an action
            - Lose 5% of player's hp
            - Increase player's damage by 10%
            - Decrease player's damage by 10%
        - After turn end:
            - Spawn a sprite
            - Swap an enemy with another
            - Double everything's health
            - Heal everything for 20hp
    - Null Zone
        - No special effects
    - Stardew Valley
        - Everything has a chance to attack itself
    - Starter Land
        - You can't die. It only appears once ever.
    - Toxic Swamp
        - Chance of increase in damage
    - Waste Land
        - Always deal less damage and a chance to take damage
    - Windy Heights
        - Every 4 turns, the enemies' dodge rate increases
    -

### Enemy types

- There are three types of enemies (though not explicitly stated in game), a special enemy, a basic enemy, and sprites.
- Basic enemies simply deal damage
    - Archer
    - Giant
    - Goblin
    - Ghost
    - Warrior
    - Bounty Hunter
    - Skeleton
    - Skeleton Hand
    - Zombie
    - Death's Minion
    - Demon
    - Ogre
    - Overlord
        - Just close the game if you see him.
    - Star Creature
    - Basilisk
    - Ninja
    - Vulture
    - Slime
    - Mini Radioactive Slime
    - Mini Lava Slime
    - Cave Dweller
- Special enemies have an extra mechanic, such as a charge up attack
    - Burning Mutant - Deals damage to itself and the player
    - Mutated Zombie - Damage increases each turn
    - Overloaded Zombie - Charges up and deals damage
    - Radioactive Slime - Creates a Mini Radioactive Slime
    - Electric Mutant - Deals damage to its party and the player
    - Item Entity - Manifests as an Item.
    - Fireball - Deals damage after flying towards you. Then dies.
    - Gargoyle - Chance to miss/deal extra damage
    - Hell Fire Imp - Summons fireballs
    - Lava Slime - Splits into Miniature versions of itself on death
    - Cloud - Chance to deal less damage.
    - Gate Keeper - Boosts the health of enemies by taking your health
    - Fairy - Has a chance to:
        - Heal itself
        - Heal its party members
        - deal extra damage
        - deal less damage
    - Assassin - High dodge rate
    - Snake - deals a % damage instead of a constant damage
    - Super Charged Bird - Charges up attack
- Sprites are useless on their own, but paired with anything else, they will make it difficult to beat the level
    - Absorb Sprite - Copy 2% of all enemies health to itself.
    - Buff Sprite - Increase it's party's damage by 10%
    - Death Sprite - Deal 10% of max player health
    - Debuff Sprite - Removes an action from the player
    - Divisor Sprite - Deals 50% of the player's current health
        - Will almost never be able to kill you on its own
    - Double Sprite - Make everything attack again
    - Healing Sprite - Heals everything in its party
    - Helper Sprite - deals some damage to thing in its party
        - includes a hidden mechanic
    - Swap Sprite - swaps the health of everything in its party
    - Tank Sprite - makes everything that has lower health than it get raised to its health
    - Wind Sprite - increases everything's dodge rate.
- Bosses
    - 6 Bosses, each with different mechanics. Usually easy to beat and comes with a spawn "animation"
        - Angel
            - Spawns 4 healing sprites on spawn
            - Spawns 3 healing sprites and a death's minion on death
            - Drops Healing Shard
            - Chance to heal itself
            - Chance to spawn a healing sprite
        - glitch
            - Shape defense mechanism
            - Drops glitched Shard
            - Spawns "glitched" entities
        - Death
            - Spawns a random number of its minions on death
            - Bar defense mechanism
            - Drops Death Shard
        - Demon Lord
            - Numbers defense mechanism
            - Drops Hell Shard
        - Mega Lava slime
            - Splits into 3 Lava Slimes on death
            - Drops Shattered Shard
        - Sprite Lord
            - Spawns 4 sprites on spawn
            - Spawns 5 sprites on death
            - Drops Sprite Shard

### Mutations

`Implemented in v0.3`

- Mutations are modifications of some entities that spawn in the game. They add extra effects and generally make the
  game harder, although they do sometimes help you, rather than hurt you.
- Mutation Types
    - Defensive
        - On Attack
            - 20% chance to take no damage
            - Only takes 80% of damage
    - Explosive
        - On Death
            - Deals damage to all entities around it
        - On Attack
            - 5% chance to die instantly
            - 20% chance to fragment and deal 40% of its damage to all entities
    - Golden
        - On Hurt
            - Drops half of its original coin amount
        - On Death
            - Drops double its count amount
    - Thorny
        - On Hurt
            - Reflects 20% of damage back to player
        - On death
            - Deals 20% of its damage to all entities
    - Toxic
        - On Hurt
            - 1 in 4 chance to poison player
    - Undead
        - On Player Heal
            - Takes double the damage you healed
        - On Hurt
            - Heal other entities for half the damage taken
        - On Player Kill Entity
            - Revive that entity with 40% health
        - On Death
            - Kill all revived entities

### Status Effects

`Implemented in v0.3`

- These effects are still a work in progress, and currently is only used by the toxic mutation. Hopefully I will add
  different types that can be used for potions or something.
- Effects have a "tick effect" which activates at the end of a turn, after an attack, after healed, then turn ends.
- Effects can "tick" when a tick effect is run. Ticks drop the duration of the status effect, and when the duration is 0
  it vanishes. However, not all tick effects tick
- Effects
    - Poison
        - Player Attack - removes 10% of player's current health, ticks.
        - Player Heal - reduce healing amount from 10% to 30%.

### Item Types/Tiers

`Implemented in v0.3`

- Tiers (Also called Types) are randomly generated when an item is bought or dropped. Types increase the stats of the
  item exponentially to
  add variance to the game.
- Types
    - Type I 50% chance, default tier.
    - Type II 25% chance
    - Type III 25% chance
    - Type IV 7% chance
    - Type V 2.5% chance
    - Type Z 0.5% chance, highest tier
    - Complex Type
        - Complex Types are different from the other types as they don't drop, and only appear when you have a thousand
          or more of an item. It compresses those items into a single Complex Item to keep from excessive ram usage.
          Most people are never going to see this appear because no one plays this game. This is just useful when using
          the `rtest` cheat to jump to high levels.

### Final Boss - The Keeper

- Entering the Final Boss is optional, but is the main purpose of the game
- The player must have all 6 shards in their inventory to enter. (Shards can be purchased in the Token Shop)
- The Keeper has 3 stages
    - Stage 1
        - On Spawn:
            - Takes all the player's item and uses it against them
        - On Attack:
            - Chance to shatter a shard
            - Chance to use the shards in the player's inventory to deal extra damage
            - Chance to use the shards in the player's inventory against them
        - On Death:
            - Spawns stage 2 boss
    - Stage 2
        - On Spawn:
            - Takes all the player's items and reanimates them.
            - Returns some of the player's items.
        - On Attack Keeper:
            - All other enemies in the Keeper's party must be killed before damage can be done to the keeper
        - On Death:
            - Drops Mystical Crystal
                - adds 1000hp to the player
        - Does not deal damage
    - Stage 3:
        - On Spawn:
            - Poem is given on how the player will die(small chance to be a different dialog)
        - On Attack Keeper:
            - All other enemies in the Keeper's party must be killed before damage can be done to the keeper
        - On Turn End:
            - Summon enemies to fill its party to 3.
        - On Death:
            - Death poem
            - Player gets insane amounts of coins.

<hr/>
