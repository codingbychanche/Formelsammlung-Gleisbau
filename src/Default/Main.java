package Default;

import java.util.List;

import Gradiente.Neigungswechsel;
import Gradiente.Ordinatenwert;

public class Main {

	public static void main (String args []) {
		System.out.println("Demo");
		
		Neigungswechsel nw=new Neigungswechsel(214.6454f,6.749f,-5.454f,10653.0f);
		
		// Die Basic's...
		float a_m=nw.getA_m();
		float lt_m=nw.getLt_m();
		System.out.println("a="+a_m+"  lt="+lt_m);
		
		// Ordinatenwerte
		List <Ordinatenwert>yn_m=nw.getYn_m(5f,5f);
		
		for (Ordinatenwert o: yn_m)
			System.out.println(o.getName()+"="+o.getX_m()+"  y="+(o.getY_m()*1000));
		
		
	}
}
