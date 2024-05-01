package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frizer {

	private long id;
	private String ime;
	private double cena;
	private String radniDani;
	private int smena;
	
	final List<Termin> termini = new ArrayList<>();
	
	public static int compareCena(Frizer f1, Frizer f2) {
		return Double.compare(f1.cena, f2.cena);
	}
	
	public static int compareRadniDan(Frizer f1, Frizer f2) {
		return f1.radniDani.compareTo(f2.radniDani);
	}
	
	public static int compareSmena(Frizer f1, Frizer f2) {
		return Integer.compare(f1.smena, f2.smena);
	}

	public Frizer() {
		super();
	}
	
	public Collection<Termin> getTermin(){
		return Collections.unmodifiableCollection(this.termini);
	}

	public Frizer(String ime,double cena, String radniDani, int smena) {
		super();
		this.ime = ime;
		this.cena = cena;
		this.radniDani = radniDani;
		this.smena = smena;
	}

	public Frizer(long id,String ime, double cena, String radniDani, int smena) {
		super();
		this.ime = ime;
		this.id = id;
		this.cena = cena;
		this.radniDani = radniDani;
		this.smena = smena;
	}
	
	public void addTermin(Termin termin) {
		this.termini.add(termin);
		if(termin != null) {
			termin.frizer.removeTermin(termin);
		}
		termin.frizer = this;
	}
	
	public void addAllTermine(Collection<Termin> termini) {
		this.termini.addAll(termini);
		for(Termin t : termini) {
			if(t.frizer != null) {
				t.frizer.removeTermin(t);
			}
			t.frizer = this;
		}
	}
	
	public void removeTermin(Termin termin) {
		this.termini.remove(termin);
		termin.frizer = null;
	}
	
	public void removeAllTermine() {
		for(Termin t : this.termini) {
			t.frizer = null;
		}
		this.termini.clear();
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getRadniDani() {
		return radniDani;
	}

	public void setRadniDani(String radniDani) {
		this.radniDani = radniDani;
	}

	public int getSmena() {
		return smena;
	}

	public void setSmena(int smena) {
		this.smena = smena;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frizer other = (Frizer) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Frizer [id=" + id + ", cena=" + cena + ", radniDani=" + radniDani + ", smena=" + smena + "]";
	}
	
	
}
