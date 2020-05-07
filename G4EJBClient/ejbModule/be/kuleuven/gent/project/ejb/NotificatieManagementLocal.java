package be.kuleuven.gent.project.ejb;

import java.util.List;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Notificatie;

@Local
public interface NotificatieManagementLocal {

	List<Notificatie> findNotificaties(int id);
	void createNotificatie(Notificatie n);
	void delete(int id);
	long getAantalNotificaties(int id);
	void setGelezen(int id);

}
