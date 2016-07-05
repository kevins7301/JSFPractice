package at.jsfpractice.jsf22.lazy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@ManagedBean
public class PaginationAjaxException {
	private static final String[] fields = { "field1", "field2" };

	public void throwRuntimeException() {
		throw new RuntimeException("Button exception.");
	}

	public LazyDataModel<Object[]> exceptionModel() {
		return getModel(false);
	}

	public LazyDataModel<Object[]> redirectModel() {
		return getModel(true);
	}

	private LazyDataModel<Object[]> getModel(final boolean redirect) {
		LazyDataModel<Object[]> model = new LazyDataModel<Object[]>() {
			private static final long serialVersionUID = -2801382067326793993L;

			@Override
			public List<Object[]> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				List<Object[]> list = new ArrayList<Object[]>();

				if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
					if (redirect) {
						redirectToEmptyPage();
					} else {
						throw new RuntimeException("Pagination exception.");
					}
				} else {
					list.add(new Object[] { System.nanoTime(), System.nanoTime()});
					list.add(new Object[] { System.nanoTime(), System.nanoTime()});
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

	public String[] getFields() {
		return fields;
	}
}
