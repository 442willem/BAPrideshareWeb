package be.kuleuven.gent.project.utils;

import java.util.Comparator;

import be.kuleuven.gent.project.data.Bericht;

public class ConversationSortByTime implements Comparator<Bericht> 
{ 
	@Override
	public int compare(Bericht o1, Bericht o2) {
		return o2.getTimestamp().compareTo(o1.getTimestamp());
	} 
} 
