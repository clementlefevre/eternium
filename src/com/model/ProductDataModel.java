package com.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class ProductDataModel extends ListDataModel<Product> implements SelectableDataModel<Product> {  

    public ProductDataModel() {
    }

    public ProductDataModel(List<Product> data) {
        super(data);
    }
    
    @Override
    public Product getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        @SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) getWrappedData();
        
        for(Product product : products) {
            if(product.getItem_id().equals(rowKey))
                return product;
        }
        
        return null;
    }

    @Override
    public Object getRowKey(Product product) {
        return (product.getItem_id());
    }

	
}
                 
