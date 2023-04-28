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
        self.setBattleHp(self.getBattleHp() - healamt);
        System.out.println("The Undead " + self.getName() + " takes " + healamt + " damage from your healing!");
    }

    @Override
    public void onHurt(List<Enemy> e, int damage, Enemy self) {
        //heal damage
        for (int i = 0; i < e.size(); i++) {
            if(!e.get(i).getName().contains("Revived") || e.get(i) == self){
                continue;
            }
            e.get(i).setBattleHp(e.get(i).getBattleHp() + damage);
        }
        self.setBattleHp(self.getBattleHp() + damage);
        System.out.println("The Undead " + self.getName() + " heals " + damage + " damage from your attack!");
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
