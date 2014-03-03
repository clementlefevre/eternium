package com.model;

import java.util.Comparator;

import com.model.Status;

import org.primefaces.model.SortOrder;

public class LazySorter implements Comparator<Status> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Status status1, Status status2) {
        try {
            Object value1 = Status.class.getField(this.sortField).get(status1);
            Object value2 = Status.class.getField(this.sortField).get(status2);

            @SuppressWarnings("unchecked")
			int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}

