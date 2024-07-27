package KeeperLand.Enemies.Bosses;

import KeeperLand.Abstracts.Boss;
import KeeperLand.Abstracts.Enemy;
import KeeperLand.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Glitch extends Boss {
    protected static boolean End;

    public Glitch() {
        super("Drops a glitched Shard");

    }

    public void setBaseStats() {
        this.baseHp = 50;
        this.damage = 5;
        this.xp = 120;
        this.name = "Glitch";
        this.coins = 50;
        this.tokens = 1;
        this.drops.add(ItemData.GlitchedShard);
    }

    @Override
    public boolean canSpawn() {
        return true; //75% spawn chance
//        return false;
    }

    @Override
    public String getDodgeText() {
        return " breaks your attack.";
    }

    @Override
    public void onDeath(Player p, List<Enemy> allies, Enemy self) {
        p.setStageNum(p.getStageNum() - 1);
        List<Enemy> spawns = Helper.getEnemies(p);
        p.setStageNum(p.getStageNum() + 1);
        spawns = Helper.getRandomElements(spawns, 3);

        System.out.println(Colors.RED + "<Error>: multiple abnormal entities detected. Attempting to fix error..." + Colors.RESET);
        for (Enemy e : spawns) {
            int rand = Main.r.nextInt(3);
            Helper.Sleep(2);
            if (rand == 0) {
                System.out.println("Entity " + Colors.RED + e.getName() + "<" + e + ">" + Colors.RESET + " detected with" + Colors.RED + " abnormal stats." + Colors.RESET + " Attempting to fix...");
                e.setBattleHp(e.getBattleHp() >> 1);
                e.setDamage(e.getDamage() << 1);
                Helper.Sleep(2);
                System.out.println("Unable to fix, health " + Colors.RED + "halved (" + (e.getBattleHp() << 1) + "->" + e.getBattleHp() + ")" + Colors.RESET + ", damage " + Colors.RED + "doubled (" + e.getDamage() / 2 + "->" + e.getDamage() + ")." + Colors.RESET);
            } else if (rand == 1) {
                System.out.println("Entity " + Colors.RED + e.getName() + "<" + e + ">" + Colors.RESET + " detected with" + Colors.RED + " abnormal stats." + Colors.RESET + " Attempting to fix...");
                e.setBattleHp(e.getBattleHp() << 1);
                e.setDamage(e.getDamage() >> 1);
                Helper.Sleep(2);
                System.out.println("Unable to fix, health" + Colors.RED + " doubled (" + e.getBattleHp() / 2 + "->" + e.getBattleHp() + ")" + Colors.RESET + ", damage" + Colors.RED + " halved (" + (e.getDamage() << 1) + "->" + e.getDamage() + ")." + Colors.RESET);
            } else {
                System.out.println("Entity " + Colors.RED + e.getName() + "<" + e + ">" + Colors.RESET + " detected with" + Colors.RED + " decreased stats." + Colors.RESET + " Attempting to fix...");
                e.setBattleHp((int) (e.getBattleHp() * 1.5));
                e.setDamage((int) (e.getDamage() * 1.5));
                Helper.Sleep(2);
                System.out.println("Fixed, health" + Colors.RED + " doubled (" + ((int) (e.getBattleHp() / 1.5)) + "->" + e.getBattleHp() + ")," + Colors.RESET + " damage" + Colors.RED + " doubled (" + ((int) (e.getDamage() / 1.5)) + "->" + e.getDamage() + ")." + Colors.RESET);
            }

        }
        allies.addAll(spawns);
        System.out.println("Player " + p.getName() + " detected with" + Colors.RED + " abnormal stats." + Colors.RESET + " Attempting to fix...");
        p.setBattleHp(p.getBattleHp() + p.getHp() / 2);
        System.out.println("Fixed, health" + Colors.RED + " increased (" + p.getBattleHp() / 2 + "->" + p.getBattleHp() + ")." + Colors.RESET);
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
        System.out.println("\n" + " Colors.CLEAR + " +
                "  /$$$$$$  /$$ /$$   /$$               /$$      \n" +
                " /$$__  $$| $$|__/  | $$              | $$      \n" +
                "| $$  \\__/| $$ /$$ /$$$$$$    /$$$$$$$| $$$$$$$ \n" +
                "| $$ /$$$$| $$| $$|_  $$_/   /$$_____/| $$__  $$\n" +
                "| $$|_  $$| $$| $$  | $$    | $$      | $$  \\ $$\n" +
                "| $$  \\ $$| $$| $$  | $$ /$$| $$      | $$  | $$\n" +
                "|  $$$$$$/| $$| $$  |  $$$$/|  $$$$$$$| $$  | $$\n" +
                " \\______/ |__/|__/   \\___/   \\_______/|__/  |__/");
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
                while (!Glitch.End && MissedLeft > 0) {
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
            Glitch.End = true;
            int res = future.get();
            Glitch.End = false;
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
        System.out.println("Glitch deals <error> damage");
        return Main.r.nextInt(damage - (int) (damage * 0.2), damage + (int) (damage * 0.2));
    }

    @Override
    public String displayBattleHp() {
        return "<error>";
    }
}
