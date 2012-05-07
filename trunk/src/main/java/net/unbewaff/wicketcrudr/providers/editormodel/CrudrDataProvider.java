/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editormodel;

import java.util.List;

import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;

import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class CrudrDataProvider<T extends ICrudrDataProvider<T>> {

    public List<T> getList(IModel<T> model) {
        return model.getObject().getList();
    }

}
