package view.board;

public interface CharTokenViewModifier {
	public void moveCharacter(int charIndex, int[] prevLocation, int[] newLocation);
	public void setInnocence(int[] location, boolean witnessed);
}
