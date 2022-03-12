package Bogen;

/**
 * Daten Satz für eine Pfeilhöhe.
 * 
 * @author Berthold
 *
 */
public class Pfeilhöhe {
	float hf,x;
	
	/**
	 * Eine neue Pfeilhöhe definieren.
	 * 
	 * @param hf	Pfeilhöhenwert.
	 * @param x		Position reletiv zum Anfang der Messung.
	 * 
	 */
	public Pfeilhöhe(float x,float hf){
		this.hf=hf;
		this.x=x;
	}

	public float getHf() {
		return hf;
	}

	public void setHf(float hf) {
		this.hf = hf;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
}
