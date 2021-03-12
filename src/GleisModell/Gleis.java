package GleisModell;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse modelliert ein Gleis und stellt methoden zur Aswertung der
 * Parameter der inneren Gleisgeometrie zur Verfügung.
 * 
 * @author Berthold
 *
 */

public class Gleis {
	private String name;
	private int gleisNummer;
	private double wegschritt_m;
	private double laenge_m;

	private List<Double> laengshoeheLinkeSchiene = new ArrayList<Double>();
	private List<Double> laengshoeheRechteSchiene = new ArrayList<Double>();

	/**
	 * Ein neues Gleis.
	 * 
	 * Es wird ein neues Gleis erzeugt. Für die linke und rechte Schiene werden
	 * entsprechend der Längenvorgabe und dem Wegschritt (Abstand zwischen den
	 * Messpunkten) Messpunkte erzeugt. Die Längshöhe der beiden Schienen wird mit
	 * dem Wert null initialisiert. Das heist: Das Gleis hat keine Längshöhenfehler,
	 * Fehler in der Gegenseitigen Höhenlage und damit auch keine Verwindungsfehler.
	 * 
	 * @param name         Gleisname.
	 * @param nummer       Nummer des Gleises
	 * @param wegschritt_m Abstand der Messpunkte in [m].
	 * @param laenge_m     Länge des Gleises in [m].
	 */
	public Gleis(String name, int nummer, double wegschritt_m, double laenge_m) {
		this.name = name;
		this.gleisNummer = nummer;
		this.wegschritt_m = wegschritt_m;
		this.laenge_m = laenge_m;
		this.erzeugeLaengshoeheLinkeSchiene_m();
		this.erzeugeLaengshoeheRechteSchiene_m();
	}

	/**
	 * Erzeugt einen ebenen, in Richtung der aufsteigenden Kilometrierung geschaut
	 * linken Schienenstrang des Gleises ohne Längshöhenfehler oder löscht eventuell
	 * bereits vorhandene Werte.
	 */
	public void erzeugeLaengshoeheLinkeSchiene_m() {
		int anzahlMesspunkte = this.getAnzahlMesspunkte();
		for (int n = 0; n <= anzahlMesspunkte; n++)
			this.laengshoeheLinkeSchiene.add(0.0);
	}

	/**
	 * Im Sinne der aufsteigenden Kilometrierung linker Schienenstrang.
	 * 
	 * @return Längshöhen des linken Schienenstranges.
	 */
	public List<Double> getLaengshoeheLinkeSchiene() {
		return this.laengshoeheLinkeSchiene;
	}

	/**
	 * Erzeugt einen ebenen, in Richtung der aufsteigenden Kilometrierung geschaut
	 * rechten Schienenstrang des Gleises ohne Längshöhenfehler oder löscht
	 * eventuell bereits vorhandene Werte.
	 */
	public void erzeugeLaengshoeheRechteSchiene_m() {
		int anzahlMesspunkte = this.getAnzahlMesspunkte();
		for (int n = 0; n <= anzahlMesspunkte; n++)
			this.laengshoeheRechteSchiene.add(0.0);
	}

	/**
	 * Im Sinne der aufsteigenden Kilometrierung rechter Schienenstrang.
	 * 
	 * @return Längshöhen des rechten Schienenstranges.
	 */
	public List<Double> getLaengshoeheRechteSchiene() {
		return this.laengshoeheRechteSchiene;
	}

	/**
	 * Messpunkte.
	 * 
	 * @return Anzahl der Messpunkte für dieses Gleis. Ergibt sich aus der Länge in
	 *         Metern und dem Abstand zwischen den Messpunkten. Der Wert wird auf
	 *         eine ganze Zahl gerundet.
	 */
	public int getAnzahlMesspunkte() {
		return (int) (this.laenge_m / this.wegschritt_m);
	}

	/**
	 * Wegschritt.
	 * 
	 * @return Abstand zwischen den Messpunkten in [m].
	 */
	public double getWegschritt_m() {
		return this.wegschritt_m;
	}

	/**
	 * Erzeugt eine sinusförmige Welle in der linken Schiene über die gesammte Länge
	 * des Gleises. Besthende Werte werden überschrieben.
	 * 
	 * Die Wellenlänge beträgt 2 x 3.14... m (also 2 x Pi)
	 * 
	 * @param phasenVerschiebung_m Verschiebung des Nullpunktes der Welle.
	 * @param amplitute_m          Maximale Auslenkung der Welle in [m].
	 */
	public void erzeugeWelleLinkeSchiene(double phasenVerschiebung_m, double amplitute_m) {
		double x = phasenVerschiebung_m;

		for (int n = 0; n < laengshoeheLinkeSchiene.size(); n++)
			this.laengshoeheLinkeSchiene.set(n, amplitute_m * Math.sin(x++));
	}

	/**
	 * Erzeugt eine sinusförmige Welle in der rechten Schiene über die gesammte
	 * Länge des Gleises. Besthende Werte werden überschrieben.
	 * 
	 * @param phasenVerschiebung_m Verschiebung des Nullpunktes der Welle.
	 * @param amplitute_m          Maximale Auslenkung der Welle in [m].
	 */
	public void erzeugeWelleRechteSchiene(double phasenVerschiebung_m, double amplitute_m) {
		double x = phasenVerschiebung_m;

		for (int n = 0; n < laengshoeheRechteSchiene.size(); n++)
			this.laengshoeheRechteSchiene.set(n, amplitute_m * Math.sin(x++));
	}

	/**
	 * Gegenseitige Höhenlage an einem beliebigen Punkt im Gleis.
	 * 
	 * @param imMeterVonGleisanfang_m Stationierung vom Beginn des Gleises aus
	 *                                gesehen. Genauigkeit: +/- wegschritt.
	 * 
	 * @return Gegenseitige Höhenlage. Die Linke Schiene ist der Bezugsstrang, der
	 *         das Vorzeichen festlegt. - heist: links tiefer als rechts. + heist:
	 *         links höher als rechts.
	 */
	public double getGH_m(double imMeterVonGleisanfang_m) {
		int messpunkt = (int) (imMeterVonGleisanfang_m / this.wegschritt_m);
		double linkerWert_m = this.laengshoeheLinkeSchiene.get(messpunkt);
		double rechterWert_m = this.laengshoeheRechteSchiene.get(messpunkt);
		return -1 * (rechterWert_m - linkerWert_m);
	}

	/**
	 * Verwindung an einer beliebigen Stelle, mit einer beliebigen Messbasislänge.
	 * 
	 * @param imMeterVomGleisanfang_m Messpunkt. Der Zweite Messpunkt liegt um die
	 *                                Länge der Messbasis, entgegen der
	 *                                aufsteigenden Kilometrierung, vor diesem
	 *                                Messpunkt.
	 * 
	 * @param laengeMessbasis_m       Zugrundeliegende Messbasis.
	 * @return Verwindung in Promille.
	 */
	public double getVerwindung_P(double imMeterVomGleisanfang_m, double laengeMessbasis_m) {
		return Math
				.abs((this.getGH_m(imMeterVomGleisanfang_m) - this.getGH_m(imMeterVomGleisanfang_m - laengeMessbasis_m))
						/ laengeMessbasis_m);
	}
}
