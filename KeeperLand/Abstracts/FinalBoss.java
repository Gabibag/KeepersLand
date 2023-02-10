package KeeperLand.Abstracts;

import java.util.List;

public abstract class FinalBoss extends Boss{
    public int getBossStage() {
        return bossStage;
    }

    public void setBossStage(int bossStage) {
        this.bossStage = bossStage;
    }

    protected int bossStage = 1;

    @Override
    public String getType() {
        return "FinalBoss";
    }

    public abstract void finalBossOnSpawn(List<Enemy> enemies);


    @Override
    public void setBaseStats() {
    }
}
