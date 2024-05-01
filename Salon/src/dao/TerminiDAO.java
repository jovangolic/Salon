package dao;

import java.util.Collection;

import model.Termin;


public interface TerminiDAO {

	public Termin get(long id) throws Exception;
	public Collection<Termin> getAll() throws Exception;
	public void add(Termin termin) throws Exception;
	
	
	
}
