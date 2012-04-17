/**
 *
 */
package net.unbewaff.wicketcrudr.components;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.providers.IEditorProvider;
import net.unbewaff.wicketcrudr.providers.ILabelProvider;
import net.unbewaff.wicketcrudr.providers.ISurroundingContainerProvider;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IObjectClassAwareModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.JavaScriptUtils;
import org.apache.wicket.validation.IValidator;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class AjaxEditableLabelContainer<T> extends Panel {

    private final ILabelProvider<T> labelProvider;
    private final IEditorProvider<T> editorProvider;
    private final ISurroundingContainerProvider containerProvider;
    private final String propertyExpression;

    /** editor component. */
    private FormComponent<T> editor;

    /** label component. */
    private Component label;

    /** panel surrounding the editor */
    private WebMarkupContainer container;

    /**
     * @param id
     * @param model
     * @param configuration
     */
    public AjaxEditableLabelContainer(final String id, final IModel<T> model, ContainerConfiguration<T> configuration) {
        super(id, model);
        setOutputMarkupId(true);
        this.labelProvider = configuration.getLabelProvider();
        this.editorProvider = configuration.getEditorProvider();
        this.containerProvider = configuration.getContainerProvider();
        this.propertyExpression = configuration.getPropertyExpression();
    }

    /**
     * Hook used by ILabelProviders to create new Labels. These can come in form
     * of simple Labels or more complex Wicket Components
     *
     * @param parent
     *            unused right now. Inherited from AjaxEditableLabel
     * @param componentId
     *            The ID for the component to create
     * @param model
     *            The Model to use by the component
     * @return A component that will be used as the display-variant of the
     *         in-place-editor
     */
    protected Component newLabel(MarkupContainer parent, String componentId, IModel<T> model) {
        return new LabelWrapper<T>(labelProvider.newLabel(componentId, new PropertyModel(model, propertyExpression)), this);
    }

    /**
     * Hook used by IEditorProviders to create FormComponents. These will be
     * added to the result of newSurroundingPanel since FormComponents don't
     * come with their own Markup
     *
     * @param parent
     *            unused right now. Inherited from AjaxEditableLabel
     * @param componentId
     *            The ID for the component to create
     * @param model
     *            The Model to use by the component
     * @return The matching FormComponent to the Markup provided by
     *         newSurroundingPanel
     */
    protected FormComponent<T> newEditor(MarkupContainer parent, String componentId, IModel<T> model) {
        FormComponent<T> newEditor = editorProvider.newEditor(componentId, new PropertyModel(model, propertyExpression));
        return newEditor;
    }

    /**
     * Hook used by ISurroundingContainerProviders to create a Component with
     * matching Markup to accept the FormComponent of newEditor.
     *
     * @param parent
     *            unused right now. Inherited from AjaxEditableLabel
     * @param componentId
     *            The ID for the component to create
     * @return A WebMarkupContainer providing a matching input or select tag
     *         with the wicket:id "editor" to accept the FormComponent provided
     *         by newEditor
     */
    protected WebMarkupContainer newSurroundingContainer(MarkupContainer parent, String componentId) {
        return containerProvider.newSurroundingContainer(componentId);
    }

    protected class EditorAjaxBehavior extends AbstractDefaultAjaxBehavior {
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

            String callbackUrl = getCallbackUrl().toString();
            char separator = (callbackUrl != null && callbackUrl.indexOf('?') > -1) ? '&' : '?';

            final String saveCall = "{"
                    + generateCallbackScript("wicketAjaxGet('" + callbackUrl + separator
                            + "save=true&'+this.name+'='+wicketEncode(this.value)") + "; return false;}";

            final String cancelCall = "{"
                    + generateCallbackScript("wicketAjaxGet('" + callbackUrl + separator + "save=false'")
                    + "; return false;}";

            final String keypress = "var kc=wicketKeyCode(event); if (kc==27) " + cancelCall
                    + " else if (kc!=13) { return true; } else " + saveCall;

            tag.put("onblur", saveCall);
            tag.put("onkeydown", keypress);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void respond(final AjaxRequestTarget target) {
            RequestCycle requestCycle = RequestCycle.get();
            boolean save = requestCycle.getRequest().getRequestParameters().getParameterValue("save").toBoolean(false);

            if (save) {
                editor.processInput();

                if (editor.isValid()) {
                    onSubmit(target);
                } else {
                    onError(target);
                }
            } else {
                onCancel(target);
            }
        }
    }


    /**
     * Adds a validator to this form component. A model must be available for
     * this component before Validators can be added. Either add this Component
     * to its parent (already having a Model), or provide one before this call
     * via constructor {@link #AjaxEditableLabel(String,IModel)} or
     * {@link #setDefaultModel(IModel)}.
     *
     * @param validator
     *            The validator
     * @return This
     */
    public final AjaxEditableLabelContainer<T> add(final IValidator<T> validator) {
        getEditor().add(validator);
        return this;
    }

    /**
     * Implementation that returns null by default (panels don't typically need
     * converters anyway). This is used by the embedded default instances of
     * label and form field to determine whether they should use a converter
     * like they normally would (when this method returns null), or whether they
     * should use a custom converter (when this method is overridden and returns
     * not null).
     */
    @Override
    public <C> IConverter<C> getConverter(final Class<C> type) {
        return null;
    }

    /**
     * The value will be made available to the validator property by means of
     * ${label}. It does not have any specific meaning to FormComponent itself.
     *
     * @param labelModel
     * @return this for chaining
     */
    public final AjaxEditableLabelContainer<T> setLabel(final IModel<String> labelModel) {
        getEditor().setLabel(labelModel);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final AjaxEditableLabelContainer<T> setDefaultModel(final IModel<?> model) {
        super.setDefaultModel(model);
        getLabel().setDefaultModel(model);
        getEditor().setDefaultModel(model);
        return this;
    }

    /**
     * Sets the required flag
     *
     * @param required
     * @return this for chaining
     */
    public final AjaxEditableLabelContainer<T> setRequired(final boolean required) {
        getEditor().setRequired(required);
        return this;
    }

    /**
     * Sets the type that will be used when updating the model for this
     * component. If no type is specified String type is assumed.
     *
     * @param type
     * @return this for chaining
     */
    public final AjaxEditableLabelContainer<T> setType(final Class<?> type) {
        getEditor().setType(type);
        return this;
    }

    /**
     * By default this returns "onclick" uses can overwrite this on which event
     * the label behavior should be triggered
     *
     * @return The event name
     */
    protected String getLabelAjaxEvent() {
        return "onclick";
    }

    /**
     * Gets the editor component.
     *
     * @return The editor component
     */
    protected final FormComponent<T> getEditor() {
        if (editor == null) {
            initLabelAndEditor(new WrapperModel());
        }
        return editor;
    }

    /**
     * Gets the label component.
     *
     * @return The label component
     */
    protected final Component getLabel() {
        if (label == null) {
            initLabelAndEditor(new WrapperModel());
        }
        return label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        // lazily add label and editor
        if (editor == null) {
            initLabelAndEditor(new WrapperModel());
        }
    }

    /**
     * Invoked when the label is in edit mode, and received a cancel event.
     * Typically, nothing should be done here.
     *
     * @param target
     *            the ajax request target
     */
    protected void onCancel(final AjaxRequestTarget target) {
        label.setVisible(true);
        containerProvider.hide(target, container);
        target.add(AjaxEditableLabelContainer.this);
    }

    /**
     * Called when the label is clicked and the component is put in edit mode.
     *
     * @param target
     *            Ajax target
     */
    public void onEdit(final AjaxRequestTarget target) {
        label.setVisible(false);
        containerProvider.show(target, container);
        target.add(AjaxEditableLabelContainer.this);
        // put focus on the textfield and stupid explorer hack to move the
        // caret to the end
        target.appendJavaScript("{ var el=wicketGet('" + editor.getMarkupId() + "');" + "   if (el.createTextRange) { "
                + "     var v = el.value; var r = el.createTextRange(); "
                + "     r.moveStart('character', v.length); r.select(); } }");
        target.focusComponent(editor);
    }

    /**
     * Invoked when the label is in edit mode, received a new input, but that
     * input didn't validate
     *
     * @param target
     *            the ajax request target
     */
    protected void onError(final AjaxRequestTarget target) {
        Serializable errorMessage = editor.getFeedbackMessage().getMessage();
        if (errorMessage != null) {
            target.appendJavaScript("window.status='" + JavaScriptUtils.escapeQuotes(errorMessage.toString()) + "';");
        }
        target.appendJavaScript("{var el=wicketGet('" + editor.getMarkupId() + "'); el.select(); el.focus();}");
    }

    /**
     * Invoked when the editor was successfully updated. Use this method e.g. to
     * persist the changed value. This implementation displays the label and
     * clears any window status that might have been set in onError.
     *
     * @param target
     *            The ajax request target
     */
    protected void onSubmit(final AjaxRequestTarget target) {
        label.setVisible(true);
        editor.setVisible(false);
        target.add(AjaxEditableLabelContainer.this);

        target.appendJavaScript("window.status='';");
    }

    /**
     * Lazy initialization of the label and editor components and set tempModel
     * to null.
     *
     * @param model
     *            The model for the label and editor
     */
    private void initLabelAndEditor(final IModel<T> model) {
        editor = newEditor(this, "editor", model);
        label = newLabel(this, "label", model);
        container = newSurroundingContainer(this, "editor");
        add(label);
        container.add(editor);
        add(container);
    }

    /**
     * Model that accesses the parent model lazily. this is required since we
     * eventually request the parents model before the component is added to the
     * parent.
     */
    private class WrapperModel implements IModel<T>, IObjectClassAwareModel<T> {

        public T getObject() {
            return getParentModel().getObject();
        }

        public void setObject(final T object) {
            getParentModel().setObject(object);
        }

        public void detach() {
            getParentModel().detach();

        }

        public Class<T> getObjectClass() {
            if (getParentModel() instanceof IObjectClassAwareModel) {
                return ((IObjectClassAwareModel) getParentModel()).getObjectClass();
            } else {
                return null;
            }
        }
    }

    /**
     * @return Gets the parent model in case no explicit model was specified.
     */
    @SuppressWarnings("unchecked")
    private IModel<T> getParentModel() {
        // the #getModel() call below will resolve and assign any inheritable
        // model this component can use. Set that directly to the label and
        // editor so that those components work like this enclosing panel
        // does not exist (must have that e.g. with CompoundPropertyModels)
        IModel<T> m = (IModel<T>) getDefaultModel();

        // check that a model was found
        if (m == null) {
            Component parent = getParent();
            String msg = "No model found for this component, either pass one explicitly or "
                    + "make sure an inheritable model is available.";
            if (parent == null) {
                msg += " This component is not added to a parent yet, so if this component "
                        + "is supposed to use the model of the parent (e.g. when it uses a "
                        + "compound property model), add it first before further configuring "
                        + "the component calling methods like e.g. setType and addValidator.";
            }
            throw new IllegalStateException(msg);
        }
        return m;
    }

    /**
     * Override this to display a different value when the model object is null.
     * Default is <code>...</code>
     *
     * @return The string which should be displayed when the model object is
     *         null.
     */
    protected String defaultNullLabel() {
        return "...";
    }

    /**
     * Dummy override to fix WICKET-1239
     */
    @Override
    protected void onModelChanged() {
        super.onModelChanged();
    }

    /**
     * Dummy override to fix WICKET-1239
     */
    @Override
    protected void onModelChanging() {
        super.onModelChanging();
    }
}
