package view.board;

public interface TokenViewModifier {
	public void moveToken(int tokenIndex, int[] prevLocation, int[] newLocation);
}
