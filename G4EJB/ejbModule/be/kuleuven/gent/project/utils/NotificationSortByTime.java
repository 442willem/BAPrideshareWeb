package be.kuleuven.gent.project.utils;

import java.util.Comparator;

import be.kuleuven.gent.project.data.Notificatie;

public class NotificationSortByTime implements Comparator<Notificatie> 
{ 
	@Override
	public int compare(Notificatie n1, Notificatie n2) {
		return n2.getTijdstip().compareTo(n1.getTijdstip());
	} 
} 
