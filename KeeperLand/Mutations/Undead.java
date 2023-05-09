package KeeperLand.Mutations;

import KeeperLand.Abstracts.Enemy;
import KeeperLand.Abstracts.Mutations;

import java.util.List;

public class Undead extends Mutations {

    public Undead() {
        super("Undead");
    }

    @Override
    public void onHeal(List<Enemy> e, int healamt, Enemy self) {
        //take heal damage
        self.setBattleHp(self.getBattleHp() - (healamt << 1));
        System.out.println("The Undead " + self.getName() + " takes " + (healamt << 1) + " damage from your healing!");
    }

    @Override
    public void onHurt(List<Enemy> e, int damage, Enemy self) {
        int heal = damage / e.size() / 2;
        //heal damage
        for (Enemy enemy : e) {
            if (!enemy.getName().contains("Revived") || enemy == self) {
                continue;
            }
            enemy.setBattleHp(enemy.getBattleHp() + heal);
        }
        self.setBattleHp(self.getBattleHp() + heal);
        System.out.println("The Undead " + self.getName() + " heals its allies for  " + heal + " hp!");
    }

    @Override
    public void onKill(List<Enemy> e, Enemy self, Enemy killed) {

        if(killed.getName().contains("Revived ")){
            return;
        }
        //revive killed with 40% hp and rename it to add "Undead" to the name
        killed.setBattleHp((int) (killed.getBaseHp() * 0.4));
        killed.setName("Revived " + killed.getName());
        System.out.println("The Undead " + self.getName() + " revives " + killed.getName() + "!");
    }

    @Override
    public void onDeath(List<Enemy> e, Enemy self) {
        for (Enemy enemy : e) {
            if(enemy.getName().contains("Revived ")){
                enemy.setBattleHp(0);
                System.out.println("The Undead " + self.getName() + " brings down " + enemy.getName() + "!");
            }
        }
    }
}
