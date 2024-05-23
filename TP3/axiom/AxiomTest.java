package axiom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AxiomTest {
    //AXIOM QUIETO


    @Test void test01StartingSpeedIsZero() {
        assertEquals(0, newBoat().getSpeed());
    }

    @Test void test02DefaultDirectionIsNorth() {
        assertEquals("N", newBoat().getDirection());
    }

    @Test void test03StartingSondaIsNotDeployed() {
        assertEquals( false, newBoat().isSondaDeployed());
    }

    @Test void test03SlowDownWhenSpeedZeroIsZero() {
        assertEquals(0, newBoat().process("s").getSpeed());
    }

    @Test void test04CannotDeployWhenStopped() {
        assertThrowsLike( "Too Slow", () -> newBoat().process("d"));
    }


    //CAMBIOS EN AXIOM
    //speed (direction ==)
    @Test void test05SpeedIncreaseCorrectly() {
        newBoatCompleteAssertion("i", 1, "N", false);
        newBoatCompleteAssertion("iiiiii", 6, "N", false);
    }

    @Test void test06IncreasingAndDecreasingSpeedCorrecly() {
        newBoatCompleteAssertion("iiss", 0, "N", false);
    }

    @Test void test07InsistInStopping() {
        newBoatCompleteAssertion("issss", 0, "N", false);
    }

    //direction (speed ==)
    @Test void test08DirectionChangesCorrectly() {
        newBoatCompleteAssertion("r", 0, "E", false);
        newBoatCompleteAssertion("l", 0, "O", false);
    }

    @Test void test09MultipleDirectionChangesCorrectly() {
        newBoatCompleteAssertion("rl", 0, "N", false);
        newBoatCompleteAssertion("lr", 0, "N", false);
        newBoatCompleteAssertion("rrrrrr", 0, "S", false);
        newBoatCompleteAssertion("ll", 0, "S", false);
        newBoatCompleteAssertion("rrr", 0, "O", false);
    }

    @Test void test10UpdatedDirectionChangesCorrectly() {
        Axiom movingBoat = newBoat().process("r");

        assertEquals("E", movingBoat.getDirection());
        assertEquals("N", movingBoat.process("l").getDirection());
        assertEquals("S", movingBoat.process("rr").getDirection());
        assertEquals("O", movingBoat.process("r").getDirection());
        assertEquals(0, movingBoat.getSpeed());
    }


    // SONDA
    @Test void test03RetrievingSondaWhenNotDeployed() {
        assertEquals(false, newBoat().process("f").isSondaDeployed());
    }

    @Test void test11DeployingSondaCorrectly() {
        assertEquals( true, newBoat().process("id").isSondaDeployed());
    }

    @Test void test12InsistInDeployingSonda() {
        assertEquals(true, newBoat().process("iddddd").isSondaDeployed());
    }

    @Test void test13RetrievingSondaCorrectly() {
        assertEquals( false, newBoat().process("id").process("f").isSondaDeployed());
    }

    @Test void test13InsistInRetrievingSonda() {
        newBoatCompleteAssertion("idffff", 1, "N", false);
        newBoatCompleteAssertion("idfiffdddf", 2, "N", false);
    }

    @Test
    void test13IncreasingAndDecreasingSpeedWithSonda() {
        newBoatCompleteAssertion("idiiiiii", 7, "N", true);
        newBoatCompleteAssertion("idiiss", 1, "N", true);
        newBoatCompleteAssertion("idisi", 2, "N", true);
    }

    @Test void test11CannotDeploySondaAfterDecreasingAllSpeed() {
        assertThrowsLike( "Too Slow", () -> newBoat().process("iiisssd"));
    }

    @Test void test15CannotStopWhenSondaDeployed() {
        assertThrowsLike( "Too Slow", () -> newBoat().process("ids"));
        assertThrowsLike( "Too Slow", () -> newBoat().process("idsisss"));

    } //TODO ver de cambiar este a CANNOT STOP WHEN DEPLOYING

    @Test void test14CannotChangeDirectionWhenSondaDeployed() {
        assertThrowsLike( "Cannot Change Direction when Deploying", () -> newBoat().process("idr"));
        assertThrowsLike( "Cannot Change Direction when Deploying", () -> newBoat().process("irrdl"));
    }

    //COMBINACIONES
    @Test void testSpeedDirectionAndSondaChangesTogether() {
        newBoatCompleteAssertion("irrli", 2, "E", false);
        newBoatCompleteAssertion("irrlii", 3, "E", false);
        newBoatCompleteAssertion("irrliir", 3, "S", false);
        newBoatCompleteAssertion("irrliirs", 2, "S", false);
        newBoatCompleteAssertion("irrliirsd", 2, "S", true);
        newBoatCompleteAssertion("irrliirsdf", 2, "S", false);
        newBoatCompleteAssertion("idfriffrdiisddf", 3, "S", false);
    }

    @Test void test22UnknownCommand() {
        assertThrowsLike( "WARNING: Unknown command", () -> newBoat().process("idx"));
        assertThrowsLike( "WARNING: Unknown command", () -> newBoat().process("xrr"));
    }

    @Test void test23Equals() {
        newBoat().equals(newBoat());
        boolean equalsDifferent = newBoat().equals(newBoat().process("i")) == false;
    }

    private Axiom newBoat() {  return new Axiom(); }
    private void newBoatCompleteAssertion(String command, int speed, String direction, boolean sondaState) {
        Axiom boat = newBoat().process(command);
        assertEquals(speed, boat.getSpeed());
        assertEquals(direction, boat.getDirection());
        assertEquals(sondaState, boat.isSondaDeployed());
    }

    private void assertThrowsLike(String errorMessage, Executable runnable){
        assertEquals( assertThrows( Exception.class, runnable) .getMessage(), errorMessage);
    }
}