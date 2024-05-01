package ui;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import dao.FrizerDAO;
import dao.TerminiDAO;
import model.Frizer;
import model.Termin;
import util.Konzola;
import util.Tabela;


public class TerminiUI {

	private static TerminiDAO terminiDAO;
	private static FrizerDAO frizerDAO;
	
	public static void setTerminiDAO(TerminiDAO terminiDAO) {
		TerminiUI.terminiDAO = terminiDAO;
	}
	
	public static void setFrizerDAO(FrizerDAO frizerDAO) {
		TerminiUI.frizerDAO = frizerDAO;
	}


	private static final Tabela<Termin> TABELA = new Tabela<>(
			"%5s %10s %20s %20s %10s %s10",
			new Object[] {"ID", "Frizer", "Datum i Vreme", "Ime", "Email", "Telefon"}
		) {

			@Override
			protected List<Object[]> uredi(Termin vrednost) {
				List<Object[]> rezultat = new ArrayList<>();
				rezultat.add(new Object[] {
					vrednost.getId(), 
					vrednost.getFrizer().getId(),
					vrednost.getDatumIVreme(),
					vrednost.getIme(),
					vrednost.getEmail(),
					vrednost.getTelefon()
				});
				return rezultat;
			}
	
	};

	public static Termin pronalazenje() throws Exception {
		long id = Konzola.ocitajLong("Unesi id termina");
		Termin termin = terminiDAO.get(id);
		if(termin == null) {
			Konzola.prikazi("Termin nije pronadjen");
		}
		return termin;
	}
	
	public static void prikazSvih() {
		try {
			Collection<Termin> termini = terminiDAO.getAll();
			TABELA.prikazi(termini);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	public static void prikaz() {
		try {
			Termin termin = pronalazenje();
			if(termin == null) {
				return;
			}
			TABELA.prikazi(termin);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	public static void zakazivanje() {
		try {
			Collection<Termin> termini = terminiDAO.getAll();
			String imeFrizera = Konzola.ocitajString("Unesi ime frizera");
			Frizer frizer = frizerDAO.getByName(imeFrizera);
			if(frizer == null) {
				System.out.println("Frizer sa unetim imenom ne postoji");
				return;
			}
			LocalDateTime datum = Konzola.ocitajDateTime("Unesi datum i vreme termina");
			// prolazi kroz sve termine i trazi da li je zakazan za to vreme
			for(Termin t : termini) {
				if(t.getFrizer().getId() == frizer.getId()) {
					if(t.getDatumIVreme().equals(datum)) {
						System.out.println("Termin je zauzet, zakazite drugi termin");
						return;
					}
				}
			}
			
			// pregled dana u nedelji i pretvaramo ga u String
			// Dobijanje dana u nedelji kao DayOfWeek enumeraciju
			DayOfWeek danUnedelji = datum.getDayOfWeek();
			String danUnedeljiStr = danUnedelji.name();
			//provera da li frizer radi tog dana
			if(!frizer.getRadniDani().contains(danUnedeljiStr)) {
				System.out.println("Frizer ne radi tog dana u nedelji");
				return;
			}
			//smena
			LocalTime pocetakPrveSmene = LocalTime.of(9, 0);
			LocalTime krajPrveSmene = LocalTime.of(14, 0);
			LocalTime pocetakDrugeSmene = LocalTime.of(14, 0);
			LocalTime krajDrugeSmene = LocalTime.of(19, 0);
			int smena = 0;
			if(datum.toLocalTime().isAfter(pocetakPrveSmene) && datum.toLocalTime().isBefore(krajPrveSmene)) {
				smena = 1;
			}
			else if(datum.toLocalTime().isAfter(pocetakDrugeSmene) && datum.toLocalTime().isBefore(krajDrugeSmene)) {
				smena = 2;
			}
			//provera da li radi u toj smeni ili ne
			if(frizer.getSmena() != smena) {
				System.out.println("Frizer ne radi u toj smeni");
				return;
			}
			
			String imeMusterije = Konzola.ocitajString("Unesi ime musterije");
			String email = Konzola.ocitajString("unesi email musterije");
			String telefon = Konzola.ocitajString("Unesi telefon musterije");
			Termin termin = new Termin(0,frizer, datum, imeMusterije, email, telefon);
			terminiDAO.add(termin);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
}
