import java.util.List;

public class Player
{
    final String name;
    Deck winPack;
    Deck gamePack;

    public Player(String name)
    {
        this.name = name;
        this.gamePack = new Deck(false);
        this.winPack = new Deck(false);
    }
    public Player(Player player)
    {
        this.name = player.getName();
        this.gamePack = player.getGamePack();
        this.winPack = player.getWinPack();

    }

    public Deck getWinPack() {return this.winPack;}
    public Deck getGamePack() {return this.gamePack;}
    public String getName() {return this.name;}

    public void setWinPack(Deck pack) {this.winPack = pack;}
    public void setGamePack(Deck pack) {this.gamePack = pack;}

    public void addCardToWinPack(Card card) {winPack.addCard(card);}
    public void addCardToGamePack(Card card) {gamePack.addCard(card);}

    public Card drawCard(){return gamePack.removeTopCard();}

    public boolean outOfCards() {return gamePack.isEmpty() && winPack.isEmpty();}

    public String toString(){return this.name;}
}
