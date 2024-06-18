import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.util.List;

import uno.*;

public class unoTest {

    @Test void StartMatch() {
        assertCabeza(matchEmilioAndJulio(), redCard(1));
    }

    @Test void CannotDrawCardWhenNotYourTurn() {
        assertThrowsLike(() -> matchEmilioAndJulio().drawCard("Julio"), "It's not your turn");
    }

    @Test void PlayAndDrawCard() {
        assertCabeza( matchEmilioAndJulio().drawCard("Emilio"), redCard(1));
    }

    @Test void CannotDrawTwice() {
        assertThrowsLike( () -> matchEmilioAndJulio().drawCard("Emilio").drawCard("Emilio") , "You have already drawn a card");
    }

    @Test void CannotPassWhenNotYourTurn() {
        assertThrowsLike( () -> matchEmilioAndJulio().pass("Julio"),"It's not your turn");
    }

    @Test void CannotPassWithoutDrawingCard() {
        assertThrowsLike( () -> matchEmilioAndJulio().pass("Emilio"),"You have to draw a card");
    }

    @Test void DrawAndPassCorrectly() {
        assertThrowsLike( () -> matchEmilioAndJulio()
                                .drawCard("Emilio")
                                .pass("Emilio")
                                .drawCard("Emilio"),
                                "It's not your turn");
    }

    @Test void CannotPlayCardWhenNotYourTurn() {
        assertThrowsLike( () -> matchEmilioAndJulio().playCard("Julio", redCard(6)), "It's not your turn");
    }

    @Test void CannotPlayACardYouDoNotHave() {
        assertThrowsLike( () -> matchEmilioAndJulio().playCard("Emilio", redCard(6)) , "You don't have that card");
    }


    @Test void TopOfDiscarpPileIsPlayedCard() {
        assertCabeza(matchEmilioAndJulio3CardsEmilio().playCard("Emilio",redCard(3)), redCard(3));
    }

    @Test void DrawAndPlayCardInTheSameTurn() {
        assertCabeza(matchEmilioAndJulio()
                    .drawCard("Emilio")
                    .playCard("Emilio", redCard(3)),
                    redCard(3));
    }

    @Test void PlaySkipCardWithTwoPlayers() {
        UNOMatch match = matchEmilioAndJulio3CardsEmilio().playCard("Emilio", redSkipCard);

        assertThrowsLike(() -> match.playCard("Julio", new NumberCard(6, "red")), "It's not your turn");
        assertCabeza(match.drawCard("Emilio"), redSkipCard);
    }


    @Test void PlayReverseCardWithTwoPlayers() {
        UNOMatch match = matchEmilioAndJulioWith(new ReverseCard("red")).playCard("Emilio", new ReverseCard("red"));

        assertCabeza(match, new ReverseCard( "red"));
        assertThrowsLike(() -> match.playCard("Emilio", new NumberCard(6, "red")), "It's not your turn");
    }

    @Test void YouHaveToChooseColorAfterPlayingWildCard() {
        assertThrows( Exception.class, () -> matchEmilioAndJulioWith( new WildCard() ).playCard("Emilio", new WildCard()).playCard("Julio",new NumberCard(6, "red")));
    }

    @Test void PlayWildCardAndChooseColor() {
        assertCabeza(matchEmilioAndJulioWith( new WildCard() ).playCard("Emilio", new WildCard().color("red")), new WildCard().color("red"));
    }

    @Test void PlayDrawTwo() {
        assertCabeza(matchEmilioAndJulioWith( new DrawTwoCard( "red" ) ).playCard("Emilio", new DrawTwoCard("red")), new DrawTwoCard("red"));
    }

    @Test void CannotPlayACardThatDoesNotMatchTheTopOfDiscardPile() {
        assertThrowsLike( () -> matchEmilioAndJulioWith( new NumberCard(3, "green") ).playCard("Emilio", new DrawTwoCard("green")) , "Invalid card");
        assertThrowsLike( () -> matchEmilioAndJulioWith( new SkipCard( "green") ).playCard("Emilio", new SkipCard("green")) , "Invalid card");
        assertThrowsLike( () -> matchEmilioAndJulioWith( new ReverseCard("green") ).playCard("Emilio", new ReverseCard("green")) , "Invalid card");
        assertThrowsLike( () -> matchEmilioAndJulioWith( new DrawTwoCard("green") ).playCard("Emilio", new DrawTwoCard("green")) , "Invalid card");
    }

