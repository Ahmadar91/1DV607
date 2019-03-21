package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public interface IWiningStrategy {
	
	boolean TestIfWinner(Dealer dealer, Player player, int maxScore);

}
