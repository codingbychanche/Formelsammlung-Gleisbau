package Gradiente;

import java.util.ArrayList;
import java.util.List;

import Allgemeines.Formel;
import Allgemeines.KoordinatenPaar;

/**
 * Definiert einen Neigungswechsel und stellt Methoden zu seiner Berechnung zur
 * Verfügung.
 * 
 * @author Berthold
 *
 */
public class Neigungswechsel {

	float km_km, s1_P, s2_P, ra_m;

	/**
	 * Neigungswechsel.
	 * 
	 * Die folgenden Parameter beschreiben den Neigumngswechsel. Alle anderen
	 * Parameter können daraus berechnet werden.
	 * 
	 * @param km_km Stationierung des Tangentenschnittpunktes.
	 * @param s1_P  Ankommendes Gefälle auf den Neigungswechsel zu.
	 * @param s2_P  Abgehendes Gefälle.
	 * @param ra_m  Ausrundungsbogen Halbmesser.
	 */
	public Neigungswechsel(float km_km, float s1_P, float s2_P, float ra_m) {
		this.km_km = km_km;
		this.s1_P = s1_P;
		this.s2_P = s2_P;
		this.ra_m = ra_m;
	}

	/**
	 * @return Ausrundungsbogen Halbmesser in Metern aus der gegebenen Geometrie.
	 */
	public float getRa_m() {
		return ra_m;
	}

	/**
	 * Berechnet den erforderlichen mindes- Ausrundungsbogen Halbmesser in Metern
	 * aus der gegebenen Geschwindigkeit gemäß <i>DB- AG, RIL 800.0110</i>.
	 * 
	 * @param v_kmh Maßgebende Geschwindigkeit.
	 * @return Ausrundungsbogenhalbmesser r<sub>a</sub> in Metern.
	 */
	public float getRaAusV_m(float v_kmh) {
		return 0.4f * (v_kmh * v_kmh);
	}

