package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;
import BlackJack.model.IDealCardObserver;

public class PlayGame implements IDealCardObserver{
	private Game a_game;
	private IView a_view;
	

	public PlayGame(Game a1_game, IView a1_view){
		this.a_game = a1_game;
		this.a_view = a1_view;
		a_game.AddSubscriber(this);
	}
	
	
	public boolean Play() {
		 
		
	    a_view.DisplayWelcomeMessage();
	    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
	    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
	
	    if (a_game.IsGameOver())
	    {
	        a_view.DisplayGameOver(a_game.IsDealerWinner());
	    }
	    
	    
	    a_view.collectEvents();
	    
	    
	    if (a_view.WanToplay())
	    {
	        a_game.NewGame();
	    }
	    else if ( a_view.WantToHit())
	    {
	        a_game.Hit();
	    }
	    else if (a_view.WantToStand())
	    {
	        a_game.Stand();
	    }
	    else if (a_view.WantToQuit())
	    	return false;
	    
	   return true;
	}


	@Override
	public void DealCardEvent() {
		
		try {
			Thread.sleep(2000);
		    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
		    Thread.sleep(2000);
		    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
			
			} catch (Exception e) {
				System.out.println("");
			}

	}
	

}