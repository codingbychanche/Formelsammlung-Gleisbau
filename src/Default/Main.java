package Default;

import java.util.List;

import Gradiente.Neigungswechsel;
import Gradiente.Ordinatenwert;

public class Main {

	public static void main (String args []) {
		System.out.println("Demo");
		System.out.println("");
		
		Neigungswechsel nw=new Neigungswechsel(214.64540f,-1.567f,-5.098f,10000.0f);
		
		// Die Basic's...
		float a_m=nw.getA_m();
		float lt_m=nw.getLt_m();
		System.out.println("a="+a_m+"m  lt="+lt_m+"m   ra="+nw.getRa_m()+"m");
		System.out.println("");
		
		// Tangenden Aufteilung der Abzisse
		// AA
		float abstandRasterAAErsteOrdinate_m=nw.getAbstandAABisErsteOrdinate_m();
		System.out.println("xal="+abstandRasterAAErsteOrdinate_m);
		
		System.out.println("Anzhal 5 Meter- Abschnitte AA bis NW:"+nw.getAnzahlFuenfMeterAbschnitte_m(abstandRasterAAErsteOrdinate_m));
		
		float abstandRasterNWLetzteOrdinateLinks_m=nw.getAbstandNWBisLetzteOrdinateLinks();
		System.out.println("xel="+abstandRasterNWLetzteOrdinateLinks_m);
		
		// NW
		
		float abstandRasterAEErsteOrdinate_m=nw.getAbstandAEBisErsteOrdinate_m();
		System.out.println("xar="+abstandRasterAEErsteOrdinate_m);
		
		System.out.println("Anzahl 5 Meter- Asbschnitte AE bis NW:"+nw.getAnzahlFuenfMeterAbschnitte_m(nw.getAbstandAEBisErsteOrdinate_m()));
		
		float abstandNWLetzteOrdinateRechts_m=nw.getAbstandNWLetzteOrdinateRechts_m();
		System.out.println("xer="+abstandNWLetzteOrdinateRechts_m);
		// AE
		System.out.println("");
		
		// Ordinatenwerte
		List <Ordinatenwert>yn_m=nw.getYn_m();
		
		for (Ordinatenwert o: yn_m)
			System.out.println(o.getName()+"=\t "+o.getX_m()+"m  \t y="+(o.getY_m()*1000)+"mm");
		
		
	}
}
