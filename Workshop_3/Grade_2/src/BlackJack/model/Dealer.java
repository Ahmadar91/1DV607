package BlackJack.model;

import BlackJack.model.rules.*;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  
  private IWiningStrategy m_winingRule;

  public Dealer(RulesFactory a_rulesFactory) {
  
    m_newGameRule = a_rulesFactory.GetNewGameRule();
    
    m_hitRule = a_rulesFactory.GetHitRule();
    
    m_winingRule = a_rulesFactory.GetWiningRule();
    
    /*for(Card c : m_deck.GetCards()) {
      c.Show(true);
      System.out.println("" + c.GetValue() + " of " + c.GetColor());
    }    */
  }
  
  public boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(this, a_player);   
    }
    return false;
  }

  public boolean Hit(Player a_player) {
	  
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
    	
    	Get_Deal_Card(a_player, true);
       
      return true;
    }
    return false;
  }
  
  public void Get_Deal_Card(Player p , boolean bool) {
	  Card c;
      c = m_deck.GetCard();
      c.Show(bool);
      p.DealCard(c);
      Notify();

  }
  
  public boolean IsDealerWinner(Player a_player) {
	  
	  return m_winingRule.TestIfWinner(this, a_player, g_maxScore);
  }

  public boolean IsGameOver() {
    if (m_deck != null && m_hitRule.DoHit(this) != true) {
        return true;
    }
    return false;
  }

  public boolean Stand() {
	
	if (m_deck != null) {
		this.ShowHand();
		while (m_hitRule.DoHit(this)) {
	
			Get_Deal_Card(this, true);	
		}
		return true;
	}
	return false;
  } 
}