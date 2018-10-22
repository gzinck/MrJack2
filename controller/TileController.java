package controller;
import java.util.Observable;
import java.util.Observer;
import model.token.*;
import model.turnkeeper.TurnKeeper;
public class TileController implements Observer
{
	private Barricade barr;
	private GasLight gasLight;
	private ManholeCover manholeCover;
	private TurnKeeper turnKeeper;
	public TileController(Barricade inBarr, GasLight inGasLight, ManholeCover inManholeCover, TurnKeeper inTurnKeeper)
	{
		barr = inBarr;
		gasLight = inGasLight;
		manholeCover = inManholeCover;
		turnKeeper = inTurnKeeper;
		barr.addObserver(this);
		gasLight.addObserver(this);
		manholeCover.addObserver(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		
		
	}
	public void tileClicked(int row, int col) {
		int turnStage = turnKeeper.getStage();
		switch(turnStage) {
//		The below two cases have nothing to do with tiles
//		case TurnKeeper.STAGE_CHOOSE_CHAR:
//			break;
//		case TurnKeeper.STAGE_CHOOSE_IFACTIONFIRST:
//			break;
		case TurnKeeper.STAGE_CHOOSE_ACTIONMOVEBEFORE:
			
			break;
		case TurnKeeper.STAGE_CHOOSE_CHARMOVE:
			break;
		case TurnKeeper.STAGE_CHOOSE_ACTIONMOVEAFTER:
			break;
		default:
			break;
		}
	}
}
