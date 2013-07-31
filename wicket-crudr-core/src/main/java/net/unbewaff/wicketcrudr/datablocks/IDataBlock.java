/**
 *
 */
package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;


import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface IDataBlock<T extends Serializable> {

    public abstract Component getValue(String componentId, IModel<T> rowModel);

    public abstract Component getLabel(String componentId, IModel<T> rowModel);

    public abstract String getName();

    /**
     * Returns an editor component if available and a display component if not
     * 
     * @param componentId the componentId
     * @param rowModel the model
     * 
     * @return an editor component if available and a display component if not
     * 
     */
    public abstract Component getEditor(String componentId, IModel<T> rowModel);

}
