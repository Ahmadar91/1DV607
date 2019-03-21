package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

class Soft17_Strategy implements IHitStrategy {

    private final int g_hitLimit = 17;

	
	public boolean DoHit(Player dealer) {
		
		if (dealer.CalcScore_Soft17() == g_hitLimit) {
			
			for (Card c : dealer.GetHand()) {
				
				if (c.GetValue() == Card.Value.Ace) {
					
					return true;
				}
			}
		}
		return dealer.CalcScore() < g_hitLimit;
	}
}