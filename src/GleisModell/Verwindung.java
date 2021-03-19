package GleisModell;

/**
 * Diese Klasse stellt methoden zur Auswertung der Gleisverwindung zur
 * Verfügung.
 * 
 * @author Berthold
 *
 */

public class Verwindung {

	/**
	 * Gegenseitige Höhenlage an einem beliebigen Punkt im Gleis.
	 * 
	 * @param imMeterVonGleisanfang_m Stationierung vom Beginn des Gleises aus
	 *                                gesehen. Genauigkeit: +/- wegschritt.
	 * 
	 * @return Gegenseitige Höhenlage. Die Linke Schiene ist der Bezugsstrang, der
	 *         das Vorzeichen festlegt. - heist: links tiefer als rechts. + heist:
	 *         links höher als rechts.
	 * 
	 * @throws Index out of bounds wenn der Messpunkt ausserhalb dieses
	 *               Gleisabschnittes liegt.
	 * 
	 */
	public static double getGH_m(Gleis gleis, double imMeterVonGleisanfang_m) {
		int messpunkt = gleis.getMesspunkt_m(imMeterVonGleisanfang_m);
		double linkerWert_m = gleis.getLaengshoeheLinkeSchiene().get(messpunkt);
		double rechterWert_m = gleis.getLaengshoeheRechteSchiene().get(messpunkt);
		return -1 * (rechterWert_m - linkerWert_m);
	}

	/**
	 * Verwindung an einer beliebigen Stelle, mit einer beliebigen Messbasislänge in
	 * &permil;.
	 * 
	 * @param imMeterVomGleisanfang_m Messpunkt. Der Zweite Messpunkt liegt um die
	 *                                Länge der Messbasis, entgegen der
	 *                                aufsteigenden Kilometrierung, vor diesem
	 *                                Messpunkt.
	 * 
	 * @param laengeMessbasis_m       Zugrundeliegende Messbasis.
	 * @return Verwindung in Promille.
	 * 
	 * @throws Index out of bounds wenn der Messpunkt weniger als um die
	 *               Messbasislänge vom Nullpunkt (Anfang des Gleisabschnittes)
	 *               entfernt liegt.
	 */
	public static double getVerwindung_P(Gleis gleis, double imMeterVomGleisanfang_m, double laengeMessbasis_m) {
		return Math.abs(
				(getGH_m(gleis, imMeterVomGleisanfang_m) - getGH_m(gleis, imMeterVomGleisanfang_m - laengeMessbasis_m))
						/ laengeMessbasis_m);
	}

	/**
	 * Verwindung an einer beliebigen Stelle, mit einer beliebigen Messbasislänge in
	 * [mm].
	 * 
	 * @param imMeterVomGleisanfang_m Messpunkt. Der Zweite Messpunkt liegt um die
	 *                                Länge der Messbasis, entgegen der
	 *                                aufsteigenden Kilometrierung, vor diesem
	 *                                Messpunkt.
	 * 
	 * @param laengeMessbasis_m       Zugrundeliegende Messbasis.
	 * @return Verwindung in [mm]
	 * 
	 * @throws Index out of bounds wenn der Messpunkt weniger als um die
	 *               Messbasislänge vom Nullpunkt (Anfang des Gleisabschnittes)
	 *               entfernt liegt.
	 */
	public static double getVerwindung_mm(Gleis gleis, double imMeterVomGleisanfang_m, double laengeMessbasis_m) {
		return Math.abs((getGH_m(gleis, imMeterVomGleisanfang_m)
				- getGH_m(gleis, imMeterVomGleisanfang_m - laengeMessbasis_m)));
	}
}
