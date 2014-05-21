package fr.kissy.cenarius.domain.card;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public abstract class Card {
    private CardType cardType;
    private String name;
    private int cost;

    public Card(CardType cardType, String name, int cost) {
        this.cardType = cardType;
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public CardType getCardType() {
        return cardType;
    }
}
