/**
 *
 */
package net.unbewaff.petclinic.listvets;

import java.io.Serializable;
import java.util.List;

import net.unbewaff.petclinic.WebSession;
import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.wicketcrudr.tools.wrappinglist.AWrappingList;
import net.unbewaff.wicketcrudr.tools.wrappinglist.IWrapperFactory;

import org.apache.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.model.IModel;

/**
 * @author DavidH
 *
 */
public class VetListProvider implements IModel<List<VetWrapper>>, Serializable {

	private AWrappingList<VetWrapper, Veterinarian> wrapper;
	private transient final static Logger logger = Logger.getLogger(VetListProvider.class);

	public VetListProvider() {

		wrapper = new AWrappingList<VetWrapper, Veterinarian>() {
			
			protected java.util.List<Veterinarian> getBaseList() {
				WebSession session = (WebSession)Session.get();
				return session.getVeterinarians();
			}

			@Override
			protected IWrapperFactory<VetWrapper, Veterinarian> getWrapperFactory() {
				return new IWrapperFactory<VetWrapper, Veterinarian>() {

					@Override
					public VetWrapper newWrapper(Veterinarian target) {
						return new VetWrapper(target);
					}
				};
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
