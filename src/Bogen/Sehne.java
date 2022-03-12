package Bogen;

/**
 * Definiert eine Sehne.
 * 
 * @author Berthold
 *
 */
public class Sehne {
	
	private float a, b;

	public Sehne(float a, float b) {
		this.a = a;
		this.b = b;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}
	
	public float getLength(){
		return a+b;
	}
}
