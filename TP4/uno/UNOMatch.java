package uno;

import java.util.*;

    public class UNOMatch {
        Map<String, List<Card>> playerCards = new HashMap<>();
        String currentPlayer;
        GameStatus gameStatus;
        boolean hasDrawnCard = false;


        public Card topOfDiscardPile = new NumberCard(1, "red");
        List<Card> deck = new ArrayList<>(Arrays.asList(
                new NumberCard(1, "red"), new NumberCard(2, "red"),
                new NumberCard(1, "blue"), new NumberCard(2, "blue"),
                new NumberCard(1, "green"), new NumberCard(2, "green"),
                new NumberCard(1, "yellow"), new NumberCard(2, "yellow")
        ));


        public UNOMatch(List <List<Card>> hands, List<String> playerNames) {

            for (int i = 0; i < playerNames.size(); i++) {
                playerCards.put(playerNames.get(i), new ArrayList<>(hands.get(i)));
            }

            currentPlayer = playerNames.get(0);
            gameStatus = new Player1Status(currentPlayer);
            gameStatus.previousStatus = new Player2Status(playerNames.get(playerNames.size() - 1));
            gameStatus.nextStatus = new Player2Status(playerNames.get(1));

            gameStatus.gameStates = List.of(gameStatus, gameStatus.previousStatus, gameStatus.nextStatus);

        }

        void nextPlayer() {
            List<String> players = new ArrayList<>(playerCards.keySet());
            currentPlayer = players.get( (players.indexOf(currentPlayer) + 1) % players.size() );
            hasDrawnCard = false;
            gameStatus.updateStatus( this );
        }

        public UNOMatch drawCard(String aPlayer) {
            return gameStatus.requestedStatus(aPlayer).drawAs( this );
        }
        public UNOMatch drawCardAsPlayer1() {
            return gameStatus.drawAsPlayer1( this );
        }
        public UNOMatch drawCardAsPlayer2() {
            return gameStatus.drawAsPlayer2( this );
        }
        public UNOMatch player1DrawCardAsPlayer1() {
            hasDrawnCardCheck();
            getCard(currentPlayer);
            hasDrawnCard = true;
            return this;
        }
        public UNOMatch player2DrawCardAsPlayer2() {
            hasDrawnCardCheck();
            getCard(currentPlayer);
            hasDrawnCard = true;
            return this;
        }


        public UNOMatch pass(String aPlayer ) {
            return gameStatus.requestedStatus(aPlayer).passAs( this );
        }
        public UNOMatch passAsPlayer1() {
            return gameStatus.passAsPlayer1( this );
        }
        public UNOMatch passAsPlayer2() {
            return gameStatus.passAsPlayer2( this );
        }
        public UNOMatch player1PassAsPlayer1() {
            passCheck();
            nextPlayer();
            return this;
        }
        public UNOMatch player2PassAsPlayer2() {
            passCheck();
            nextPlayer();
            return this;
        }

        public UNOMatch playCard(String aPlayer, Card playedCard) {
            if (!playedCard.canIGoOnTop(topOfDiscardPile)) {throw new RuntimeException("Invalid card");};
            gameStatus.requestedStatus(aPlayer).playsCardAs( this , playedCard);
            return this;
        }
        public UNOMatch playCardAsPlayer1(Card playedCard) {
            return gameStatus.playCardAsPlayer1(this, playedCard);
        }
        public UNOMatch playCardAsPlayer2(Card playedCard) {
            return gameStatus.playCardAsPlayer2(this, playedCard);
        }
        public UNOMatch player1PlayCardAsPlayer1(Card playedCard) {
            currentPlayerContains(playedCard);

            playedCard.processCard(this, currentPlayer);
            UNOCheck(this, currentPlayer, playedCard);

            playedCard.calledUNO = false;
            nextPlayer();
            return this;
        }
        public UNOMatch player2PlayCardAsPlayer2(Card playedCard) {
            currentPlayerContains(playedCard);

            playedCard.processCard(this, currentPlayer);
            UNOCheck(this, currentPlayer, playedCard);

            playedCard.calledUNO = false;
            nextPlayer();
            return this;
        }

        void getCard(String aPlayer) {
            List<Card> playerCards = getCards(aPlayer);
            playerCards.add(deck.get(0));
            deck.remove(0);
            this.playerCards.put(aPlayer, playerCards);
        }

        private void currentPlayerContains(Card playedCard) {
            if (!playerCards.get(currentPlayer).contains(playedCard)) { throw new RuntimeException("You don't have that card"); }
        }

        public void processSkipCard(String aPlayer, SkipCard skipCard) {
            List<Card> playerCards = getCards(aPlayer);
            playerCards.remove(skipCard);
            this.playerCards.put(aPlayer, playerCards);
            this.topOfDiscardPile = skipCard;
            nextPlayer();
        }

        public void processNumberCard(String aPlayer, NumberCard numberCard) {
            extractCardFromCurrentPlayer(aPlayer, numberCard);
        }

        public void processDrawTwoCard(String aPlayer, DrawTwoCard drawTwoCard) {
            int nextPlayerIndex = (new ArrayList<>(this.playerCards.keySet())).indexOf(aPlayer)+1;
            String nextPlayer = (new ArrayList<>(this.playerCards.keySet())).get(nextPlayerIndex % this.playerCards.size());
            this.getCard(nextPlayer);
            this.getCard(nextPlayer);

            extractCardFromCurrentPlayer(aPlayer, drawTwoCard);
        }

        public void processWildCard(String aPlayer, WildCard wildCard) {
            extractCardFromCurrentPlayer(aPlayer, wildCard);
        }

        public void processReverseCard(String aPlayer, ReverseCard reverseCard) {
            HashMap<String, List<Card>> playerCardsCopy = new HashMap<>();

            List<String> players = new ArrayList<>(this.playerCards.keySet());
            Collections.reverse(players);
            for (String player : players) { playerCardsCopy.put(player, this.playerCards.get(player)); }

            this.playerCards = playerCardsCopy;

            extractCardFromCurrentPlayer(aPlayer, reverseCard);
        }


        private void extractCardFromCurrentPlayer(String aPlayer, Card card) {
            List<Card> playerCards = getCards(aPlayer);
            playerCards.remove(card);
            this.playerCards.put(aPlayer, playerCards);
            this.topOfDiscardPile = card;
        }

        private List<Card> getCards(String aPlayer) {
            return (List<Card>) new ArrayList(this.playerCards.get(aPlayer));
        }

        void UNOCheck(UNOMatch match, String aPlayer, Card playedCard) {
            if (playedCard.calledUNO && getCards(aPlayer).size() > 1) {
                throw new RuntimeException("You can't call UNO if you have more than one card");
            }
            if (getCards(aPlayer).size() == 1 && !playedCard.calledUNO) {
                throw new RuntimeException("UNO");
            }
            if (getCards(aPlayer).size() == 0) {
                match.gameStatus = new GameEndedStatus( aPlayer );
            }
        }


        public void passCheck( ) {
            if (!hasDrawnCard) throw new RuntimeException("You have to draw a card");
        }
        public void hasDrawnCardCheck( ) {
            if (hasDrawnCard) throw new RuntimeException("You have already drawn a card");
            else hasDrawnCard = true;
        }
    }