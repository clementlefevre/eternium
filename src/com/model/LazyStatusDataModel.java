package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazyStatusDataModel extends LazyDataModel<Status> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Status> datasource;

	public LazyStatusDataModel(List<Status> datasource) {
		this.datasource = datasource;
	}

	@Override
	public Status getRowData(String rowKey) {
		for(Status status : datasource) {
			System.out.println("on checke le rowdata");
			System.out.println("status.getStatus_id()" +status.getStatus_id());
			System.out.println("rowKey()"+rowKey);
			if(status.getStatus_id().equals(rowKey))
				return status;
		}

		return null;
	}

	@Override
	public Object getRowKey(Status status) {
		System.out.println(status.getStatus_id());
		return status.getStatus_id();
	}

	@Override
	public List<Status> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
		List<Status> data = new ArrayList<Status>();
		System.out.println("on demarre le binz de load");
		System.out.println(datasource.size());
		System.out.println("filter size : " + filters.size());

		System.out.println(filters);

		;
		//filter
		for(Status status : datasource) {
			boolean match = true;

			for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
				try {
					String filterProperty = it.next();

					
					String fieldValue="";


					if("status_id".equals(filterProperty )) {

						fieldValue=String.valueOf(status.getStatus_id());
					}
					else if("status_name.status_name".equals(filterProperty )) {
						fieldValue=String.valueOf(status.getStatus_name().getStatus_name());
					}
					else if("comment".equals(filterProperty )) {
						fieldValue=String.valueOf(status.getComment());
					}
					else if("product.product_infos.item_number".equals(filterProperty )) {	
						fieldValue=String.valueOf(status.getProduct().getProduct_infos().getItem_number());
					}
					else if("product.product_infos.item_name".equals(filterProperty )) {	
						fieldValue=String.valueOf(status.getProduct().getProduct_infos().getItem_name());
					}
					else if("product.serial".equals(filterProperty )) {	
						fieldValue=String.valueOf(status.getProduct().getSerial());
					}
					else if("product.delivery_notes.delivery_notes_name".equals(filterProperty )) {	
						fieldValue=String.valueOf(status.getProduct().getDelivery_notes().getDelivery_notes_name());
					}
					else if("product.product_infos.item_family.family_name".equals(filterProperty )) {	
						fieldValue=String.valueOf(status.getProduct().getProduct_infos().getItem_family().getFamily_name());
					}else if("loc.train_type.train_family".equals(filterProperty )) {	
						fieldValue=String.valueOf(status.getLoc().getTrain_type().getTrain_family());
					}else if("loc.train_type.train_label".equals(filterProperty )) {	
						fieldValue=String.valueOf(status.getLoc().getTrain_type().getTrain_label());
					}else if("loc.loc_serial_number".equals(filterProperty )) {	
						fieldValue=String.valueOf(status.getLoc().getLoc_serial_number());
					}



					String filterValue = filters.get(filterProperty);

					//String fieldValue=String.valueOf(status.getStatus_name().getStatus_name());




					if(filterValue == null || fieldValue.contains(filterValue)) {
						match = true;
					}
					else {
						match = false;
						break;
					}
				} catch(Exception e) {
					match = false;
				} 
			}

			if(match) {
				data.add(status);
			}
		}

		//sort
		if(sortField != null) {
			Collections.sort(data, new LazySorter(sortField, sortOrder));
		}

		//rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		//paginate
		if(dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			}
			catch(IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		}
		else {
			return data;
		}
	}
}                