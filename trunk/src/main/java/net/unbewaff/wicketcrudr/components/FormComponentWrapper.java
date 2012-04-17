package net.unbewaff.wicketcrudr.components;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.IMarkupFragment;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.MarkupType;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IModelComparator;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.validation.IValidationError;

/**
 * Wrapper to {@link FormComponent}s to provide needed functionality and save the delegate from having to
 * implement them again and again.
 *
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
class FormComponentWrapper<T> extends FormComponent<T> {

    private static final long serialVersionUID = 6716051604844872977L;
    private final FormComponent<T> delegate;
	private final AjaxEditableLabelContainer<T> parent;

	/**
	 * @param delegate The wrapped FormComponent. Every non-final method will be called from there
	 * @param parent The parental {@link AjaxEdiatableLabelContainer}
	 */
	public FormComponentWrapper(FormComponent<T> delegate, AjaxEditableLabelContainer<T> parent) {
	    super(delegate.getId(), delegate.getModel());
		this.delegate = delegate;
		this.parent = parent;
        setOutputMarkupId(true);
        setVisible(false);
        add(new EditorAjaxBehavior());	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return delegate.hashCode();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.LabeledWebMarkupContainer#getLabel()
	 */
	public IModel<String> getLabel() {
		return delegate.getLabel();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#add(org.apache.wicket.Component[])
	 */
	public MarkupContainer add(Component... childs) {
		return delegate.add(childs);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#addOrReplace(org.apache.wicket.Component[])
	 */
	public MarkupContainer addOrReplace(Component... childs) {
		return delegate.addOrReplace(childs);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#contains(org.apache.wicket.Component, boolean)
	 */
	public boolean contains(Component component, boolean recurse) {
		return delegate.contains(component, recurse);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#getAssociatedMarkupStream(boolean)
	 */
	public MarkupStream getAssociatedMarkupStream(boolean throwException) {
		return delegate.getAssociatedMarkupStream(throwException);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#getAssociatedMarkup()
	 */
	public Markup getAssociatedMarkup() {
		return delegate.getAssociatedMarkup();
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#getMarkup(org.apache.wicket.Component)
	 */
	public IMarkupFragment getMarkup(Component child) {
		return delegate.getMarkup(child);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#getMarkupType()
	 */
	public MarkupType getMarkupType() {
		return delegate.getMarkupType();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#checkRequired()
	 */
	public boolean checkRequired() {
		return delegate.checkRequired();
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#internalAdd(org.apache.wicket.Component)
	 */
	public void internalAdd(Component child) {
		delegate.internalAdd(child);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#iterator()
	 */
	public Iterator<Component> iterator() {
		return delegate.iterator();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#error(org.apache.wicket.validation.IValidationError)
	 */
	public void error(IValidationError error) {
		delegate.error(error);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#remove(org.apache.wicket.Component)
	 */
	public MarkupContainer remove(Component component) {
		return delegate.remove(component);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#remove(java.lang.String)
	 */
	public MarkupContainer remove(String id) {
		return delegate.remove(id);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#removeAll()
	 */
	public MarkupContainer removeAll() {
		return delegate.removeAll();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#getForm()
	 */
	public Form<?> getForm() {
		return delegate.getForm();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#getInput()
	 */
	public String getInput() {
		return delegate.getInput();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#getInputAsArray()
	 */
	public String[] getInputAsArray() {
		return delegate.getInputAsArray();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#getInputName()
	 */
	public String getInputName() {
		return delegate.getInputName();
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#replace(org.apache.wicket.Component)
	 */
	public MarkupContainer replace(Component child) {
		return delegate.replace(child);
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#getValidatorKeyPrefix()
	 */
	public String getValidatorKeyPrefix() {
		return delegate.getValidatorKeyPrefix();
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#setDefaultModel(org.apache.wicket.model.IModel)
	 */
	public MarkupContainer setDefaultModel(IModel<?> model) {
		return delegate.setDefaultModel(model);
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#size()
	 */
	public int size() {
		return delegate.size();
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#toString()
	 */
	public String toString() {
		return delegate.toString();
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#toString(boolean)
	 */
	public String toString(boolean detailed) {
		return delegate.toString(detailed);
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#isInputNullable()
	 */
	public boolean isInputNullable() {
		return delegate.isInputNullable();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#isMultiPart()
	 */
	public boolean isMultiPart() {
		return delegate.isMultiPart();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#isRequired()
	 */
	public boolean isRequired() {
		return delegate.isRequired();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#processChildren()
	 */
	public boolean processChildren() {
		return delegate.processChildren();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#setLabel(org.apache.wicket.model.IModel)
	 */
	public FormComponent<T> setLabel(IModel<String> labelModel) {
		return delegate.setLabel(labelModel);
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#setModelValue(java.lang.String[])
	 */
	public void setModelValue(String[] value) {
		delegate.setModelValue(value);
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#setType(java.lang.Class)
	 */
	public FormComponent<T> setType(Class<?> type) {
		return delegate.setType(type);
	}

	/**
	 * @see org.apache.wicket.Component#getMarkup()
	 */
	public IMarkupFragment getMarkup() {
		return delegate.getMarkup();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#updateModel()
	 */
	public void updateModel() {
		delegate.updateModel();
	}

	/**
	 * @see org.apache.wicket.markup.html.form.FormComponent#validate()
	 */
	public void validate() {
		delegate.validate();
	}

	/**
	 * @see org.apache.wicket.MarkupContainer#onComponentTagBody(org.apache.wicket.markup.MarkupStream, org.apache.wicket.markup.ComponentTag)
	 */
	public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		delegate.onComponentTagBody(markupStream, openTag);
	}

	/**
	 * @see org.apache.wicket.Component#detachModels()
	 */
	public void detachModels() {
		delegate.detachModels();
	}

	/**
	 * Tries to obtain a matching converter from the {@link AjaxEditableLabelContainer}
	 * @see org.apache.wicket.Component#getConverter(java.lang.Class)
	 */
	public <C> IConverter<C> getConverter(Class<C> type) {
        IConverter<C> c = parent.getConverter(type);
        return c != null ? c : delegate.getConverter(type);
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
	 * @see org.apache.wicket.MarkupContainer#hasAssociatedMarkup()
	 */
	public boolean hasAssociatedMarkup() {
		return delegate.hasAssociatedMarkup();
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
	 * @see org.apache.wicket.Component#setVersioned(boolean)
	 */
	public Component setVersioned(boolean versioned) {
		return delegate.setVersioned(versioned);
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


    protected class EditorAjaxBehavior extends AbstractDefaultAjaxBehavior
    {
        private static final long serialVersionUID = 1L;

        /**
         * Constructor.
         */
        public EditorAjaxBehavior() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onComponentTag(final ComponentTag tag) {
            super.onComponentTag(tag);
            final String saveCall = "{" +
                generateCallbackScript("wicketAjaxGet('" + getCallbackUrl() +
                    "&save=true&'+this.name+'='+wicketEncode(this.value)") + "; return false;}";

            final String cancelCall = "{" +
                generateCallbackScript("wicketAjaxGet('" + getCallbackUrl() + "&save=false'") +
                "; return false;}";


            final String keypress = "var kc=wicketKeyCode(event); if (kc==27) " + cancelCall +
                " else if (kc!=13) { return true; } else " + saveCall;

            tag.put("onblur", saveCall);
            tag.put("onkeypress", "if (Wicket.Browser.isSafari()) { return; }; " + keypress);
            tag.put("onkeydown", "if (!Wicket.Browser.isSafari()) { return; }; " + keypress);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void respond(final AjaxRequestTarget target) {
            RequestCycle requestCycle = RequestCycle.get();
            boolean save = requestCycle.getRequest().getRequestParameters().getParameterValue("save").toBoolean(false);

            if (save) {
                processInput();

                if (isValid()) {
                    parent.onSubmit(target);
                } else {
                    parent.onError(target);
                }
            } else {
                parent.onCancel(target);
            }
        }
    }

}
