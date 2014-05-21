package fr.kissy.cenarius.domain.predicate;

import com.google.common.base.Predicate;
import fr.kissy.cenarius.domain.entity.Minion;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class AwakeMinion implements Predicate<Minion> {
    public static final AwakeMinion INSTANCE = new AwakeMinion();

    @Override
    public boolean apply(Minion minion) {
        return !minion.isSleeping();
    }
}
