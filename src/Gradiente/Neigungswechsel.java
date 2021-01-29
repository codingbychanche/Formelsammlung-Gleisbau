package Gradiente;

import java.util.ArrayList;
import java.util.List;

/**
 * Definiert einen Neigungswechsel und stellt Methoden zur Berechnung zur
 * Verfügung.
 * 
 * @author Berthold
 *
 */
public class Neigungswechsel {

	float km_km, s1_P, s2_P, ra_m;

	/**
	 * Neigungswechsel
	 * 
	 * @param km_km	Stationierung des Tangentenschnittpunktes.
	 * @param s1_P	Ankommendes Gefälle auf den Neigungswechsel zu.
	 * @param s2_P	Abgehendes Gefälle.
	 * @param ra_m	Ausrundungsbogen Halbmesser.
	 */
	public Neigungswechsel(float km_km, float s1_P, float s2_P, float ra_m) {
		this.km_km = km_km;
		this.s1_P = s1_P;
		this.s2_P = s2_P;
		this.ra_m = ra_m;
	}

	/**
	 * Tangentenlänge.
	 * 
	 * @return Tangentenlänge in Metern.
	 */
	public float getLt_m() {
		return (ra_m / 2000) * Math.abs(s1_P - s2_P);
	}

	/**
	 * Scheitelpunkts Abstandsmaß a
	 * 
	 * @return a in m
	 */
	public float getA_m() {
		return getLt_m() * getLt_m() / (2 * ra_m);
	}

	/**
	 * Beliebiger Ordinatenwert.
	 * 
	 * @param xn_m Abszissemwert für den gesuchten Ordinatenwert in metern.
	 * @return Ordinatenwert in metern.
	 */
	public float getYn_m(float xn_m) {
		return (xn_m * xn_m) / (2 * this.ra_m);
	}

	/**
	 * Berechnet wieviele volle 5- Meter Abschnitte zwischen Ausrundungsbogenanfang
	 * (AA) oder Ausrundungbogenende (AE) und dem Tangentenschnittpunkt liegen.
	 * 
	 * @param x1_m Abstand des ersten Ordinatenwertes auf dem fünf- Meter Raster zum
	 *             AA oder AE
	 * 
	 * @return Anzahl der vollen fünf Meter Abschnitte zwischen dem ersten und dem
	 *         letzten Ordinatenwert zwischen AA und NW.
	 */
	public int getAnzahlFuenfMeterAbschnitte(float x1_m) {
		return (int) (getLt_m() - x1_m) / 5;

	}

	/**
	 * Berechnet alle Ordinatenwerte vom Ausrundungsbogen- Anfang (AA) aus in
	 * Richtung der Auftseigenden Kilometrierung bis zum Tangentenschnittpunkt
	 * am Neigungswechsel (NW) und vom Ausrundungsbogenende in absteigender
	 * Kilometrierung in Richtung des Tangentenschnittpunktes.
	 * 
	 * Es müssen jeweils der Abstand von AA bis zum ersten gesuchten Ordinatenwert
	 * auf dem 5- Meter Raster und der Abstand vom AE bis zum letzten Ordinatenwert
	 * auf dem 5- Meter Raster übergeben werden.
	 * 
	 * Die Abstände von den jeweils letzten Ordinatenwerten vor dem
	 * Tangendenschnittpunkt werden aus den gegebenen Werten berechnet.
	 * 
	 * @param x1_m  Abstand vom AA bis zum ersten Ordinatenwert.
	 * @param x11_m Abstand vom AE bis zum letzten Ordinatenwert.
	 * @return Die errechneten Ordinatenwerte.
	 */
	public List<Ordinatenwert> getYn_m(float x1_m, float x11_m) {
		List<Ordinatenwert> ynResultList_m = new ArrayList();

		// Wir beginnen bei AA
		ynResultList_m.add(new Ordinatenwert("AA", 0, 0));

		// Erster Ordinatenwert nach AA
		float xn_m = x1_m;

		// Abstand vom letzten Ordinatenwert bis zum Tangendenschnittpunkt.
		int fuenfMeterAbschnitte = getAnzahlFuenfMeterAbschnitte(x1_m);
		float xe_m = getLt_m() - (x1_m + 5 * fuenfMeterAbschnitte);

		float yn_m = getYn_m(xn_m);
		ynResultList_m.add(new Ordinatenwert("x", xn_m, yn_m));

		// Folgende Ordinatenwerte im 5- Meter Raster Abstand
		for (int n = 0; n < fuenfMeterAbschnitte; n++) {
			xn_m = xn_m + 5;
			ynResultList_m.add(new Ordinatenwert("x" + n, xn_m, getYn_m(xn_m)));
		}

		// Jetzt a
		xn_m = xn_m + xe_m;
		ynResultList_m.add(new Ordinatenwert("a", xn_m, getA_m()));

		// Nun der zweite Parabelast vom AE in Richtung der
		// absteigenden Kilometrierung zum Tangentenschnittpunkt hin.

		// Wir beginnen bei AE
		ynResultList_m.add(new Ordinatenwert("AE", 0, 0));

		// Erster Ordinatenwert nach AE
		xn_m = x11_m;

		fuenfMeterAbschnitte = getAnzahlFuenfMeterAbschnitte(x11_m);
		float xee_m = getLt_m() - (x11_m + 5 * fuenfMeterAbschnitte);

		// Erster Ordinatenwert nach den Tangendenschnittpunkt..
		ynResultList_m.add(new Ordinatenwert("x", xn_m, getYn_m(xn_m)));

		// Folgende Ordinatenwerte im 5- Meter Raster Abstand
		for (int n = 0; n < fuenfMeterAbschnitte; n++) {
			xn_m = xn_m + 5;
			ynResultList_m.add(new Ordinatenwert("x" + n, xn_m, getYn_m(xn_m)));
		}

		// Jetzt a
		xn_m = xn_m + xee_m;
		ynResultList_m.add(new Ordinatenwert("a", xn_m, getA_m()));

		// Ergebnis...
		return ynResultList_m;
	}
}
