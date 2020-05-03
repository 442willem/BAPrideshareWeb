package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Tussenstop;

@Local
public interface TussenstopManagementEJBLocal {
	public void createTir(Tussenstop t);

	public Tussenstop findRit(int id);

	public void keurRitGoed(int ritId);

	public void deleteRit(int id);
}
