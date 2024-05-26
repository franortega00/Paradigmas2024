package axiom;

public abstract class Navigation {
    public abstract Navigation turnLeft();
    public abstract Navigation turnRight();
    public abstract String heading();

    public static class North extends Navigation {
        public String heading() { return "N"; }
        public Navigation turnLeft() { return new West(); }
        public Navigation turnRight() { return new East(); }
    }

    public static class South extends Navigation {
        public String heading() { return "S"; }
        public Navigation turnLeft() { return new East(); }
        public Navigation turnRight() { return new West(); }
    }

    public static class West extends Navigation {
        public String heading() { return "O"; }
        public Navigation turnLeft() { return new South(); }
        public Navigation turnRight() { return new North(); }
    }

    public static class East extends Navigation {
        public String heading() { return "E"; }
        public Navigation turnLeft() { return new North(); }
        public Navigation turnRight() { return new South(); }
    }
}