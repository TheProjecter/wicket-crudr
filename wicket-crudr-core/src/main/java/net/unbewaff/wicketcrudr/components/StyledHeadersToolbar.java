/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unbewaff.wicketcrudr.components;

import java.util.List;

import net.unbewaff.wicketcrudr.datablocks.IMultipleStyledColumn;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IStyledColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;

/**
 * Toolbars that displays column headers. If the column is sortable a sortable
 * header will be displayed.
 *
 * @see DefaultDataTable
 *
 * @author Igor Vaynberg (ivaynberg)
 * @author David Hendrix (Nicktarix)
 *
 */
public class StyledHeadersToolbar extends AbstractToolbar {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param <T>
     *            the column data type
     * @param table
     *            data table this toolbar will be attached to
     * @param stateLocator
     *            locator for the ISortState implementation used by sortable
     *            headers
     */
    public <T> StyledHeadersToolbar(final DataTable<T> table, final ISortStateLocator stateLocator) {
        super(table);

        RepeatingView headers = new RepeatingView("headers");
        add(headers);

        final List<IColumn<T>> columns = table.getColumns();
        for (final IColumn<T> column : columns) {
            AbstractItem item = new AbstractItem(headers.newChildId());
            headers.add(item);

            WebMarkupContainer header = null;
            if (column.isSortable()) {
                header = newSortableHeader("header", column.getSortProperty(), stateLocator);
            } else {
                header = new WebMarkupContainer("header");
            }

            if (column instanceof IMultipleStyledColumn) {
                AttributeAppender classAppender = new AttributeAppender("class",
                        ((IMultipleStyledColumn<?>) column).getCssClassForHeader()).setSeparator(" ");

                header.add(classAppender);
            } else if (column instanceof IStyledColumn) {
                AttributeAppender classAppender = new AttributeAppender("class",
                        ((IStyledColumn<?>) column).getCssClass()).setSeparator(" ");

                header.add(classAppender);
            }

            item.add(header);
            item.setRenderBodyOnly(true);
            header.add(column.getHeader("label"));
        }
    }

    /**
     * Factory method for sortable header components. A sortable header
     * component must have id of <code>headerId</code> and conform to markup
     * specified in <code>HeadersToolbar.html</code>
     *
     * @param headerId
     *            header component id
     * @param property
     *            property this header represents
     * @param locator
     *            sort state locator
     * @return created header component
     */
    protected WebMarkupContainer newSortableHeader(final String headerId, final String property,
            final ISortStateLocator locator) {
        return new OrderByBorder(headerId, property, locator) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged() {
                getTable().setCurrentPage(0);
            }
        };
    }

}
