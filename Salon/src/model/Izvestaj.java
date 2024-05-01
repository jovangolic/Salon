package model;


public class Izvestaj {

	public final String imeFrizera;
	public final double ukupanPrihod;
	public final String danSaNajvisePrihoda;
	
	public Izvestaj(String imeFrizera, double ukupanPrihod, String danSaNajvisePrihoda) {
		super();
		this.imeFrizera = imeFrizera;
		this.ukupanPrihod = ukupanPrihod;
		this.danSaNajvisePrihoda = danSaNajvisePrihoda;
	}

	@Override
	public String toString() {
		return "Izvestaj [imeFrizera=" + imeFrizera + ", ukupanPrihod=" + ukupanPrihod + ", danSaNajvisePrihoda="
				+ danSaNajvisePrihoda + "]";
	}
	
	public static int compareDanSaNajvisePrihoda(Izvestaj iz1, Izvestaj iz2) {
		return iz1.danSaNajvisePrihoda.compareTo(iz2.danSaNajvisePrihoda);
	}
	
	public static int comapreUkupanPrihod(Izvestaj iz1, Izvestaj iz2) {
		return -Double.compare(iz1.ukupanPrihod, iz2.ukupanPrihod);
	}
	
}
