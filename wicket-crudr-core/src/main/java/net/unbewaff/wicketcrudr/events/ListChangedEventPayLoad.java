package net.unbewaff.wicketcrudr.events;

import java.util.List;

import net.unbewaff.wicketcrudr.dataproviders.IManageableDataProvider;

import org.apache.wicket.ajax.AjaxRequestTarget;


/**
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
public class ListChangedEventPayLoad<T> extends AjaxEventPayload {

    private static final long serialVersionUID = -2939475524135589170L;
    private final IManageableDataProvider<T> provider;

    /**
     * @param target
     * @param provider
     */
    public ListChangedEventPayLoad(AjaxRequestTarget target, IManageableDataProvider<T> provider) {
        super(target);
        this.provider = provider;
    }

    /**
     * @return the provider
     */
    public IManageableDataProvider<T> getProvider() {
        return provider;
    }


}
