package editor;

/** 
 * Enum type that represent tiles in the editor/game. 
 */
public enum TileType {

	BLUESTONE(1), GREYSTONE(2), REDBRICK(3), WOOD(4), PLAYER(5);
	
	private int value;
	
	TileType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	/**
	 * Returns TileType from passed value.
	 */
	public static TileType getTypeFromValue(int value) {
		switch (value) {
		case 1:  return BLUESTONE;
		case 2:  return GREYSTONE;
		case 3:  return REDBRICK;
		case 4:  return WOOD;
		case 5:  return PLAYER;
		default: return null;
		}
	}
	
	@Override
	public String toString() {
		switch (value) {
		case 1: return "bluestone";
		case 2: return "greystone";
		case 3: return "redbrick";
		case 4: return "wood";
		case 5: return "player";
		default: return "null type";
		}
	}
}
