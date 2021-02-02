package Allgemeines;
/**
 * Allgemeine Beschreibung einer Formel.
 * 
 * @author Berthold
 *
 */
public class Formel {

	private String formelImKlartext;
	private String quellenAngabe;
	
	/**
	 * Erstellt eine neue Formel.
	 * 
	 * @param formelImKlartext	Die formel in Textform. Zum Beispiel: y=x^2-1
	 * @param quellenAngabe		Quelle. Zum Beispiel: "Siehe Wendhorst, 20 Auflage, Seite 456"
	 */
	public Formel(String formelImKlartext, String quellenAngabe) {
		super();
		this.formelImKlartext = formelImKlartext;
		this.quellenAngabe = quellenAngabe;
	}

	/**
	 * @return Die Formel im Klartext.
	 */
	public String getFormelImKlartext() {
		return formelImKlartext;
	}

	/**
	 * @return Die Quellenangabe.
	 */
	public String getQuellenAngabe() {
		return quellenAngabe;
	}
}
