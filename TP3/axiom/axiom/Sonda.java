package axiom;

public abstract class Sonda {
    public abstract boolean sondaState();
    public abstract Sonda RetrieveSonda();
    public abstract Sonda DeploySonda();
    public abstract void sondaSecurityTest();
    public abstract OficialSpeed decreaseSpeed(Axiom axiom);

    public static class SondaDeployed extends Sonda {
        public static final String cannotChangeDirectionWhenDeploying = "Cannot Change Direction when Deploying";
        public boolean sondaState() {
            return true;
        }
        public SondaNotDeployed RetrieveSonda() {
            return new SondaNotDeployed();
        }
        public SondaDeployed DeploySonda() { return this; }
        public void sondaSecurityTest() { throw new RuntimeException(cannotChangeDirectionWhenDeploying); }
        public OficialSpeed decreaseSpeed(Axiom axiom) {
            axiom.speed.decreaseSpeedAsDeployed(this);
            return axiom.speed;
        }
    }

    public static class SondaNotDeployed extends Sonda {
        public boolean sondaState() { return false; }
        public SondaNotDeployed RetrieveSonda() {
            return this;
        }
        public SondaDeployed DeploySonda() { return new SondaDeployed(); }
        public void sondaSecurityTest() {}
        public OficialSpeed decreaseSpeed(Axiom axiom) {
            axiom.speed.decreaseSpeed();
//            axiom.speed = axiom.speed.DecreaseSpeed();
            return axiom.speed;
        }
    }
}
