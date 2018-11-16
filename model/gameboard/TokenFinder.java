package model.gameboard;

import model.token.*;
import model.tile.*;

/**
 * This finds tokens at specified locations, or finds all tokens
 * of a specialized type.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface TokenFinder {
	/**
	 * Gets the manhole cover at the given (row, col) location.
	 * 
	 * @param location the (row, col) of the desired manhole cover
	 * @return the manhole cover found at the location
	 */
	public ManholeCover getManholeCover(int[] location);
	/**
	 * Gets the barricade at the given location.
	 * 
	 * @param location the (row, col) of the desired barricade.
	 * @return the barricade found at the location
	 */
	public Barricade getBarricade(int[] location);
	/**
	 * Gets the gaslight at the given location.
	 * 
	 * @param location the (row, col) of the desired gaslight
	 * @return the gaslight found at the location
	 */
	public GasLight getGasLight(int[] location);
	/**
	 * Gets the character token at the given location.
	 * @param location the (row, col) of the desired character.
	 * @return the character at the location
	 */
	public CharacterToken getCharacter(int[] location);
	
	/**
	 * Gets all the manhole covers.
	 * @return all manhole covers
	 */
	public ManholeCover[] getManholeCovers();
	/**
	 * Gets all the barricades.
	 * @return all barricades
	 */
	public Barricade[] getBarricades();
	/**
	 * Gets all gaslights.
	 * @return all gaslights
	 */
	public GasLight[] getGasLights();
	/**
	 * Gets all characters.
	 * @return all character tokens
	 */
	public CharacterToken[] getCharacters();
	
	/**
	 * Gets the manhole at a given location.
	 * 
	 * @param location the (row, col) of the manhole
	 * @return the manhole
	 */
	public Manhole getManhole(int[] location);
	/**
	 * Gets the exit at a given location.
	 * 
	 * @param location the (row, col) of the exit
	 * @return the exit
	 */
	public Exit getExit(int[] location);
	/**
	 * Gets the lamp at a given location
	 * 
	 * @param location the (row, col) of the lamppost
	 * @return the lamppost
	 */
	public Lamppost getLamp(int[] location);
	/**
	 * Gets the lightable tile at the given location.
	 * 
	 * @param location the (row, col) of the lightable tile
	 * @return the lightable tile
	 */
	public Lightable getTile(int[] location);
	
	/**
	 * Gets all manholes.
	 * 
	 * @return all manholes
	 */
	public Manhole[] getManholes();
	/**
	 * Gets all exits.
	 * 
	 * @return all exits
	 */
	public Exit[] getExits();
	/**
	 * Gets all lampposts.
	 * 
	 * @return all lampposts
	 */
	public Lamppost[] getLamps();
	/**
	 * Gets the location of the neighbour of a tile in a given direction
	 * from a specified row and column. This is nontrivial because there
	 * are six neighbours to a hexagonal tile, and the locations of those
	 * neighbours depends on whether the column is even or odd.
	 *  
	 * @param row the row of the original tile
	 * @param col the column of the original tile
	 * @param direction the direction to find the neighbouring tile
	 * @return the {row, col} of the neighbour, if it exists, or null
	 * if it does not exist.
	 */
	public int[] getLocation(int row, int col, int direction);
}
