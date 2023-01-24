# About This Game

<!-- TOC -->
* [About This Game](#about-this-game)
    * [Shops](#shops)
    * [Battles](#battles)
    * [Locations](#locations)
    * [Enemy types](#enemy-types)
    * [Status effects](#status-effects)
    * [Location modifiers](#location-modifiers)
<!-- TOC -->
### Shops
  - There are 2 different shops, 1 is the regular shop, which allows you to purchase things with coins.
  - The other is a special shop, unlocked after buying the shop key [^1]
    - This shop allows purchasing legendary items, (0.01% drop rate) with tokens (coins that are dropped by bosses).
### Battles
  - The player can choose to battle when in "home screen", and they must survive all the battles until they reach the boss or escape. 
    - Players can escape, but have a chance to lose what they earned during battle(all their other items will be safe).
  - Their health will gain 20% [^1] of what they currently have(does not exceed max hp) and the game continues.
  - Each turn there will be 3 enemies fighting you, and you can deal two attacks per turn[^2], choosing to attack two enemies, or attacking one enemy.
    - There is a small chance to dodge attacks, mean you take 0 damage.
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
### Enemy types
- Special enemies
  - These have either a passive ability or an attacking ability
  - Attacking ability
    - Something that usually applies a status effect, or deal more damage if you have lower health, or make you deal lower damage, etc.
  - Passive ability
    - This is something like heal, which will heal other creatures in it's 'party'
    - All passive abilities:
      - heal (others)
      - deflect (throws your shot back at you)
      - reflect (does your attack back at you next turn)
      - shrapnel (throws back a percentage of your attack at you)
      - explosive (deals damage on death)
      - toxic (gives you poison on attack)
      - [more later]
- Basic enemies
  - Is either given a status effect or not
  - status
    - Status effects, such as immune, resistant, stronger, weaker, revival, etc.
  - If it's not, then they simply attack and don't have any change
- Bosses
  - Tiered in rare - super - legendary 
  - Regular bosses have an 85% chance of spawning
    - 6-9x(yes seriously) more health than regular enemies. 
    - 1.2x more damage than regular creatures
    - Drop 3-5 tokens
  - rare bosses have 10% chance of spawning 
    - have 9x-12x more health than regular enemies
    - 1.8x more damage than regular creatures
    - drop 6-9 tokens
  - super bosses have a 4.9% chance of spawning 
    - have 15x-20x more health than regular enemies
    - 2.2x more damage than regular creatures
    - spawn extra enemies at the start of the game
    - drop 12-18 tokens
  - legendary bosses have a 0.1% chance of spawning
    - have 100x-1000x more health than regular enemies
    - 2.4x more damage than regular creatures
    - attacks twice
    - spawns extra enemies
    - drops 20-40 tokens and a legendary item
    - this one isn't meant to be beatable.
### Status effects
 - resistant - takes 20% less damage
 - regeneration - heals 10% of current health (does not exceed max health)
 - blind - cannot see health bars
 - poisoned - lose 5% of max health each turn
 - multiplier - multiplies all effects by 2[^1]
 - health boost - increased health
 - damage boost - increased damage
 - aaaaaaaa - aaaaaaaaa
 - scale - increased health and damage
 - others
### Location modifiers
 - Location strength modifiers
   - slightly less (x0.7)
   - slightly (x1.1)
   - very (x4)
   - very very (x5)
   - normally
   - doubly
   - triply (idk if this is a word)
   - not (negates the effect, pretend it doesn't exist)
 - Location status effect modifiers
   - immune (no effects can be applied, not affected by strength modifiers unless it's 'not')
   - critical (when health <10% deal 2x damage and take 1/2x damage)
   - swift (dodge attacks more)
   - armour deficient (buffs by armour are halved)
   - revive-able (come back to life with health 10% health) 
   - 
<hr />
[^1] May change
[^2] May change due to status effects or other parameters 
