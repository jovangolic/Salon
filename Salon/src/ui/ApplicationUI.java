package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.FrizerDAO;
import dao.TerminiDAO;
import dao.impl.database.DatabaseFrizerDAO;
import dao.impl.database.DatabaseTerminDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class ApplicationUI {

	private static void initDatabase() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/salon?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade"
				,"root","gospodarsvega");
		
		FrizerDAO frizerDAO = new DatabaseFrizerDAO(conn);
		TerminiDAO terminiDAO = new DatabaseTerminDAO(conn, frizerDAO);
		
		FrizerUI.setFrizerDAO(frizerDAO);
		TerminiUI.setTerminiDAO(terminiDAO);
		TerminiUI.setFrizerDAO(frizerDAO);
		IzvestajUI.setFrizerDAO(frizerDAO);
		IzvestajUI.setTerminiDAO(terminiDAO);
		
	}
	
	static {
		try {
			initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greška pri povezivanju sa izvorom podataka!");
			
			System.exit(1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Meni.pokreni("Salon", new StavkaMenija[] {
			new IzlaznaStavkaMenija("Izlaz"),
			new FunkcionalnaStavkaMenija("Prikaz jednog frizera") {

				@Override
				public void izvrsi() { FrizerUI.prikaz(); }
				
			}, 
			new FunkcionalnaStavkaMenija("Prikaz svih termina") {

				@Override
				public void izvrsi() { TerminiUI.prikazSvih(); }
				
			},
			new FunkcionalnaStavkaMenija("Zakazivanje termina") {
				
				@Override
				public void izvrsi() {TerminiUI.zakazivanje();}
			},
			
			new FunkcionalnaStavkaMenija("Izvestaj") {
				
				@Override
				public void izvrsi() {IzvestajUI.izvestavanje();}
			}
		});
	}
}
