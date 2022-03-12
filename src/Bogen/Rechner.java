package Bogen;

/**
 * Rechenroutinen.
 * 
 * @author Berthold
 *
 */
public class Rechner {

	/**
	 * Berechnet die Pfeilhöhenwerte eine beliebigen Sehen im Übergang zwischen
	 * einer Geraden und einem Vollbogen.
	 * 
	 * @param sehne Eine Instanz von {@link Sehne}
	 * @param radius Der Radius des Vollbogens.
	 * @param wegschritt Messpunktraster Abstand.
	 * @return
	 */
	public static PfeilhöhenTabelle geradeVollbogen (Sehne sehne,float radius,float wegschritt){
		float sehnenlänge=sehne.getLength();
		float a=sehne.getA();
		float b=sehne.getB();
		float hf=0;
		float mittelpunktsWinkelAmTP=0;
		
		PfeilhöhenTabelle hfTabelle=new PfeilhöhenTabelle();
		
		//
		// Berechnet die Pfeilhöhe hf am Sehenteilungspunkt.
		// Das Sehenende liegt als Anfangsbedingung genau am Bogenanfang=x=0.
		//
		for (float x=0;x<=sehnenlänge+10;x=x+wegschritt){
			
			//
			// Mittelpunkswinkel zwischen Bogenanfang und aktuell zurückgelegtem Weg x
			// auf dem Bogen, am Sehnenende.
			//
			float mittelpunktsWinkelAmSe =Formeln.mittelpunktsWinkel_GRAD(x, radius);
			
			// Ordinate am Sehnenende
			float ySe=Formeln.ordinatenmaß(radius, mittelpunktsWinkelAmSe); 
			
			//
			// Fall 1: Der Sehenteilungspunkt liegt vor dem Bogenanfang.
			//
			if (x<=b){				
				// Nun noch die Pfeilhöhe am Sehenteilungspunkt.
				hf=Formeln.pfeilhöheAusOrdinatenmaß(a, b, ySe);
			}
			
			//
			// Fall 2: Der Sehnenteilungspunkt ist im Bogen, der Sehenenanfang aber noch vor Bogenanfang.
			//
			if (x>b){
				mittelpunktsWinkelAmTP=Formeln.mittelpunktsWinkel_GRAD((x-b), radius);
				float ordinatenmaßAnTP=Formeln.ordinatenmaß(radius, mittelpunktsWinkelAmTP);
				float pfeilhöheAmTPBisGerade=Formeln.pfeilhöheAusOrdinatenmaß(a, b, ySe);
				hf=pfeilhöheAmTPBisGerade-ordinatenmaßAnTP;
			}
			
			//
			// Fall 3: Die gesammte Sehne ist im Vollbogen
			//
			if (x>=sehnenlänge)
				hf=(a*b/(2*radius));
			
			//
			// Result
			//
			Pfeilhöhe p=new Pfeilhöhe(x-b,hf);
			hfTabelle.add(p);
		}
		return hfTabelle;
	}
}
