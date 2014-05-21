package fr.kissy.cenarius.domain.move;

import fr.kissy.cenarius.domain.card.MinionCard;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class MinionMove extends Move {
    private MinionCard minionCard;

    public MinionMove(MinionCard minionCard) {
        this.minionCard = minionCard;
    }
}
