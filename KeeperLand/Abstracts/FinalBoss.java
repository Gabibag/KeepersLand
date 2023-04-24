package KeeperLand.Abstracts;

public abstract class FinalBoss extends Boss {
    protected int bossStage = 1;

    public int getBossStage() {
        return bossStage;
    }

    public void setBossStage(int bossStage) {
        this.bossStage = bossStage;
    }

    @Override
    public String getType() {
        return "FinalBoss";
    }


    @Override
    public void setBaseStats() {
    }
}
