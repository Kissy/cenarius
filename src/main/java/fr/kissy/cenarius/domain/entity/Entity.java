package fr.kissy.cenarius.domain.entity;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public abstract class Entity {
    protected int attack;
    protected int health;

    protected Entity(int attack, int health) {
        this.attack = attack;
        this.health = health;
    }

    public abstract void newTurn();

    public void hit(int attack) {
        this.health -= attack;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }
}
