package KeeperLand.Enemies.Bosses;

import KeeperLand.Abstracts.Boss;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.*;
import KeeperLand.Interacts.Battle;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Bug extends Boss {
    protected static boolean End;

    public void setBaseStats() {
        this.baseHp = 75;
        this.damage = 5;
        this.xp = 120;
        this.name = "Bug";
        this.coins = 50;
        this.tokens = 1;
        this.drops.add(ItemData.GlitchedShard);
    }

    @Override
    public boolean canSpawn(Player p) {
        return (Main.r.nextBoolean() || Main.r.nextBoolean()); //75% spawn chance
//        return false;
    }

    @Override
    public String getDodgeText() {
        return " breaks your attack.";
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        List<Enemy> spawns = Battle.getEnemies(p);
        spawns = Helper.getRandomElements(spawns, 4);

        System.out.println(Colors.RED + "<Error>: multiple abnormal entites detected. Attempting to fix error..." + Colors.RESET);
        for (Enemy e : spawns) {
            int rand = Main.r.nextInt(3);
            if (rand == 0) {
                System.out.println("Entity " + e.getName() + "<" + e + "> detected with abnormal stats. Attempting to fix...");
                e.setBattleHp(e.getBattleHp() >> 1);
                e.setDamage(e.getDamage() << 1);
                Helper.Sleep(1);
                System.out.println("Entity " + e.getName() + "<" + e + "> unable to fix, health halved (" + e.getBattleHp() * 2 + "->" + e.getBattleHp() + "), damage doubled (" + e.getDamage() / 2 + "->" + e.getDamage() + ").");
            } else if (rand == 1) {
                System.out.println("Entity " + e.getName() + "<" + e + "> detected with abnormal stats. Attempting to fix...");
                e.setBattleHp(e.getBattleHp() << 1);
                e.setDamage(e.getDamage() >> 1);
                Helper.Sleep(1);
                System.out.println("Entity " + e.getName() + "<" + e + "> unable to fix, health doubled (" + e.getBattleHp() / 2 + "->" + e.getBattleHp() + "), damage halved (" + e.getDamage() * 2 + "->" + e.getDamage() + ").");
            } else if (rand == 2) {
                System.out.println("Entity " + e.getName() + "<" + e + "> detected with decreased stats. Attempting to fix...");
                e.setBattleHp(e.getBattleHp() << 1);
                e.setDamage(e.getDamage() << 1);
                Helper.Sleep(1);
                System.out.println("Entity " + e.getName() + "<" + e + "> fixed, health doubled (" + e.getBattleHp() / 2 + "->" + e.getBattleHp() + "), damage doubled (" + e.getDamage() / 2 + "->" + e.getDamage() + ").");
            }
        }
        allies.addAll(spawns);
        Helper.Sleep(1);
    }


    @Override
    public void bossOnSpawn(List<Enemy> allies) {
        System.out.println(
                " /$$$$$$$            /$$             /$$              \n" +
                        "| $$__  $$          | $$            | $$              \n" +
                        "| $$  \\ $$  /$$$$$$ | $$  /$$$$$$  /$$$$$$    /$$$$$$ \n" +
                        "| $$  | $$ /$$__  $$| $$ /$$__  $$|_  $$_/   /$$__  $$\n" +
                        "| $$  | $$| $$$$$$$$| $$| $$$$$$$$  | $$    | $$$$$$$$\n" +
                        "| $$  | $$| $$_____/| $$| $$_____/  | $$ /$$| $$_____/\n" +
                        "| $$$$$$$/|  $$$$$$$| $$|  $$$$$$$  |  $$$$/|  $$$$$$$\n" +
                        "|_______/  \\_______/|__/ \\_______/   \\___/   \\_______/");
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR +
                " /$$$$$$$$/$$                \n" +
                "|__  $$__/ $$                \n" +
                "   | $$  | $$$$$$$   /$$$$$$ \n" +
                "   | $$  | $$__  $$ /$$__  $$\n" +
                "   | $$  | $$  \\ $$| $$$$$$$$\n" +
                "   | $$  | $$  | $$| $$_____/\n" +
                "   | $$  | $$  | $$|  $$$$$$$\n" +
                "   |__/  |__/  |__/ \\_______/");
        Helper.Sleep(1);
        System.out.println("\n" + Colors.CLEAR +
                " /$$$$$$$                     \n" +
                "| $$__  $$                    \n" +
                "| $$  \\ $$ /$$   /$$  /$$$$$$ \n" +
                "| $$$$$$$ | $$  | $$ /$$__  $$\n" +
                "| $$__  $$| $$  | $$| $$  \\ $$\n" +
                "| $$  \\ $$| $$  | $$| $$  | $$\n" +
                "| $$$$$$$/|  $$$$$$/|  $$$$$$$\n" +
                "|_______/  \\______/  \\____  $$\n" +
                "                     /$$  \\ $$\n" +
                "                    |  $$$$$$/\n" +
                "                     \\______/ ");
        Helper.Sleep(1.5);
        System.out.println(Colors.CLEAR);
    }

    public int BossAttack(Player p, List<Enemy> allies) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            /* (non-Javadoc)
             * @see java.util.concurrent.Callable#call()
             */
            public Integer call() {
                int MissedLeft = 3;
                String GoalShape = Helper.getRandomElements(Arrays.asList("O", "X", "■"), 1).get(0);
                String GoalColor = Helper.RandomColor();
                System.out.println("Hit enter when this shape appears:" + GoalColor + GoalShape + Colors.RESET);
                String shape = "n";
                String Color = "n";
                while (!Bug.End && MissedLeft > 0) {
                    shape = Helper.getRandomElements(Arrays.asList("O", "X", "■"), 1).get(0);
                    Color = Helper.RandomColor();
                    System.out.print("\r:" + Color + shape + Colors.RESET);
                    if (shape.equals(GoalShape) && Color.equals(GoalColor)) {
                        MissedLeft--;
                    }
                    Helper.Sleep(0.5);
                }
                System.out.println(Colors.RESET);
                if (MissedLeft == 0) {
                    System.out.println("You failed to dodge");
                    return 0;
                }
                int result = shape.equals(GoalShape) ? 1 : 0;
                result += (Color.equals(GoalColor)) ? 1 : 0;
                return result;
            }
        });
        try {
            Main.s.nextLine();
            Bug.End = true;
            int res = future.get();
            Bug.End = false;
            if (res == 0) {
                System.out.println("You failed to dodge");
                return this.damage;
            }
            if (res == 2) {
                System.out.println("You dodged the attack");
                return 0;
            }
            if (res == 1) {
                System.out.println("You almost dodged the attack");
                return this.damage / 2;
            }
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    //override the attack command in enemy
    public int Attack(Player p, List<Enemy> allies) {
        System.out.println("Bug deals <error> damage");
        return Main.r.nextInt(damage - (int) (damage * 0.2), damage + (int) (damage * 0.2));
    }

    @Override
    public String displayBattleHp() {
        return "<error>";
    }
}
