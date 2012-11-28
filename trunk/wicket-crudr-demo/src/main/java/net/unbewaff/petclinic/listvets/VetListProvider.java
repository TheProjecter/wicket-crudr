/**
 *
 */
package net.unbewaff.petclinic.listvets;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.unbewaff.petclinic.WebSession;
import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.tools.WrappingList;

import org.apache.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.model.IModel;

/**
 * @author DavidH
 *
 */
public class VetListProvider implements IModel<List<VetWrapper>>, Serializable {

	private WrappingList<VetWrapper, Veterinarian> wrapper;
	private transient final static Logger logger = Logger.getLogger(VetListProvider.class);

	public VetListProvider() {
		Constructor<VetWrapper> constructor = null;
		try {
			constructor = VetWrapper.class.getConstructor(Veterinarian.class);
		} catch (SecurityException e) {
			logger.error(e);
		} catch (NoSuchMethodException e) {
			logger.error(e);
		}
		wrapper = new WrappingList<VetWrapper, Veterinarian>(constructor) {
			
			protected java.util.List<Veterinarian> getBaseList() {
				WebSession session = (WebSession)Session.get();
				return session.getVeterinarians();
			} 
		};
	}
	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.components.ICrudrListProvider#getList()
	 */
	public List<VetWrapper> getObject() {
		return wrapper;
	}
	@Override
	public void detach() {
		// do nothing
	}
	
	@Override
	public void setObject(List<VetWrapper> object) {
		// can't set a list
	}

}
