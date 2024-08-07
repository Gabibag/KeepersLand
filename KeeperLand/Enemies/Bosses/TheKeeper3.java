package KeeperLand.Enemies.Bosses;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.FinalBoss;
import KeeperLand.*;
import KeeperLand.Enemies.Common.ItemEntity;
import KeeperLand.Mutations.None;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TheKeeper3 extends FinalBoss {//stage 2 of finalBoss

    public void setBaseStats() {
        this.baseHp = 500;
        this.damage = 0;
        this.xp = Integer.MAX_VALUE / 100; //yeah you shouldn't level up with this
        this.name = "The Keeper (Final Stage)";
        this.coins = Integer.MAX_VALUE / 2;
        this.tokens = 1;
        this.bossStage = 2;
    }

    private static void displayBossSpawnMessage() { //all chatgpt
        //todo damn rework ts i was such a freshman when i made this
        /*int essay = Main.r.nextInt(10);
        if (essay == 2) {
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " So, you've finally made it to my final stage. How brave of you. ");
            Helper.Sleep(2.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " But let me tell you, your bravery will be the death of you. I have been waiting for this moment, the moment when I can show you the true meaning of power.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " I am the keeper of all things, the master of this world. I have been collecting your items, your memories, your very soul. ");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " And now, I have brought them all back to life, to face you one last time. But this time, I won't be so easy to defeat.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " I will summon the greatest enemies you've ever faced. The ones you thought you had defeated, the ones that haunt your dreams.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " They will come back, stronger, faster, and angrier. They will come back with a vengeance, and you will fall.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " But I am a fair Keeper, I will give you back the items I have taken from you.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " I want to see what you can do with them, how much of a challenge you can give me. I want to see if you are truly the one who can defeat me.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " This fight will be unlike any you have ever faced.");
            Helper.Sleep(2.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " You will be surrounded by danger, by fear, by death. You will be tested, pushed to your limits. And in the end, you will fall.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " So come forth, player, let us see what you are made of.");
            Helper.Sleep(2.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " Let us see if you have what it takes to defeat me.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " Let the final boss fight begin.");
            Helper.Sleep(2.5);
            Helper.continuePrompt();

        }//do it again, but with a different paragraph
        else if (essay == 3) {
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " So, you've finally made it to my final stage. Impressive, I must admit.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " But don't get too cocky, mortal. You're in over your head now.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " You may have defeated my first form and my reanimated items,");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " but now, I will summon the fiercest creatures from every corner of this world.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " As a show of respect, I shall give back all the items I had taken from you.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " But make no mistake, this fight will be a true test of your abilities.");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " So, are you ready? Can you handle the power of the Keeper?");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " Or will you be just another failed hero, lost to the depths of time?");
            Helper.Sleep(3.5);
            System.out.println(Colors.RED + "The Keeper:" + Colors.RESET + " Let the final boss fight begin.");
            Helper.Sleep(3.5);
            Helper.continuePrompt();
        }// do it again but as a poem
        else { //rewrite this code to add "Colors.RED + "The Keeper:" + Colors.RESET  +" infront of each printstatement
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "Hello player. I see you've made it to my final stage.");
            Helper.Sleep(2.5);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "But you cannot defeat me.");
            Helper.Sleep(2.5);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "I am the Keeper, the master of all");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "Do you think you've won?");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "Think again, foolish one");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "I've stolen your items,");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "Taken what you've worked to obtain");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "But now, I give them back");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "For this final showdown");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "You've defeated my minions,");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "But can you handle me?");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "I summon creatures,");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "From every corner and every sea");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "Do not underestimate");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "The power I possess");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "I am the Keeper,");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "The ruler of this land");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "You may have come this far,");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "But this is where you stand");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "Your journey ends here,");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "Your defeat is near");
            Helper.Sleep(2);
            System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "For I am the Keeper,");
            Helper.Sleep(2);
            System.out.print(Colors.RED + "The Keeper: " + Colors.RESET + "And you, ");
            Helper.Sleep(2);
            System.out.print("my dear,");
            Helper.Sleep(2);
            System.out.println(" are just a mere player");
            Helper.Sleep(1);
            Helper.continuePrompt();
        }*/
    }

    @Override
    public boolean canSpawn() {
        return false;
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        p.addMoney(allies.get(0).getCoins());
        System.out.println(
                "You gained " + allies.get(0).getCoins() + Colors.CYAN + "◊" +
                        Colors.RESET);
        allies.remove(0);
        //todo rework the ending tf
        /*System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "Oh. So I have been defeated.");
        Helper.Sleep(1);
        System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "I am sure you think you have won.");
        Helper.Sleep(1);
        System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "But I have merely been killed. But you? You have become the thing you have sworn to kill.");
        Helper.Sleep(1);
        System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "I must pass on my power to some creature. And you are the only one who seems fit.");
        Helper.Sleep(1);
        System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "Keep the world safe. Let the creatures live.");
        Helper.Sleep(1);
        System.out.println(Colors.RED + "The Keeper: " + Colors.RESET + "I will make sure you " + Colors.RED + "will not disappoint" + Colors.RESET + ".");
        Helper.Sleep(1);
        Helper.continuePrompt();
        System.out.println(Colors.CLEAR + "To be continued in...");
        Helper.Sleep(1);
        System.out.println("\n" +
                " ________ __                       __    __                                           __                           __                                \n" +
                "|        |  \\                     |  \\  /  \\                                         |  \\                         |  \\                               \n" +
                " \\$$$$$$$| $$____   ______        | $$ /  $$______   ______   ______   ______   _____| $$_______         _______ _| $$_    ______   ______  __    __ \n" +
                "   | $$  | $$    \\ /      \\       | $$/  $$/      \\ /      \\ /      \\ /      \\ /      \\$/       \\       /       |   $$ \\  /      \\ /      \\|  \\  |  \\\n" +
                "   | $$  | $$$$$$$|  $$$$$$\\      | $$  $$|  $$$$$$|  $$$$$$|  $$$$$$|  $$$$$$|  $$$$$$|  $$$$$$$      |  $$$$$$$\\$$$$$$ |  $$$$$$|  $$$$$$| $$  | $$\n" +
                "   | $$  | $$  | $| $$    $$      | $$$$$\\| $$    $| $$    $| $$  | $| $$    $| $$   \\$$\\$$    \\        \\$$    \\  | $$ __| $$  | $| $$   \\$| $$  | $$\n" +
                "   | $$  | $$  | $| $$$$$$$$      | $$ \\$$| $$$$$$$| $$$$$$$| $$__/ $| $$$$$$$| $$      _\\$$$$$$\\       _\\$$$$$$\\ | $$|  | $$__/ $| $$     | $$__/ $$\n" +
                "   | $$  | $$  | $$\\$$     \\      | $$  \\$$\\$$     \\\\$$     | $$    $$\\$$     | $$     |       $$      |       $$  \\$$  $$\\$$    $| $$      \\$$    $$\n" +
                "    \\$$   \\$$   \\$$ \\$$$$$$$       \\$$   \\$$\\$$$$$$$ \\$$$$$$| $$$$$$$  \\$$$$$$$\\$$      \\$$$$$$$        \\$$$$$$$    \\$$$$  \\$$$$$$ \\$$      _\\$$$$$$$\n" +
                "                                                            | $$                                                                           |  \\__| $$\n" +
                "                                                            | $$                                                                            \\$$    $$\n" +
                "                                                             \\$$                                                                             \\$$$$$$ ");
        Helper.Sleep(1);
        Helper.continuePrompt();*/

        System.out.println(Colors.CLEAR + "Thank you for playing!");
        Helper.continuePrompt();
        System.out.println(Colors.CLEAR);
    }

    @Override
    public String getDodgeText() {
        return " ignores your attack.";
    }

    @Override
    public int Attack(Player p, List<Enemy> allies) {
        //make the keeper add 2 more enemies to the list of allies if allies has 0 enemies other than itself
        if (allies.size() <= 1) {
            List<Enemy> temp = new ArrayList<>(Main.allEnemies);
            try {
                for (int i = 0; i < 2; i++) {
                    Enemy e = temp.get((Main.r.nextInt(temp.size() - 1))).getClass().getConstructor().newInstance();
                    while (e instanceof FinalBoss || e instanceof ItemEntity || e.isBoss()) {
                        e = temp.get((Main.r.nextInt(temp.size() - 1))).getClass().getConstructor().newInstance();
                    }
                    if (e.getLevel() > 20) {
                        e.setLevel(e.getLevel() - 20);
                    }
                    e.scaleStats();
                    allies.add(e);
                }

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            System.out.println("The Keeper " + Colors.RED + "summons" + Colors.RESET + " " + checkIfaN(allies.get(1).getName()) + " " + allies.get(1).getName() + " and " + checkIfaN(allies.get(2).getName()) + " " + allies.get(2).getName() + " to fight for him!");
        }
        return 0;
    }

    public String checkIfaN(String str) {
        if (str.startsWith("a") || str.startsWith("e") || str.startsWith("i") || str.startsWith("o") || str.startsWith("u")) {
            return "an";
        }
        return "a";
    }

    @Override
    public void bossOnSpawn(List<Enemy> enemies) {
//        enemies.add(this);
        this.mutate = new None();
        //for drops in TheKeeper, create a new enemy with the same stats as the drop
        //then add it to the list of enemies
        List<Item> tempItems = new ArrayList<>();
        displayBossSpawnMessage();
        System.out.println(Colors.CLEAR);
        List<Enemy> temp = new ArrayList<>(Main.allEnemies);
        /*for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getName().equalsIgnoreCase("Invalid") || temp.get(i).getName().equalsIgnoreCase("Severed Skeleton Hand") || temp.get(i).getName().equals("The Keeper")) {
                temp.remove(i);
                i--;
            }
        }*/
        //remove all ItemEntity s in the list of enemies
        for (int i = temp.size() - 1; i >= 0; i--) {
            if (temp.get(i) instanceof ItemEntity) {
                temp.remove(i);
                i--;
            }
        }

        try {
            for (int i = 0; i < 2; i++) {
                Enemy e = temp.get((Main.r.nextInt(temp.size() - 1))).getClass().getConstructor().newInstance();
                while (e instanceof FinalBoss || e instanceof ItemEntity || e.isBoss()) {
                    e = temp.get((Main.r.nextInt(temp.size() - 1))).getClass().getConstructor().newInstance();
                }
                if (e.getLevel() > 20) {
                    e.setLevel(e.getLevel() - 20);
                }
                e.scaleStats();
                enemies.add(e);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
