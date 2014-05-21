package fr.kissy.cenarius.domain;

import com.google.common.collect.Lists;
import fr.kissy.cenarius.domain.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class Game {
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    private List<Player> players;
    private int currentPlayerIndex;

    public Game(List<Player> players) {
        this.players = players;
        this.currentPlayerIndex = new Random().nextInt(1);
        currentPlayer().addOponent(otherPlayer());
        otherPlayer().addOponent(currentPlayer());
        for (int i = 1; i <= 3; i++) {
            currentPlayer().draw();
        }
        for (int i = 1; i <= 4; i++) {
            otherPlayer().draw();
        }
    }

    private void run() {
        while (true) {
            Player currentPlayer = currentPlayer();
            Player otherPlayer = otherPlayer();
            LOGGER.debug("Score : {} - {}", currentPlayer.toString(), otherPlayer.toString());
            if (currentPlayer.isDead()) {
                break;
            }
            currentPlayer.newTurn();
            currentPlayer.play();
            endTurn();
        }
    }

    private void endTurn() {
        currentPlayerIndex ^= 1;
    }

    private Player currentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private Player otherPlayer() {
        return players.get(currentPlayerIndex ^ 1);
    }

    public static void main(String[] args) {
        int games = 0;
        double startTime = System.nanoTime();
        while (games < 10000) {
            new Game(
                    Lists.newArrayList(
                            new Player(),
                            new Player()
                    )
            ).run();
            games++;
            double elapsedTime = (System.nanoTime() - startTime) / 1000000000d;
            int gamesPerSeconds = (int) Math.round(games / elapsedTime);
            LOGGER.info("{} games played in {}ms at {} games/s", games, Math.round(elapsedTime * 1000d), gamesPerSeconds);
        }
    }
}
