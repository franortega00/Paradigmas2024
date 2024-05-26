package axiom;

public class OficialSpeed {
    private Speed level = new StoppedSpeed();

    public int value() { return level.value(); }
    public void increaseSpeed(){ level = level.increaseSpeed(); }
    public void decreaseSpeed(){ level = level.decreaseSpeed(); }
    public void speedSecurityTest(){ level.speedSecurityTest(); }
    public void decreaseSpeedAsDeployed(Sonda.SondaDeployed sondaDeployed){ level = level.decreaseSpeedAsDeployed(sondaDeployed); }
}
