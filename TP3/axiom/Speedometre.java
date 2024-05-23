package axiom;

public abstract class Speedometre {
    protected int speed;
    protected Speedometre previousSpeed;
    protected abstract int value();
    public abstract Speedometre IncreaseSpeed();
    public abstract Speedometre DecreaseSpeed();
    public abstract void speedSecurityTest();
    public abstract Speedometre decreaseSpeedAsDeployed(Sonda.SondaDeployed sondaDeployed);

    public static class StoppedSpeed extends Speedometre {

        private static final String tooSlow = "Too Slow";
        public int value() { return 0; }
        public Speedometre IncreaseSpeed() { return new MovingSpeed(this); }
        public Speedometre DecreaseSpeed() { return this; }
        public void speedSecurityTest() { throw new RuntimeException(tooSlow); }
        public Speedometre decreaseSpeedAsDeployed(Sonda.SondaDeployed sondaDeployed ) { return this; }
    }

    public static class MovingSpeed extends Speedometre {
        public MovingSpeed(Speedometre speed) {
            previousSpeed = speed;
            this.speed = speed.speed + 1; }
        public int value() { return speed; }
        public Speedometre IncreaseSpeed() { return new MovingSpeed(this); }

        public Speedometre DecreaseSpeed() {
            speed = previousSpeed.speed;
            return previousSpeed;
        }
        public void speedSecurityTest() {}
        public Speedometre decreaseSpeedAsDeployed(Sonda.SondaDeployed sondaDeployed) {
            previousSpeed.speedSecurityTest();
            return DecreaseSpeed();
        }
    }
}
