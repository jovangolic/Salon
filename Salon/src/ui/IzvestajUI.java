package ui;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import dao.FrizerDAO;
import dao.TerminiDAO;
import model.Frizer;
import model.Izvestaj;
import model.Termin;
import util.Konzola;

public class IzvestajUI {

	private static FrizerDAO frizerDAO;
	private static TerminiDAO terminiDAO;
	
	public static void setFrizerDAO(FrizerDAO frizerDAO) {
		IzvestajUI.frizerDAO = frizerDAO;
	}
	public static void setTerminiDAO(TerminiDAO terminiDAO) {
		IzvestajUI.terminiDAO = terminiDAO;
	}
	
	public static void izvestavanje() {
		try {
			Collection<Termin> termini = terminiDAO.getAll();
			int smena = Konzola.ocitajInt("Unesi smenu (1 ili 2)");
			//proveravanje unete smene
			if(smena != 1 && smena != 2) {
				System.out.println("Smena mora biti 1 ili 2");
				return;
			}
		
			//dobavljanje svih frizera prema datoj smeni
			Collection<Frizer> frizeri = frizerDAO.findBySmena(smena);
			//generisanje izvestaja
			List<Izvestaj> izvestaji = new ArrayList<>();
			for(Frizer f : frizeri) {
				String imeFrizera = null;
				double ukupanPrihod = 0;
				String radniDanMaxPrihod = null;
				int brojacTermina = 0;
				//prva kolona
				imeFrizera = f.getIme();
				//druga kolona
				for(Termin t : termini) {
					if(t.getFrizer().getId() == f.getId()) {
						brojacTermina++;
						ukupanPrihod += f.getCena() * brojacTermina;
					}
				}
				//treca kolona
				for(Termin t : termini) {
					if(t.getFrizer().getId() == f.getId()) {
						DayOfWeek danUnedelji = t.getDatumIVreme().getDayOfWeek();
						String danUNedeljiStr = danUnedelji.getDisplayName(TextStyle.FULL,Locale.getDefault());
						radniDanMaxPrihod = danUNedeljiStr;
					}
				}
				Izvestaj izvestaj = new Izvestaj(imeFrizera, ukupanPrihod, radniDanMaxPrihod);
				izvestaji.add(izvestaj);
			}
			izvestaji.sort(Izvestaj::comapreUkupanPrihod);
			System.out.println();
			for(Izvestaj i : izvestaji) {
				System.out.println(i);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}
	
}
