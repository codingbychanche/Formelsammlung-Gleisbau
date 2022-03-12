package Bogen;

import java.util.ArrayList;
import java.util.List;

/**
 * Eine Tabelle mit Pfeilhöhenwerten.
 * 
 * @author Berthold
 *
 */
public class PfeilhöhenTabelle {
	
	private List <Pfeilhöhe> pfeilhöhen;
	
	/**
	 * Legt eine neue, leere Tabelle für Peilhöhenwerte an.
	 */
	public PfeilhöhenTabelle(){
		pfeilhöhen=new ArrayList<Pfeilhöhe>();
	}
	
	/**
	 * Eine eue Pfeilhöhe der tabelle hinzufügen.
	 * 
	 * @param pfeilhöhe Eine Instanzt von {@link Pfeilhöhe}. 
	 */
	public void add(Pfeilhöhe pfeilhöhe){
		pfeilhöhen.add(pfeilhöhe);
	}
	
	/**
	 * Liefert die gesammte Tabelle.
	 * 
	 * @return Die Tabelle mit den Pfeilhöhenwerten 
	 */
	public List<Pfeilhöhe> getPfeilhöhen(){
		return pfeilhöhen;
	}
	
	/**
	 * Liefert die Anzahl der in dieser Liste geispeicherten Elemente.
	 * 
	 * @return Anzahl der Eelemente in dieser Liste als {@link Interger}
	 */
	public int getLength(){
		return pfeilhöhen.size();
	}
	
	/**
	 * Liefert eine Pfeilhöhe aus der Tabelle.
	 * 
	 * @param i Index unter dem die Pfeilhöhe in dieser Tabelle gespeichert ist.
	 * @return Eine Instantz von {@link Pfeilhöhe}
	 * @throws ...
	 */
	
	public Pfeilhöhe getPfeilhöheAtIndex(int i){
		return pfeilhöhen.get(i);
	}
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	public float getPfeilhöheBeiX(float x){
		
			return 1f;
			
	}
	
	
}
