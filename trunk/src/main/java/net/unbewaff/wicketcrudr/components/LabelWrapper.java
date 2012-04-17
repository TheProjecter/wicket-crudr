package net.unbewaff.wicketcrudr.components;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.IMarkupFragment;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IModelComparator;
import org.apache.wicket.util.convert.IConverter;

/**
 * A Wrapper to add needed functionality to all Label-Components.
 *
 * @author David Hendrix (Nicktarix)
 *
 */
class LabelWrapper<T> extends WebComponent {

	private static final long serialVersionUID = 6991182183271562673L;
	private final WebComponent delegate;
	private final AjaxEditableLabelContainer<T> parent;

	/**
	 * @param delegate The Label-Component. Every non-final method is delegated there
	 * @param parent The parental AjaxEditableLabelContainer
	 */
	public LabelWrapper(WebComponent delegate, AjaxEditableLabelContainer<T> parent) {
		super(delegate.getId());
		this.delegate = delegate;
		this.parent = parent;
		setOutputMarkupId(true);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return delegate.hashCode();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	/**
	 * @see org.apache.wicket.Component#getMarkup()
	 */
	public IMarkupFragment getMarkup() {
		return delegate.getMarkup();
	}

	/**
	 * @see org.apache.wicket.Component#internalInitialize()
	 */
	public void internalInitialize() {
		delegate.internalInitialize();
	}

	/**
	 * @see org.apache.wicket.Component#detachModels()
	 */
	public void detachModels() {
		delegate.detachModels();
	}

	/**
	 * @see org.apache.wicket.Component#getConverter(java.lang.Class)
	 */
	public <C> IConverter<C> getConverter(Class<C> type) {
		return delegate.getConverter(type);
	}

	/**
	 * @see org.apache.wicket.Component#getId()
	 */
	public String getId() {
		return delegate.getId();
	}

	/**
	 * @see org.apache.wicket.Component#getLocale()
	 */
	public Locale getLocale() {
		return delegate.getLocale();
	}

	/**
	 * @see org.apache.wicket.Component#getMarkupId(boolean)
	 */
	public String getMarkupId(boolean createIfDoesNotExist) {
		return delegate.getMarkupId(createIfDoesNotExist);
	}

	/**
	 * @see org.apache.wicket.Component#getMarkupId()
	 */
	public String getMarkupId() {
		return delegate.getMarkupId();
	}

	/**
	 * @see org.apache.wicket.Component#getSession()
	 */
	public Session getSession() {
		return delegate.getSession();
	}

	/**
	 * @see org.apache.wicket.Component#getSizeInBytes()
	 */
	public long getSizeInBytes() {
		return delegate.getSizeInBytes();
	}

	/**
	 * @see org.apache.wicket.Component#getVariation()
	 */
	public String getVariation() {
		return delegate.getVariation();
	}

	/**
	 * @see org.apache.wicket.Component#isEnabled()
	 */
	public boolean isEnabled() {
		return delegate.isEnabled();
	}

	/**
	 * @see org.apache.wicket.Component#isVersioned()
	 */
	public boolean isVersioned() {
		return delegate.isVersioned();
	}

	/**
	 * @see org.apache.wicket.Component#isVisible()
	 */
	public boolean isVisible() {
		return delegate.isVisible();
	}

	/**
	 * @see org.apache.wicket.Component#internalPrepareForRender(boolean)
	 */
	public void internalPrepareForRender(boolean setRenderingFlag) {
		delegate.internalPrepareForRender(setRenderingFlag);
	}

	/**
	 * @see org.apache.wicket.Component#renderHead(org.apache.wicket.markup.html.internal.HtmlHeaderContainer)
	 */
	public void renderHead(HtmlHeaderContainer container) {
		delegate.renderHead(container);
	}

	/**
	 * @see org.apache.wicket.Component#replaceWith(org.apache.wicket.Component)
	 */
	public Component replaceWith(Component replacement) {
		return delegate.replaceWith(replacement);
	}

	/**
	 * @see org.apache.wicket.Component#setMarkupId(java.lang.String)
	 */
	public Component setMarkupId(String markupId) {
		return delegate.setMarkupId(markupId);
	}

	/**
	 * @see org.apache.wicket.Component#setDefaultModel(org.apache.wicket.model.IModel)
	 */
	public Component setDefaultModel(IModel<?> model) {
		return delegate.setDefaultModel(model);
	}

	/**
	 * @see org.apache.wicket.Component#setVersioned(boolean)
	 */
	public Component setVersioned(boolean versioned) {
		return delegate.setVersioned(versioned);
	}

	/**
	 * @see org.apache.wicket.Component#toString()
	 */
	public String toString() {
		return delegate.toString();
	}

	/**
	 * @see org.apache.wicket.Component#toString(boolean)
	 */
	public String toString(boolean detailed) {
		return delegate.toString(detailed);
	}

	/**
	 * @see org.apache.wicket.Component#getBehaviors(java.lang.Class)
	 */
	public <M extends Behavior> List<M> getBehaviors(Class<M> type) {
		return delegate.getBehaviors(type);
	}

	/**
	 * @see org.apache.wicket.Component#getModelComparator()
	 */
	public IModelComparator getModelComparator() {
		return delegate.getModelComparator();
	}

    /**
     * @see org.apache.wicket.Component#onComponentTagBody(org.apache.wicket.markup.MarkupStream, org.apache.wicket.markup.ComponentTag)
     */
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        Object modelObject = getDefaultModelObject();
        if ((modelObject == null) || "".equals(modelObject)) {
            replaceComponentTagBody(markupStream, openTag, defaultNullLabel());
        } else {
            delegate.onComponentTagBody(markupStream, openTag);
        }
    }

	/**
	 * @see org.apache.wicket.Component#get(java.lang.String)
	 */
	public Component get(String path) {
		return delegate.get(path);
	}

	/**
	 * @see org.apache.wicket.Component#canCallListenerInterface(java.lang.reflect.Method)
	 */
	public boolean canCallListenerInterface(Method method) {
		return delegate.canCallListenerInterface(method);
	}

	/**
	 * @see org.apache.wicket.Component#renderHead(org.apache.wicket.markup.html.IHeaderResponse)
	 */
	public void renderHead(IHeaderResponse response) {
		delegate.renderHead(response);
	}

	/**
	 * @see org.apache.wicket.Component#onEvent(org.apache.wicket.event.IEvent)
	 */
	public void onEvent(IEvent<?> event) {
		delegate.onEvent(event);
	}

	/**
	 * @see org.apache.wicket.Component#remove(org.apache.wicket.behavior.Behavior[])
	 */
	public Component remove(Behavior... behaviors) {
		return delegate.remove(behaviors);
	}

	/**
	 * @see org.apache.wicket.Component#add(org.apache.wicket.behavior.Behavior[])
	 */
	public Component add(Behavior... behaviors) {
		return delegate.add(behaviors);
	}

    /**
     *
     * @return The string which should be displayed when the model object is
     *         null.
     */
    protected String defaultNullLabel() {
        return "...";
    }

    protected class LabelAjaxBehavior extends AjaxEventBehavior {
        private static final long serialVersionUID = 1L;

        /**
         * Construct.
         *
         * @param event
         */
        public LabelAjaxBehavior(final String event) {
            super(event);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onEvent(final AjaxRequestTarget target) {
            parent.onEdit(target);
        }
    }

}
