package hu.bme.annaATbarbies.sokoban.model;
/*
 * A feluletek tisztasagat tarolo enum.
 * A szamok a tolas nehezseget jelentik.
 */
public enum SurfaceContamination {
	NONE(4), HONEY(5), OIL(3);
	
	private int val;
	
	private SurfaceContamination(int val) {
		this.val = val;
	}
	public int getValue() {
		return val;
	}
}
