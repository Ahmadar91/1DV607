package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public class DealerIsWinerStrategy implements IWiningStrategy {

	@Override
	public boolean TestIfWinner(Dealer dealer, Player player, int maxScore) {
		// TODO Auto-generated method stub
		
		// the following code is cut from Dealer
	    if (player.CalcScore() > maxScore) {
	        return true;
	        
	      } else if (dealer.CalcScore() > maxScore) {
	        return false;
	      }
	      return dealer.CalcScore() >= player.CalcScore(); 
		
		
	}

}
