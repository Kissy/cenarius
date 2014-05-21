package fr.kissy.cenarius.domain.move;

import fr.kissy.cenarius.domain.card.SpellCard;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class SpellMove extends Move {
    private SpellCard spellCard;

    public SpellMove(SpellCard spellCard) {
        this.spellCard = spellCard;
    }
}