	/**
	 * @return Formel zur Berechnung von R<sub>a</sub> im HTML Format.
	 */
	public Formel getFormelRaHtml() {
		return new Formel("r<sub>a</sub>=0.4&sdotV<sup>2</sup>", "<i>DB AG, RIL 800.0110</i>");
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
	 * @return Formel zur Berechnung von l<sub>t</sub> im HTML- Format.
	 */
	public Formel getFormelLtHtml() {
		return new Formel("l<sub>t</sub>=(r<sub>a</sub>/2000)&sdot&deltas", "-");
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
	 * @return Formel zur Berechnung des Scheitelpunktsabstandsmaßes a
	 */
	public Formel getFormelAHtml() {
		return new Formel("a=l<sub>t</sub><sup>2</sup>/2&sdotr<sub>a</sub>", "-");
	}

	/**
	 * Beliebiger Ordinatenwert.
	 * 
	 * @param xn_m Abszissemwert für den gesuchten Ordinatenwert in [m].
	 * @return Ordinatenwert in [m].
	 */
	public float getYn_m(float xn_m) {
		return (xn_m * xn_m) / (2 * this.ra_m);
	}

	/**
	 * @return Formel zur Berechnung eines beliebigen Ordinatenwertes y<sub>n>/sub>
	 *         zwischen Ausrundungsbogenanfang (AA) und dem Tangentenschnittpunkt am
	 *         Neigungswechsel (NW).
	 */
	public Formel getFormelYn() {
		return new Formel("y<sub>n</sub>=x<sup>2</sup>/(2&sdot)l<sub>t</sub>", "-");
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
	public int getAnzahlFuenfMeterAbschnitte_m(float x1_m) {
		return (int) (getLt_m() - x1_m) / 5;
	}

	/**
	 * Liefert den Abstand x<sub>al</sub> vom Ausrundungsbogenanfang (AA) bis zum
	 * ersten Ordinatenwert auf dem 5- Meter Raster.
	 * 
	 * @return Abstand AA bis zum ersten Ordinatenwert auf dem 5-Meter Raster in [m]
	 */
	public float getAbstandAABisErsteOrdinate_m() {
		float kmAA_m = ((getKm_km() * 1000) - getLt_m());
		float kmAAGerundet_m = (int) (kmAA_m / 5) * 5;
		return 5 - (kmAA_m - kmAAGerundet_m);
	}

	/**
	 * Liefert den Abstand x<sub>el</sub> vom Tangentenschnittpunkt (NW) bis zum
	 * letzten Ordinatenwert auf der Linken Seite des NW.
	 * 
	 * @return Abstand NW bis zum letzten Ordinatenwert links vom NW.
	 */
	public float getAbstandNWBisLetzteOrdinateLinks() {
		float kmNW_m = getKm_km() * 1000;
		float kmNWGerundet_m = (int) (kmNW_m / 5) * 5;
		return kmNW_m - kmNWGerundet_m;
	}

	/**
	 * Liefert den Abstand x<sub>ar</sub> vom Ausrundungsbogenende (AE) bis zum
	 * ersten Ordinatenwert in [m].
	 * 
	 * @return Abstand AE bis zum ersten Ordinatenwert.
	 */
	public float getAbstandAEBisErsteOrdinate_m() {
		float kmAE_m = (getKm_km() * 1000) + getLt_m();
		float kmAEGerundet_m = (int) (kmAE_m / 5) * 5;
		return kmAE_m - kmAEGerundet_m;
	}

	/**
	 * Liefert den Abstand x<sub>er</sub> vom Tangentenschnittpukt (NW) bis zum
	 * letzte Ordinatenwert auf der rechten Seite des NW in [m].
	 * 
	 * @return Abstand NW bis zum letzten Ordinatenwert rechts vom NW.
	 */
	public float getAbstandNWLetzteOrdinateRechts_m() {
		float kmNW_m = getKm_km() * 1000;
		float kmNWGerundet_m = (int) (kmNW_m / 5) * 5;
		return 5 - (kmNW_m - kmNWGerundet_m);
	}

	/**
	 * Berechnet alle Ordinatenwerte vom Ausrundungsbogen- Anfang (AA) aus in
	 * Richtung der aufsteigenden Kilometrierung bis zum Tangentenschnittpunkt am
	 * Neigungswechsel (NW) und vom Ausrundungsbogenende in absteigender
	 * Kilometrierung in Richtung des Tangentenschnittpunktes.
	 * <p>
	 * 
	 * Es müssen jeweils der Abstand von AA bis zum ersten gesuchten Ordinatenwert
	 * auf dem 5- Meter Raster und der Abstand vom AE bis zum letzten Ordinatenwert
	 * auf dem 5- Meter Raster übergeben werden.
	 * <p>
	 * 
	 * Die Abstände von den jeweils letzten Ordinatenwerten vor dem
	 * Tangendenschnittpunkt werden aus den gegebenen Werten berechnet.
	 * 
	 * @return Die errechneten Ordinatenwerte.
	 */
	public List<KoordinatenPaar> getYn_m() {
		float x1_m = getAbstandAABisErsteOrdinate_m();
		float x11_m = getAbstandAEBisErsteOrdinate_m();

		List<KoordinatenPaar> ynResultList_m = new ArrayList();

		// Wir beginnen bei AA
		ynResultList_m.add(new KoordinatenPaar("AA x", 0, "y", 0));

		// Erster Ordinatenwert nach AA
		float xn_m = x1_m;

		// Abstand vom letzten Ordinatenwert bis zum Tangendenschnittpunkt.
		int fuenfMeterAbschnitte = getAnzahlFuenfMeterAbschnitte_m(x1_m);
		float xe_m = getLt_m() - (x1_m + 5 * fuenfMeterAbschnitte);

		float yn_m = getYn_m(xn_m);
		ynResultList_m.add(new KoordinatenPaar("x", xn_m, "y", yn_m));

		// Folgende Ordinatenwerte im 5- Meter Raster Abstand
		for (int n = 0; n < fuenfMeterAbschnitte; n++) {
			xn_m = xn_m + 5;
			ynResultList_m.add(new KoordinatenPaar("x" + n, xn_m, "y", getYn_m(xn_m)));
		}

		// Jetzt a
		xn_m = xn_m + xe_m;
		ynResultList_m.add(new KoordinatenPaar("xa", xn_m, "a", getA_m()));

		// Nun der zweite Parabelast vom AE in Richtung der
		// absteigenden Kilometrierung zum Tangentenschnittpunkt hin.

		// Wir beginnen bei AE
		ynResultList_m.add(new KoordinatenPaar("AE", 0, "y", 0));

		// Erster Ordinatenwert nach AE
		xn_m = x11_m;

		fuenfMeterAbschnitte = getAnzahlFuenfMeterAbschnitte_m(x11_m);
		float xee_m = getLt_m() - (x11_m + 5 * fuenfMeterAbschnitte);

		// Erster Ordinatenwert nach den Tangendenschnittpunkt..
		ynResultList_m.add(new KoordinatenPaar("x", xn_m, "y", getYn_m(xn_m)));

		// Folgende Ordinatenwerte im 5- Meter Raster Abstand
		for (int n = 0; n < fuenfMeterAbschnitte; n++) {
			xn_m = xn_m + 5;
			ynResultList_m.add(new KoordinatenPaar("x" + n, xn_m, "y", getYn_m(xn_m)));
		}

		// Jetzt a
		xn_m = xn_m + xee_m;
		ynResultList_m.add(new KoordinatenPaar("xa", xn_m, "a", getA_m()));

		// Ergebnis...
		return ynResultList_m;
	}

	/**
	 * @return Genaue Stationierung des Neigungswechsels am Tangentenschnittpunkt.
	 */
	public float getKm_km() {
		return km_km;
	}

	/**
	 * @return Am Neigungswechsel, aus Richtung der fallenden Kilometrierung,
	 *         ankommendes Gefälle in Promille.
	 */
	public float get_s1_P() {
		return s1_P;
	}

	/**
	 * @return Vom Neigungswechsel, in Richtung der Aufsteigenden Kilometrierung,
	 *         abgehendes Gefälle in Promille.
	 */
	public float getS2_P() {
		return s2_P;
	}
}
