package BlackJack.model.rules;

public class RulesFactory {
	

  public INewGameStrategy GetNewGameRule() {
	  
    return new AmericanNewGameStrategy();
  }
  
  public IHitStrategy GetHitRule() {
	  
	    //return new BasicHitStrategy(); 
		  
		  return new Soft17_Strategy();
	  }
  
	public IWiningStrategy GetWiningRule(){
		
		//return new DealerIsWinerStrategy();
		
		return new PlayerIsWinerStrategy(); // on equal Score player wins	
	} 
}