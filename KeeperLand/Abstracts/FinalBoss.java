package KeeperLand.Abstracts;

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



    @Override
    public void setBaseStats() {
    }
}
