package Bogen;

public class Formeln {
	/**
	 * Berechnet aus gegebener Bogenlänge und dem dazu passenden Radius
	 * den Mittelpunkswinkel des dazugehörnden Kreisbogens.
	 * 
	 * @param bogenLänge Die Länge des Bogens in einer beliebigen Längeneinheit.
	 * @param radius Der Radius des Kreises auf dem der Bogen liegt.
	 * @return Mittelpunkswinkel zwischen Bogenanfang und Bogenende.
	 */
	public static float mittelpunktsWinkel_GRAD(float bogenLänge,float radius){
		return (float) ((360*bogenLänge)/(2*Math.PI*radius));
	}
	
	/**
	 * Berechnet aus bekanntem Radius und dem Mittelpunktswinkel zwischen
	 * dem Anfang und dem Ende des Kreisbogens das Ordinatenmaß (die
	 * Abweichung von der Geraden zu dem Punkt am Bogenende).
	 * 
	 * @param radius Radius des Bogens.
	 * @param mittelpunktsWinkel Winkel zwischen Bogenanfang und Bogenende.
	 * @return Ordinatenmaß.
	 */
	public static float ordinatenmaß (float radius, float mittelpunktsWinkel){
		float mittelpunktsWinkelInRad=(float) (((90-mittelpunktsWinkel)*Math.PI))/180;
		return (float) (radius-radius*Math.sin(mittelpunktsWinkelInRad));
	}
	
	/**
	 * <p>Anwendung des Strahlensatzes.</p>
	 * 
	 * Eine Sehen die in der geraden beginnt und in einem Vollbogen endet,
	 * wird an ihrem ende um das Ordinatenmaß y höher liegen als am Anfang.<p>
	 * 
	 * Diese Methode liefert den Höhenunterschied am Sehenteilungspunkt.
	 * 
	 * @param a Länge des ersten Sehenabschnittes von rechts aus gesehen.
	 * @param b	Länge des zweiten Sehenabschnittes von rechts aus gesehen.
	 * @param ordinatenmaß Höhenunterschied zwischen Sehenanfang und Ende.
	 * @return 
	 */
	public static float pfeilhöheAusOrdinatenmaß(float a,float b,float ordinatenmaß){
		return ((a*ordinatenmaß)/(a+b));
	}
}
