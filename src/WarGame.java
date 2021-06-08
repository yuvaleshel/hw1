public class WarGame
{
    Player player1;
    Player player2;
    private Player first;
    private Player second;

    private Player winner;

    public WarGame(String p1, String p2)
    {
        player1 = new Player(p1);
        player2 = new Player(p2);
    }
    public Player getPlayer1() {return player1;}
    public Player getPlayer2() {return player2;}
    public void setPlayer1(Player p1) {player1 = p1;}
    public void setPlayer2(Player p2) {player2 = p2;}

    public void firstPlayer()
    {
        int diff = player1.getName().compareTo(player2.getName());

        if(diff<0)
        {
            this.first = new Player(player1);
            this.second = new Player(player2);
        }
        else
        {
            this.first = new Player(player2);
            this.second = new Player(player1);
        }
    }

    public void initializeGame() {
        this.firstPlayer();
        Deck deck = new Deck(true);
        deck.shuffle();
        System.out.println(this.first.getName());
        System.out.println(this.second.getName());

        for(int i = 0; i<26; i++)
        {

            this.first.gamePack.addCard(deck.removeTopCard());
            this.second.gamePack.addCard(deck.removeTopCard());
        }
    }

    public String start()
    {
        this.initializeGame();

        int n = 1;
        System.out.println("Initializing the game...");

        while(!(this.first.outOfCards() && this.second.outOfCards()))
        {
            Deck temp = new Deck(false);

            System.out.println("------------------------- Round number " + n + "-------------------------");
            Card c1 = this.first.drawCard();
            temp.addCard(c1);
            System.out.println(this.first.getName() + " drew " + c1.toString());
            Card c2 = this.second.drawCard();
            temp.addCard(c2);
            System.out.println(this.second.getName() + " drew " + c2.toString());

            int val1 = c1.getValue();
            int val2 = c2.getValue();

            cardsCompare(val1,val2, temp);

            if (this.winner==this.first) {
                Deck united = this.first.getWinPack().united(this.first.getWinPack(),temp);
                this.first.setWinPack(united);
            }
            if (this.winner==this.second)
            {
                Deck united = this.second.getWinPack().united(this.second.getWinPack(),temp);
                this.second.setWinPack(united);
            }
            n++;
        }

        if(this.first.outOfCards())
            return this.second.getName();
        return this.first.getName();

    }

    public void cardsCompare(int val1, int val2, Deck temp)
    {
        if(val1>val2)
        {
            this.winner=this.first;
            System.out.println(this.first.getName() + " won ");
        }
        else {
            if (val1 < val2)
            {
                this.winner = this.second;
                System.out.println(this.second.getName() + " won ");
            }
            else
            {
                Card c1;
                Card c2;
                for (int i = 0; i < 2; i++) {
                    if (!(this.first.outOfCards() && (!(this.second.outOfCards()))))
                    {
                        c1 = this.first.drawCard();
                        temp.addCard(c1);
                        System.out.println(this.first.getName() + " drew " + c1.toString());
                        c2 = this.second.drawCard();
                        temp.addCard(c2);
                        System.out.println(this.second.getName() + " drew " + c2.toString());
                    }
                }
                if (!(this.first.outOfCards() && this.second.outOfCards())) {
                    Card c3 = this.first.drawCard();
                    Card c4 = this.second.drawCard();
                    System.out.println(this.first.getName() + " drew " + c3.toString());
                    System.out.println(this.second.getName() + " drew " + c4.toString());
                    temp.addCard(c3);
                    temp.addCard(c4);
                    cardsCompare(c3.getValue(), c4.getValue(), temp);
                }

            }

        }
    }
}
