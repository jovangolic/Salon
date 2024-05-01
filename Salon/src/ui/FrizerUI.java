package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import dao.FrizerDAO;
import model.Frizer;

import util.Konzola;
import util.Tabela;


public class FrizerUI {
	
	private static FrizerDAO frizerDAO;

	public static void setFrizerDAO(FrizerDAO frizerDAO) {
		FrizerUI.frizerDAO = frizerDAO;
	}

	private static final Tabela<Frizer> TABELA = new Tabela<>(
			"%5s %20s %5s %50s %5s",
			new Object[] {"ID", "Ime", "Cena", "Radni dani", "Smena"}
		) {

			@Override
			protected List<Object[]> uredi(Frizer vrednost) {
				List<Object[]> rezultat = new ArrayList<>();
				rezultat.add(new Object[] {
					vrednost.getId(), 
					vrednost.getIme(),
					vrednost.getCena(),
					vrednost.getRadniDani(),
					vrednost.getSmena()
				});
				return rezultat;
			}
	
	};
	
	/*private static boolean isValidDay(String day) {
	    String[] validDays = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
	    return Arrays.asList(validDays).contains(day.toUpperCase());
	}*/

	public static Frizer pronalazenje() throws Exception {
		prikazSvih();
		long id = Konzola.ocitajLong("unesi id frizera");
		Frizer frizer = frizerDAO.get(id);
		if(frizer == null) {
			Konzola.prikazi("Frizer nije pronadjen");
		}
		return frizer;
	}
	
	public static void prikazSvih() {
		try {
			Collection<Frizer> frizeri = frizerDAO.getAll();
			TABELA.prikazi(frizeri);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	public static void prikaz() {
		try {
			Frizer frizer = pronalazenje();
			if(frizer == null) {
				return;
			}
			TABELA.prikazi(frizer);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	/*private static void dodavanje() {
		try {
			String ime = "";
			while(ime.equals("")) {
				ime = Konzola.ocitajString("Unesi ime frizera");
			}
			double cena = 0;
			while(cena <= 0) {
				cena = Konzola.ocitajDouble("unesi cenu sisanja");
			}
			String radniDanInput = "";
	        String radniDani = "";
	        while(radniDanInput.isEmpty()) {
	            radniDanInput = Konzola.ocitajString("Unesi radni dan npr(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY)");
	            radniDanInput = radniDanInput.toUpperCase(); //konvertuje unos u velika slova
	            switch (radniDanInput) {
	                case "MONDAY":
	                case "TUESDAY":
	                case "WEDNESDAY":
	                case "THURSDAY":
	                case "FRIDAY":
	                case "SATURDAY":
	                case "SUNDAY":
	                    radniDani = radniDanInput;
	                    break;
	                default:
	                    radniDanInput = ""; // Ponovno postavlja unos kako bi petlja nastavila
	                    System.out.println("Uneli ste neispravan dan. Molimo unesite jedan od ponuđenih dana.");
	            }
	        }
			int smena = 0;
			while(smena != 1 && smena != 2) {
			    smena = Konzola.ocitajInt("unesi smenu (1 ili 2)");
			}
			Frizer frizer = new Frizer(ime, cena, radniDani, smena);
			
			frizerDAO.add(frizer);
			Konzola.prikazi("Uspesno dodat");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	private static void izmena() {
		try {
			Frizer frizer = pronalazenje();
			if(frizer == null) {
				return;
			}
			String ime = "";
			while(ime.equals("")) {
				ime = Konzola.ocitajString("Unesi ime frizera");
			}
			double cena = 0;
			while(cena <= 0) {
				cena = Konzola.ocitajDouble("unesi cenu sisanja");
			}
			String radniDanInput = "";
	        String radniDani = "";
	        while(radniDanInput.isEmpty()) {
	            radniDanInput = Konzola.ocitajString("Unesi radni dan npr(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY)");
	            radniDanInput = radniDanInput.toUpperCase(); //konvertuje unos u velika slova
	            switch (radniDanInput) {
	                case "MONDAY":
	                case "TUESDAY":
	                case "WEDNESDAY":
	                case "THURSDAY":
	                case "FRIDAY":
	                case "SATURDAY":
	                case "SUNDAY":
	                    radniDani = radniDanInput;
	                    break;
	                default:
	                    radniDanInput = ""; // Ponovno postavlja unos kako bi petlja nastavila
	                    System.out.println("Uneli ste neispravan dan. Molimo unesite jedan od ponuđenih dana.");
	            }
	        }
	        int smena = 0;
			while(smena != 1 && smena != 2) {
			    smena = Konzola.ocitajInt("unesi smenu (1 ili 2)");
			}
			frizer.setIme(ime);
			frizer.setCena(cena);
			frizer.setRadniDani(radniDani);
			frizer.setSmena(smena);
			frizerDAO.update(frizer);
			Konzola.prikazi("Azuriranje uspesno");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
	private static void brisanje() {
		try {
			Frizer frizer = pronalazenje();
			if(frizer == null) {
				return;
			}
			frizerDAO.delete(frizer.getId());
			Konzola.prikazi("Uspesno obrisano");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}*/
	
	
}
