package BlackJack.model;

public class Game {

  private Dealer m_dealer;
  private Player m_player;

  public Game()
  {
    m_dealer = new Dealer(new BlackJack.model.rules.RulesFactory());
    m_player = new Player();
  }
    
    
  public boolean IsGameOver()
  {
    return m_dealer.IsGameOver();
  }
  
  public boolean IsDealerWinner()
  {
    return m_dealer.IsDealerWinner(m_player);
  }
  
  public boolean NewGame()
  {
    return m_dealer.NewGame(m_player);
  }
  
  public boolean Hit()
  {
    return m_dealer.Hit(m_player);
  }
  
  public boolean Stand()
  {
    // TODO: Implement this according to Game_Stand.sequencediagram
	  return m_dealer.Stand();
    //return true; 
  }
  
  public Iterable<Card> GetDealerHand()
  {
    return m_dealer.GetHand();
  }
  
  public Iterable<Card> GetPlayerHand()
  {
    return m_player.GetHand();
  }
  
  public int GetDealerScore()
  {
    return m_dealer.CalcScore();
	  //return 20; //  for WiningStrategy  testing purpose
    
  }
  
  public int GetPlayerScore()
  {
    return m_player.CalcScore();
   // return 20;//  for WiningStrategy  testing purpose
  }


public void AddSubscriber(IDealCardObserver a_subscriber) {
	// TODO Auto-generated method stub
	m_dealer.AddSubscriber(a_subscriber);
	
}
    
  
}