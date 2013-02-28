/**
 *
 */
package net.unbewaff.wicketcrudr;

import java.io.Serializable;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.type.Css;
import net.unbewaff.wicketcrudr.columns.ColumnFactory;
import net.unbewaff.wicketcrudr.components.StyledHeadersToolbar;
import net.unbewaff.wicketcrudr.interfaces.IFactory;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.link.Link;
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

	private static final long serialVersionUID = -840357677393152697L;
	private final int rowsPerPage;
	private String tableCssClass = "ui-widget";
	private final List<IColumn<T>> columns;
	private boolean isFactory = false;

	/**
	 * @param id
	 * @param model
	 */
	public AutoLister(String id, IModel<List<T>> model, int rowsPerPage, Class<T> clazz) {
		super(id, model);
		this.rowsPerPage = rowsPerPage;
		Css css = clazz.getAnnotation(Css.class);
		if (css != null) {
			tableCssClass = tableCssClass + " " + css.value();
		}
		columns = ColumnFactory.getColumns(clazz);
		isFactory = IFactory.class.isAssignableFrom(clazz);
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.Component#onInitialize()
	 */
	@Override
	protected void onInitialize() {
		@SuppressWarnings("unchecked")
		final IDataProvider<T> dataProvider = new ListDataProvider<T>((List<T>)getDefaultModelObject());
		DataTable<T> dataTable = new DataTable<T>("lister", columns, dataProvider, rowsPerPage);
		dataTable.add(new AttributeAppender("class", Model.of(tableCssClass), " "));
		StyledHeadersToolbar headerToolbar = new StyledHeadersToolbar(dataTable, null);
		dataTable.addTopToolbar(headerToolbar);
		add(dataTable);
		Link<Void> link = new AjaxFallbackLink<Void>("add") {

			private static final long serialVersionUID = -119990055206955389L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				
				
			}
			
		};
		link.setVisible(isFactory);
		add(link);
		super.onInitialize();
	}
}
