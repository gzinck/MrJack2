package model.token;

public interface Token {
	public int[] getTokenLocation();
	public int[] getPrevTokenLocation();
	public int getTokenType();
	public void initializeObservers();
}
