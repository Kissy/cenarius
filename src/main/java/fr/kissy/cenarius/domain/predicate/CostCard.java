package fr.kissy.cenarius.domain.predicate;

import com.google.common.base.Predicate;
import fr.kissy.cenarius.domain.card.Card;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class CostCard implements Predicate<Card> {
    private int maxCost;

    public CostCard(int maxCost) {
        this.maxCost = maxCost;
    }

    @Override
    public boolean apply(Card card) {
        return card.getCost() <= maxCost;
    }
}
