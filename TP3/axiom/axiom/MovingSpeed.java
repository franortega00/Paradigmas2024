package axiom;

public class MovingSpeed extends Speed {
    public MovingSpeed(Speed aSpeed) {
        previousSpeed = aSpeed;
        this.speed = aSpeed.speed + 1; }
    public int value() { return speed; }
    public Speed increaseSpeed() { return new MovingSpeed(this); }
    public Speed decreaseSpeed() {
        speed = previousSpeed.speed;
        return previousSpeed;
    }
    public void speedSecurityTest() {}
    public Speed decreaseSpeedAsDeployed(Sonda.SondaDeployed sondaDeployed) {
        previousSpeed.speedSecurityTest();
        return decreaseSpeed();
    }
}
