package fr.kissy.cenarius.domain;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import fr.kissy.cenarius.domain.card.Card;
import fr.kissy.cenarius.domain.card.MinionCard;
import fr.kissy.cenarius.domain.card.MinionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class Deck {
    private static final Logger LOGGER = LoggerFactory.getLogger(Deck.class);
    private Deque<Card> cards;

    public Deck() {
        List<Card> availableCards = new ArrayList<Card>(30);
        availableCards.add(new MinionCard("Beast", 1, MinionType.Beast, 1, 1));
        availableCards.add(new MinionCard("Beast", 1, MinionType.Beast, 1, 1));
        availableCards.add(new MinionCard("Dragon", 2, MinionType.Dragon, 2, 2));
        availableCards.add(new MinionCard("Dragon", 2, MinionType.Dragon, 2, 2));
        availableCards.add(new MinionCard("Demon", 2, MinionType.Demon, 1, 3));
        availableCards.add(new MinionCard("Demon", 2, MinionType.Demon, 1, 3));
        availableCards.add(new MinionCard("General", 3, MinionType.General, 2, 3));
        availableCards.add(new MinionCard("General", 3, MinionType.General, 2, 3));
        availableCards.add(new MinionCard("Murloc", 3, MinionType.Murloc, 3, 2));
        availableCards.add(new MinionCard("Murloc", 3, MinionType.Murloc, 3, 2));
        Collections.shuffle(availableCards, new Random());
        this.cards = new ArrayDeque<Card>(availableCards);
    }

    public Card draw() {
        return cards.pollFirst();
    }
}
