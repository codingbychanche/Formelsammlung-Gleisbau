package Default;

import java.util.ArrayList;
import java.util.List;

import Allgemeines.KoordinatenPaar;
import Gradiente.Neigungswechsel;
import GleisModell.Gleis;
import GleisModell.Verwindung;


/**
 * Demonstrations Programm zur Formelsammlung Gleisbau.
 * 
 * @author Berthold
 *
 */
public class Main {

	/**
	 * Main Methode.
	 * 
	 * @param args	Nix erforderlich.
	 */
	public static void main (String args []) {
		System.out.println("Demo");
		System.out.println("");
		
		/*
		Neigungswechsel nw=new Neigungswechsel(1.80000f,6.749f,-5.454f,10653.0f);
		
		// Die Basic's...
		float a_m=nw.getA_m();
		float lt_m=nw.getLt_m();
		System.out.println("a="+a_m+"m  lt="+lt_m+"m   ra="+nw.getRa_m()+"m");
		System.out.println("");
		
		// Formeln im Klartext
		System.out.println(nw.getFormelRaHtml().getFormelImKlartext()+"\tQuelle:"+nw.getFormelRaHtml().getQuellenAngabe());
		System.out.println(nw.getFormelLtHtml().getFormelImKlartext()+"\tQuelle:"+nw.getFormelLtHtml().getQuellenAngabe());
		System.out.println(nw.getFormelAHtml().getFormelImKlartext()+"\tQuelle:"+nw.getFormelAHtml().getQuellenAngabe());
		System.out.println("");
		
		// Tangenden Aufteilung der Abzisse
		// AA
		float abstandRasterAAErsteOrdinate_m=nw.getAbstandAABisErsteOrdinate_m();
		System.out.println("xal="+abstandRasterAAErsteOrdinate_m);
		
		System.out.println("Anzahl 5 Meter- Abschnitte AA bis NW:"+nw.getAnzahlFuenfMeterAbschnitte_m(abstandRasterAAErsteOrdinate_m));
		
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
		List <KoordinatenPaar>yn_m=nw.getYn_m();
		
		for (KoordinatenPaar o: yn_m)
			System.out.println(o.getBezeichnungX()+"=\t "+o.getX()+"m  \t y="+(o.getY()*1000)+"mm");
			*/
		
		
		//
		// Gleis
		//
		Gleis myGleis=new Gleis("Demo",1,0.5,100);
		
		// Sinusförmige Welle in der linken Schiene.
		// Erster Nullpunkt ist bei 0 m. die Amplitute beträgt 0.025 m
		myGleis.erzeugeWelleLinkeSchiene(0.0,0.025);
		List <Double> linkeSchiene=new ArrayList<Double>();
		linkeSchiene=myGleis.getLaengshoeheLinkeSchiene();
		
		int x=0;
		double meterVomGleisanfang=0;
		double gh_m=0;
		for (Double y:linkeSchiene) {
			gh_m=Verwindung.getGH_m(myGleis,meterVomGleisanfang);
			System.out.println("x ="+x+"   Meter:"+meterVomGleisanfang+"    y="+y+" GH="+gh_m);
			x++;
			meterVomGleisanfang=x*myGleis.getWegschritt_m();
		}
		
		
		double vw_p=Verwindung.getVerwindung_P(myGleis,100,3.6);
		System.out.println("VW="+vw_p+" Promille");
	}
}
