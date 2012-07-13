/**
 *
 */
package net.unbewaff.wicketcrudr.components;

import java.io.Serializable;
import java.util.List;

import net.unbewaff.wicketcrudr.columns.ColumnFactory;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class AutoLister<T extends Serializable> extends Panel implements Serializable {

	private final int rowsPerPage;
	private final Class<T> clazz;
	private String tableCssClass = "ui-widget";

	/**
	 * @param id
	 */
	public AutoLister(String id, int rowsPerPage, Class<T> clazz) {
		super(id);
		this.rowsPerPage = rowsPerPage;
		this.clazz = clazz;
	}

	/**
	 * @param id
	 * @param model
	 */
	public AutoLister(String id, IModel<? extends ICrudrListProvider<T>> model, int rowsPerPage, Class<T> clazz) {
		super(id, model);
		this.rowsPerPage = rowsPerPage;
		this.clazz = clazz;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.Component#onInitialize()
	 */
	@Override
	protected void onInitialize() {
		final List<IColumn<T>> columns = ColumnFactory.getColumns(clazz);
		@SuppressWarnings("unchecked")
		ICrudrListProvider<T> listProvider = (ICrudrListProvider<T>)getDefaultModelObject();
		final IDataProvider<T> dataProvider = new ListDataProvider<T>(listProvider.getList());
		DataTable<T> dataTable = new DataTable<T>("lister", columns, dataProvider, rowsPerPage);
		dataTable.add(new AttributeAppender("class", Model.of(getTableCssClass())));
		HeadersToolbar headerToolbar = new HeadersToolbar(dataTable, null);
		dataTable.addTopToolbar(headerToolbar);
		add(dataTable);
		super.onInitialize();
	}

	/**
	 * @return the CSS Class to use for the DataTable component of AutoLister. To change this you can either
	 * override this method in a subclass or use {@link AutoLister#setTableCssClass(String)}.
	 */
	protected String getTableCssClass() {
		return tableCssClass;
	}

	public void setTableCssClass(String tableCssClass) {
		this.tableCssClass = tableCssClass;
	}

}
