package be.kuleuven.gent.project.ejb;

import java.util.List;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.data.Route;

@Local
public interface RitManagementEJBLocal {
	public void createRit(Rit r);
	public void wijzigRit();
	public List<Rit> findAllRitten();
	int getAantalRitten();
	public List<Rit> findRitten(int route);
	public void keurRitGoed(int ritId);
	public void keurRitAf(int ritId);
	public List<Route> findPassagierRit(int passagierID);
}