    @Test void PlayReverseCardWithThreePlayers() { 
        assertCabeza(matchEmilioJulioAndBruno().playCard("Emilio", new ReverseCard("red"))
                .playCard("Bruno", new NumberCard(7, "red")), new NumberCard( 7, "red"));
    }

    @Test void JugarMasDosEnmatchDe3SalteaJugador() {
        UNOMatch match = matchEmilioJulioAndBruno().playCard("Emilio", redSkipCard);
        assertThrowsLike( () -> match.playCard("Julio", new NumberCard(6, "red")), "It's not your turn");

        assertCabeza(match, redSkipCard);

        match.playCard("Bruno", new NumberCard(7, "red"));
        assertCabeza(match, new NumberCard( 7, "red"));
    }


    @Test void NoSePuedeCantarUnoSiQuedaMasDeUnaCarta() {
        assertThrowsLike( () -> matchEmilioAndJulioWith( new NumberCard(4, "red") ).playCard("Emilio", new NumberCard(4, "red").uno()) , "You can't call UNO if you have more than one card");
    }

    @Test void CantarUnoYNoSePuedeJugarCartaSiElJuegoHaTerminado() {
        UNOMatch match = matchEmilioAndJulio();

        match.playCard("Emilio", redSkipCard);
        assertCabeza(match, redSkipCard);

        match.playCard("Emilio", redCard(3).uno() );
        assertCabeza(match, redCard(1));

        assertThrowsLike( () -> match.playCard("Julio", redCard(6)) , "Game has ended");
    }


    @Test void QuedarseSinCartasYNoCantarUno() {
        UNOMatch match = new UNOMatch(List.of( List.of(new NumberCard(1, "green")),
                List.of(new NumberCard(5, "green")) ),List.of("Emilio", "Julio"));

        match.playCard("Emilio", new NumberCard(1, "green"));
        assertThrowsLike( () -> match.playCard("Julio", new NumberCard(5, "green").uno()) , "Game has ended");

    }


    private void assertThrowsLike(Executable runnable, String errorMessage){
        assertEquals( assertThrows( Exception.class, runnable) .getMessage(), errorMessage);
    }

    NumberCard redCard( int number) {
        return new NumberCard(number, "red");
    }
    NumberCard blueCard(int numero) {
        return new NumberCard(numero, "blue");
    }
    SkipCard redSkipCard = new SkipCard( "red" ); 


    private UNOMatch matchEmilioAndJulio() {
        return new UNOMatch(List.of(List.of(redCard(3), redSkipCard),
                List.of(blueCard(6), new NumberCard(6, "red"))),
                List.of("Emilio", "Julio"));
    }
    private UNOMatch matchEmilioAndJulio3CardsEmilio() {
        return new UNOMatch(List.of(List.of(redCard(3), redSkipCard,new NumberCard(4, "red")),
                List.of(blueCard(6), new NumberCard(6, "red"))),
                List.of("Emilio", "Julio"));
    }

    private UNOMatch matchEmilioAndJulioWith(Card carta ) {
        return new UNOMatch(List.of(List.of(redCard(3), redSkipCard, carta),
                List.of(blueCard(6), new NumberCard(6, "red"))),
                List.of("Emilio", "Julio"));
    }

    private UNOMatch matchEmilioJulioAndBruno() {
        UNOMatch match = new UNOMatch(List.of( List.of(redSkipCard, new NumberCard(9, "red"), new ReverseCard("red")),
                List.of(new NumberCard(6, "red"), new NumberCard(6, "yellow")),
                List.of(new NumberCard(7, "red"),new NumberCard(4, "red"),new NumberCard(7, "red"))),List.of("Emilio","Julio","Bruno"));
        return match;
    }

    private void assertCabeza(UNOMatch match, Card carta) {
        carta.equals(match.topOfDiscardPile);
    }

}

