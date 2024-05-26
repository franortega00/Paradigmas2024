package axiom;

public class StoppedSpeed extends Speed {
    public static final String tooSlow = "Too Slow";
    public int value() { return 0; }
    public Speed increaseSpeed() { return new MovingSpeed(this); }
    public Speed decreaseSpeed() { return this; }
    public Speed decreaseSpeedAsDeployed( Sonda.SondaDeployed sondaDeployed ) { return this; }
    public void speedSecurityTest() { throw new RuntimeException(tooSlow); }
}
