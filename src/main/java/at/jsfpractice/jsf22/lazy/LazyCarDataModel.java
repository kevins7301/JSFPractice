package at.jsfpractice.jsf22.lazy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyCarDataModel extends LazyDataModel<Car> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Car> datasource;

	public LazyCarDataModel(List<Car> datasource) {
		this.datasource = datasource;
	}

	@Override
	public Car getRowData(String rowKey) {
		for (Car car : datasource) {
			if (car.getId().equals(rowKey))
				return car;
		}

		return null;
	}

	@Override
	public Object getRowKey(Car car) {
		return car.getId();
	}

	@Override
	public List<Car> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<Car> data = new ArrayList<Car>();

		// filter
		for (Car car : datasource) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));

						if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						match = false;
					}
				}
			}

			if (match) {
				data.add(car);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new LazySorter(sortField, sortOrder));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}
	
	
	
	private LazyDataModel<Object[]> getModel(final boolean redirect) 
	 {
	    LazyDataModel<Object[]> model = new LazyDataModel<Object[]>() 
		{
	      private static final long serialVersionUID = -2801382067326793993L;

	      @Override
	      public List<Object[]> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) 
		  {
			List<Object[]> list = new ArrayList<Object[]>();

	        if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) 
			{
	          if (redirect) 
			  {
	            redirectToEmptyPage();
	          } 
			  else 
			  {
	            throw new RuntimeException("Pagination exception.");
	          }
	        } 
			else 
			{
	          list.add(new Object[] {System.nanoTime(), System.nanoTime()});
	          list.add(new Object[] {System.nanoTime(), System.nanoTime()});
	        }
	        return list;
	      }
	    };
	    model.setRowCount(4);

	    return model;
	  }
	
	public void redirectToEmptyPage() {
	    try {
	      FacesContext.getCurrentInstance().getExternalContext().redirect("test2.xhtml");
	    } catch (IOException e) {
	      // ignore
	    }
	  }
}