package Allgemeines;

/**
 * Koordinatenpaar.
 * 
 * @author Berthold
 *
 */
public class KoordinatenPaar {
	public String BezeichnungX,BezeichnungY;
	public float x,y;
	
	/**
	 * Ein Koordinatenpaar in einem rechtwinkeligen Koordinatensystem.
	 * 
	 * @param BezeichnungX	Bezeichnung des Abzissenwertes.
	 * @param x		Wert.
	 * @param BezeichnungY	Bezeichnung des Ordinatenwertes.
	 * @param y		Wert.
	 */
	public KoordinatenPaar(String BezeichnungX, float x,String BezeichnungY, float y) {
		super();
		this.BezeichnungX = BezeichnungX;
		this.x = x;
		this.BezeichnungY=BezeichnungY;
		this.y = y;
	}

	/**
	 * @return Bezeichnung des Abzissenwertes.
	 */
	public String getBezeichnungX() {
		return BezeichnungX;
	}

	/**
	 * @return Wert auf der X- Achse.
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return Wert auf der Y- Achse.
	 */
	public float getY() {
		return y;
	}

	/**
	 * @return Bezeichnung des Ordinatenwertes.
	 */
	public String getBezeichnungY() {
		return BezeichnungY;
	}
	
}
