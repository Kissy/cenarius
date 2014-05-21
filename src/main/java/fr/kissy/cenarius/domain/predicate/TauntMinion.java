package fr.kissy.cenarius.domain.predicate;

import com.google.common.base.Predicate;
import fr.kissy.cenarius.domain.entity.Minion;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class TauntMinion implements Predicate<Minion> {
    public static final TauntMinion INSTANCE = new TauntMinion();

    @Override
    public boolean apply(Minion minion) {
        return minion.hasTaunt();
    }
}
