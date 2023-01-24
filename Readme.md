# About This Game

<!-- TOC -->
* [About This Game](#about-this-game)
    * [Gameplay](#gameplay)
    * [Locations](#locations)
    * [Mob types](#mob-types)
<!-- TOC -->
### Gameplay
- Shops
  - There are 2 different shops, 1 is the regular shop, which allows you to purchase things with coins.
  - The other is a special shop, unlocked after buying the shop key[^1]
    - This shop allows purchasing legendary items, (0.01% drop rate) with tokens (coins that are dropped by bosses).
- Battles
  - The player can choose to battle when in "home screen", and they must survive all the battles until they reach the boss or escape. 
    - Players can escape, but have a chance to lose what they earned during battle(all their other items will be safe).
  - Their health will gain 20%[^1] of what they currently have(does not exceed max hp) and the game continues. 
  - 
  - Each turn there will be 3 enemies fighting you, and you can deal two attacks per turn[^2], choosing to attack two enemies, or attacking one enemy.
  - every group will have at least 1 special enemy type, but this can be higher due to randomized effects.
  - A boss will appear every 10 (maybe 5) rounds and drop tokens(similar to coins but only dropped by bosses).
  - 
### Locations 
(this one is more note-like) \
We should also have locations, and make them so that each location gives a different modifier

But we want it to scale infinitely , so we can have the first 5 or 6 be hardcoded, 
such as "poisoned realm," which, say drops your health by a certain amount.
After the first few hardcoded elements, we can have each location be modified slightly using modifiers.
such as
- slightly
- increased
- decreased
- extra

Things such as this.
then we can have a main area element
- poison
- heal
- revive
- scale

And make then finally an element that affects only mobs
- immune
- resistant
- stronger
- powerless

The first one determines the scale of the effect the location gives,
the second one gives the effect to the player, the last one gives an effect to enemies.
So, "slightly revived immune" would mean when the player dies, they revive
with a small amount of health, while the enemies would be immune to status effects.
Obviously the location can't say "slightly revived immune," so we can change it so specific
enemy effectors have a specific display name (These will be nouns).\
ex: powerless -> arena\
    stronger  -> battlefield \
etc. 
### Mob types
- Special
  - These have either a passive ability or an attacking ability
  - Attacking ability
    - Something that usually applies a status effect, or deal more damage if you have lower health, or make you deal lower damage, etc.
  - Passive ability
    - This is something like heal, which will heal other creatures in it's 'party'
- Basic
  - Is either given a status effect or not
  - Effected
    - Status effects, such as immune, resistant, stronger, weaker, revival, etc.

<hr />
[^1] May change
[^2] May change due to status effects or other parameters 
