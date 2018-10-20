package controller;

import model.gameboard.GameBoard;
import model.player.*;
import model.table.Table;
import model.turnkeeper.TurnKeeper;

public class GameController {
	private GameBoard gb;
	private MrJack jack;
	private Detective detective;
	private TurnKeeper turnKeeper;
	private Table table;
	
	public void initializeGame()  {
		// First, we need to instantiate everything.
		gb = new GameBoard();
		jack = new MrJack();
		detective = new Detective();
		turnKeeper = new TurnKeeper(jack, detective, gb.getRemovableGaslights());
		table = new Table();
		table.startRound();
	}
	public void continueGame() {
		// This runs through a turn
		if(turnKeeper.roundOver()) {
			table.startRound();
		}
	}
	public void updateTurn() {
		
	}
	public boolean gameIsOver() {
		return false;
	}
}
