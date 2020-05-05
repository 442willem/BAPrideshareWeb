package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Profiel;

@Local
public interface ProfielManagementEJBLocal {
	public void createProfiel(Profiel p);

	public Profiel getProfiel();

	public Profiel findProfiel(int id);

	public String getDriverscore(int id);
	public String getPassagierscore(int id);

	public Profiel findProfiel(String username);
	
	
}
