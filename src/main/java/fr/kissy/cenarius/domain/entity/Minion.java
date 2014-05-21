package fr.kissy.cenarius.domain.entity;

import fr.kissy.cenarius.domain.card.MinionCard;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class Minion extends Entity {
    private MinionCard minionCard;
    private boolean taunt;
    private boolean sleeping;

    public Minion(int attack, int health) {
        super(attack, health);
        this.sleeping = true;
    }

    public Minion(MinionCard minionCard) {
        this(minionCard.getAttack(), minionCard.getHealth());
        this.minionCard = minionCard;
    }

    public void newTurn() {
        sleeping = false;
    }

    public String getName() {
        return minionCard.getName();
    }

    public int getAttack() {
        return minionCard.getAttack();
    }

    public boolean hasTaunt() {
        return taunt;
    }

    public boolean isSleeping() {
        return sleeping;
    }
}
