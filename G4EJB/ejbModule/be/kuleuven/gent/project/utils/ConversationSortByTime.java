package be.kuleuven.gent.project.utils;

import java.util.Comparator;

import be.kuleuven.gent.project.data.Bericht;

public class ConversationSortByTime implements Comparator<Bericht> 
{ 
	@Override
	public int compare(Bericht o1, Bericht o2) {
		return o1.getTimestamp().compareTo(o2.getTimestamp());
	} 
} 
