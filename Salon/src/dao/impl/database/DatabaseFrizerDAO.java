package dao.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import dao.FrizerDAO;
import model.Frizer;

public class DatabaseFrizerDAO implements FrizerDAO {
	
	private final Connection conn;

	public DatabaseFrizerDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public Frizer get(long id) throws Exception {
		Frizer frizer = null;
		String sql = "select ime, cena, radniDani, smena from frizeri where id = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			int param = 0;
			ps.setLong(++param, id);
			try(ResultSet rset = ps.executeQuery()){
				if(rset.next()) {
					int kol = 0;
					String ime = rset.getString(++kol);
					double cena = rset.getDouble(++kol);
					String radniDani = rset.getString(++kol);
					int smena = rset.getInt(++kol);
					frizer = new Frizer(id, ime, cena, radniDani, smena);
				}
			}
		}
		return frizer;
	}

	@Override
	public Collection<Frizer> getAll() throws Exception {
		Collection<Frizer> frizeri = new ArrayList<>();
		String sql = "select id, ime, cena, radniDani, smena from frizeri";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			try(ResultSet rset = ps.executeQuery()){
				while(rset.next()) {
					int kol = 0;
					long id = rset.getLong(++kol);
					String ime = rset.getString(++kol);
					double cena = rset.getDouble(++kol);
					String radniDani = rset.getString(++kol);
					int smena = rset.getInt(++kol);
					Frizer frizer = new Frizer(id,ime, cena, radniDani, smena);
					frizeri.add(frizer);
				}
			}
		}
		return frizeri;
	}


	@Override
	public Frizer getByName(String ime) throws Exception {
		Frizer frizer = null;
		String sql = "select id, cena, radniDani, smena from frizeri where ime = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			int param = 0;
			ps.setString(++param, ime);
			try(ResultSet rset = ps.executeQuery()){
				if(rset.next()) {
					int kol = 0;
					long id = rset.getLong(++kol);
					double cena = rset.getDouble(++kol);
					String darniDani = rset.getString(++kol);
					int smena = rset.getInt(++kol);
					frizer = new Frizer(id, ime, cena, darniDani, smena);
				}
			}
		}
		return frizer;
	}

	@Override
	public Collection<Frizer> findBySmena(int smena) throws Exception {
		Collection<Frizer> frizeri = new ArrayList<>();
		String sql = "select id, ime, cena, radniDani from frizeri where smena = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			int param = 0;
			ps.setInt(++param, smena);
			try(ResultSet rset = ps.executeQuery()){
				while(rset.next()) {
					int kol = 0;
					long id = rset.getLong(++kol);
					String ime = rset.getString(++kol);
					double cena = rset.getDouble(++kol);
					String radniDani = rset.getString(++kol);
					Frizer frizer = new Frizer(id, ime, cena, radniDani, smena);
					frizeri.add(frizer);
				}
			}
		}
		return frizeri;
	}

}
