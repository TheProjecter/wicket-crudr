/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Lister;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class ColumnFactory implements Serializable {

    private ColumnFactory() {
        // static use only
    }

    public <T> IColumn<T> getColumn(Lister l, Editor e) {
        return null;
    }
}


