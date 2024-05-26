package axiom;

public abstract class Speed {
    int speed = 0;
    Speed previousSpeed;

    public abstract int value();
    public abstract Speed increaseSpeed();
    public abstract Speed decreaseSpeed();
    public abstract Speed decreaseSpeedAsDeployed(Sonda.SondaDeployed sondaDeployed);
    public abstract void speedSecurityTest();

}

