package fr.kissy.cenarius.domain.entity;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import fr.kissy.cenarius.domain.Deck;
import fr.kissy.cenarius.domain.card.Card;
import fr.kissy.cenarius.domain.card.CardType;
import fr.kissy.cenarius.domain.card.MinionCard;
import fr.kissy.cenarius.domain.function.PlayerToMinions;
import fr.kissy.cenarius.domain.move.Move;
import fr.kissy.cenarius.domain.predicate.AwakeMinion;
import fr.kissy.cenarius.domain.predicate.CostCard;
import fr.kissy.cenarius.domain.predicate.TauntMinion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class Player extends Entity {
    private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);
    private Random random;
    private Deck deck;
    private List<Card> hand;
    private List<Minion> minions;
    private Set<Player> opponents;
    // armor cumulated
    private int mana;
    private int maxMana;
    private int overload;

    public Player() {
        super(0, 30);
        this.random = new Random();
        this.deck = new Deck();
        this.hand = Lists.newArrayList();
        this.minions = Lists.newLinkedList();
        this.opponents = Sets.newHashSet();
        this.mana = 0;
        this.maxMana = 0;
    }

    public void newTurn() {
        if (this.maxMana < 10) {
            this.maxMana++;
        }
        this.mana = this.maxMana - this.overload;
        this.overload = 0;
        for (Minion minion : minions) {
            minion.newTurn();
        }
        draw();
    }

    public void play() {
        Player defender = Iterables.get(opponents, 0);

        // Play cards
        while (true) {
            FluentIterable<? extends Card> playableCards = getPlayableCards();
            if (playableCards.isEmpty()) {
                break;
            }
            int playableCardsCount = playableCards.size();
            Card cardToPlay = playableCards.get(random.nextInt(playableCardsCount));
            play(cardToPlay);
        }

        FluentIterable<Minion> awakeMinions = getAwakeMinions();
        FluentIterable<? extends Entity> availableTargets = getAvailableTargets();
        int availableTargetsCount = availableTargets.size();
        for (Minion minion : awakeMinions) {
            Entity target = availableTargets.get(random.nextInt(availableTargetsCount));
            target.hit(minion.getAttack());
            LOGGER.debug(minion.getName() + " attacked " + defender);
        }
    }

    private void play(Card card) {
        mana -= card.getCost();
        if (card.getCardType() == CardType.Minion) {
            minions.add(0, new Minion((MinionCard) card));
        } else {
            // TODO
        }
        hand.remove(card);
        LOGGER.debug("Played " + card.getName());
    }

    public void draw() {
        Card card = this.deck.draw();
        if (card != null) {
            hand.add(card);
            LOGGER.debug("Drown " + card.getName());
        } else {
            fatigue();
        }
    }

    private void fatigue() {
        hit(1);
    }

    public FluentIterable<? extends Entity> getAvailableTargets() {
        FluentIterable<? extends Entity> tauntMinions = FluentIterable.from(opponents)
                .transformAndConcat(PlayerToMinions.INSTANCE).filter(TauntMinion.INSTANCE);
        if (Iterables.isEmpty(tauntMinions)) {
            return FluentIterable.from(Iterables.concat(FluentIterable.from(opponents)
                    .transformAndConcat(PlayerToMinions.INSTANCE), opponents));
        }
        return tauntMinions;
    }

    public FluentIterable<? extends Card> getPlayableCards() {
        return FluentIterable.from(hand).filter(new CostCard(mana));
    }

    public FluentIterable<Minion> getAwakeMinions() {
        return FluentIterable.from(minions).filter(AwakeMinion.INSTANCE);
    }

    public void addOponent(Player opponent) {
        this.opponents.add(opponent);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("health", health)
                .add("mana", mana)
                .toString();
    }

    public List<Minion> getMinions() {
        return minions;
    }
}
