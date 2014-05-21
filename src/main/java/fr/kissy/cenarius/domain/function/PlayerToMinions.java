package fr.kissy.cenarius.domain.function;

import com.google.common.base.Function;
import fr.kissy.cenarius.domain.entity.Minion;
import fr.kissy.cenarius.domain.entity.Player;

import java.util.List;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class PlayerToMinions implements Function<Player, List<Minion>> {
    public static final PlayerToMinions INSTANCE = new PlayerToMinions();

    @Override
    public List<Minion> apply(Player player) {
        return player.getMinions();
    }
}
