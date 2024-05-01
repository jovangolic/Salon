package dao.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import dao.FrizerDAO;
import dao.TerminiDAO;
import model.Frizer;
import model.Termin;

public class DatabaseTerminDAO implements TerminiDAO {
	
	private final Connection conn;
	private final FrizerDAO frizerDAO;

	public DatabaseTerminDAO(Connection conn, FrizerDAO frizerDAO) {
		super();
		this.conn = conn;
		this.frizerDAO = frizerDAO;
	}

	@Override
	public Termin get(long id) throws Exception {
		Termin termin = null;
		String sql = "select frizerId, datumIVreme, ime, email, telefon from termini where id = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			int param = 0;
			ps.setLong(++param, id);
			try(ResultSet rset = ps.executeQuery()){
				if(rset.next()) {
					int kol = 0;
					long frizerId = rset.getLong(++kol);
					Frizer frizer = frizerDAO.get(frizerId);
					LocalDateTime datum = rset.getTimestamp(++kol).toLocalDateTime();
					String ime = rset.getString(++kol);
					String email = rset.getString(++kol);
					String telefon = rset.getString(++kol);
					termin = new Termin(id, frizer, datum, ime, email, telefon);
				}
			}
		}
		return termin;
	}

	@Override
	public Collection<Termin> getAll() throws Exception {
		Collection<Termin> termini = new ArrayList<>();
		String sql = "select id, frizerId, datumIVreme, ime, email, telefon from termini";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			try(ResultSet rset = ps.executeQuery()){
				while(rset.next()) {
					int kol = 0;
					long id = rset.getLong(++kol);
					long frizerId = rset.getLong(++kol);
					Frizer frizer = frizerDAO.get(frizerId);
					LocalDateTime datum = rset.getTimestamp(++kol).toLocalDateTime();
					String ime = rset.getString(++kol);
					String email = rset.getString(++kol);
					String telefon = rset.getString(++kol);
					Termin termin = new Termin(id, frizer, datum, ime, email, telefon);
					termini.add(termin);
				}
			}
		}
		return termini;
	}

	@Override
	public void add(Termin termin) throws Exception {
		String sql = "INSERT INTO termini( frizerId, datumIVreme, ime, email, telefon) VALUES(?,?,?,?,?)";
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			int param = 0;
			ps.setLong(++param, termin.getFrizer().getId());
			ps.setTimestamp(++param, Timestamp.valueOf(termin.getDatumIVreme()));
			ps.setString(++param, termin.getIme());
			ps.setString(++param, termin.getEmail());
			ps.setString(++param, termin.getTelefon());
			ps.executeUpdate();
		}
	}

	

}
