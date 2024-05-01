package dao;

import java.util.Collection;

import model.Frizer;


public interface FrizerDAO {

	public Frizer get(long id) throws Exception;
	public Collection<Frizer> getAll() throws Exception;
	public Frizer getByName(String ime) throws Exception;
	public Collection<Frizer> findBySmena(int smena) throws Exception;
}
