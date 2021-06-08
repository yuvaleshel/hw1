import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Deck {
    List<Card> deck = new ArrayList<Card>(52);

    public Deck(boolean init)
    {
        if (init) {
            List<Shape> shapes = new ArrayList<Shape>();
            shapes.add(Shape.Spades);
            shapes.add(Shape.Clubs);
            shapes.add(Shape.Diamoonds);
            shapes.add(Shape.Haarts);

            for (Shape s : shapes) {
                for (int j = 0; j < 13; j++) {
                    Card c = new Card(j + 1, s);
                    deck.add(c);
                }
            }
        }
    }
    public int getDeckSize(){
        return this.deck.size();
    }
    public void addCard(Card card)
    {
        deck.add(0,card);
    }
    public Card removeTopCard()
    {
        Card last = deck.get(deck.size()-1);
        deck.remove(deck.size() - 1);
        return last;
    }

    public boolean isEmpty()
    {
        return deck.isEmpty();
    }
    public void shuffle()
    {
        for (int i = 0 ; i<50; i++)
        {
//            int j = (int)(Math.random()*52);
//            int k = (int)(Math.random()*52);
            int j = Main.rnd.nextInt(this.deck.size());
            int k = Main.rnd.nextInt(this.deck.size());
            Collections.swap(this.deck, j, k);
            Card cardJ = deck.get(j);
            Card cardK = deck.get(k);

            deck.set(j,cardK);
            deck.set(k,cardJ);
        }
    }

    public String toString()
    {
        String s = "";
        for (Card c:deck)
            s += c.toString();

        return s;


    }

    public Deck united (Deck d1, Deck d2) {
        int UNITED_DECK_SIZE = d2.getDeckSize();
        for(int i=0; i< UNITED_DECK_SIZE ;i++)
            d1.addCard(d2.removeTopCard());
        return d1;
    }


}
