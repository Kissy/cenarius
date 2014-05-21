package fr.kissy.cenarius.domain.card;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class MinionCard extends Card {
    private MinionType minionType;
    private int attack;
    private int health;

    public MinionCard(String name, int cost, MinionType minionType, int attack, int health) {
        super(CardType.Minion, name, cost);
        this.minionType = minionType;
        this.attack = attack;
        this.health = health;
    }

    public boolean canAttack() {
        return true;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }
}
