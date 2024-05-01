package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Termin {

	private long id;
	Frizer frizer;
	private LocalDateTime datumIVreme;
	private String ime;
	private String email;
	private String telefon;
	
	private final String DATE_FORMAT = "dd.MM.yyyy. HH:mm";
	private DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	
	public Termin() {
		super();
	}

	public Termin(Frizer frizer, LocalDateTime datumIVreme, String ime, String email, String telefon) {
		super();
		this.frizer = frizer;
		this.datumIVreme = datumIVreme;
		this.ime = ime;
		this.email = email;
		this.telefon = telefon;
	}

	public Termin(long id, Frizer frizer, LocalDateTime datumIVreme, String ime, String email, String telefon) {
		super();
		this.id = id;
		this.frizer = frizer;
		this.datumIVreme = datumIVreme;
		this.ime = ime;
		this.email = email;
		this.telefon = telefon;
		frizer.termini.add(this);
	}
	
	public static int compareFrizer(Termin t1, Termin t2) {
		return Frizer.compareSmena(t1.frizer, t2.frizer);
	}
	
	public static int compareDatum(Termin t1, Termin t2) {
		return t1.datumIVreme.compareTo(t2.datumIVreme);
	}
	
	public static int compareImeMusterije(Termin t1, Termin t2) {
		return t1.ime.compareTo(t2.ime);
	}
	
	public static int compareEmail(Termin t1, Termin t2) {
		return t1.email.compareTo(t2.email);
	}
	
	public static int compareTelefon(Termin t1, Termin t2) {
		return t2.telefon.compareTo(t2.telefon);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Frizer getFrizer() {
		return frizer;
	}

	public void setFrizer(Frizer frizer) {
		if(frizer != null) {
			frizer.termini.remove(this);
		}
		this.frizer = frizer;
		if(frizer != null) {
			frizer.termini.add(this);
		}
	}

	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
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
		Termin other = (Termin) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Termin [id=" + id + ", frizer=" + frizer.getId() + ", datumIVreme=" + datumIVreme.format(DATE_TIME_FORMATTER)+ ", ime=" + ime + ", email="
				+ email + ", telefon=" + telefon + "]";
	}
	
	
	
}
