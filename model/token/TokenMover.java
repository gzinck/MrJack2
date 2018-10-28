package model.token;

import java.util.Arrays;

import model.ability.Ability;

public class TokenMover {
	private int[][] tokenLocationOptions, tileLocationOptions;
	private int[] selectedToken, selectedTile;
	
	private Ability currAbility;
	// Given a tile location, we want to identify the token clicked on.
	// With this token, we want to get the tiles that are accessible and
	// highlight those. Returns false if there are no options, given the
	// ability and the token chosen.
	public int[][] getTokenOptions(Ability ability) {
		currAbility = ability;
		tokenLocationOptions = ability.getAbilityTokenOptions();
		return tokenLocationOptions;
	}
	public boolean selectToken(int[] location) {
		// Must see if it exists in the tokenLocationOptions
		for(int i = 0; i < tokenLocationOptions.length; i++) {
			boolean isEqual = true;
			for(int j = 0 ; j < location.length; j++) if(tokenLocationOptions[i][j] != location[j]) isEqual = false;
			if(isEqual) {
				selectedToken = location;
				return true;
			}
		}
		return false;
	}
	public int[][] getTileOptions() {
		tileLocationOptions = currAbility.getAbilityTileOptions();
		for(int i = 0; i < tileLocationOptions.length; i++) {
			System.out.println(Arrays.toString(tileLocationOptions[i]));
		}
		return tileLocationOptions;
	}
	public boolean selectTile(int[] location) {
		// Must see if it exists in the tokenLocationOptions
		for(int i = 0; i < tileLocationOptions.length; i++) {
			boolean isEqual = true;
			for(int j = 0 ; j < location.length; j++) if(tileLocationOptions[i][j] != location[j]) isEqual = false;
			if(isEqual) {
				selectedTile = location;
				return true;
			}
		}
		return false;
	}
	public void performMove() {
		currAbility.performAbility(selectedToken, selectedTile);
		// Reset variables so that logic still works
		currAbility = null;
		tokenLocationOptions = null;
		tileLocationOptions = null;
		selectedToken = null;
		selectedTile = null;
	}
	public boolean tokenSelected() {
		return (selectedToken != null);
	}
	public boolean tileSelected() {
		return (selectedTile != null);
	}
}
