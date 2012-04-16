package net.unbewaff.wicketcrudr.components;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Session;
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
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.validation.IValidationError;

public class FormComponentWrapper<T> extends FormComponent<T> {
	
	private final FormComponent<T> internal;

	public FormComponentWrapper(FormComponent<T> internal) {
		super(internal.getId(), internal.getModel());
		this.internal = internal;
		setOutputMarkupId(true);
	}

	public int hashCode() {
		return internal.hashCode();
	}

	public IModel<String> getLabel() {
		return internal.getLabel();
	}

	public boolean equals(Object obj) {
		return internal.equals(obj);
	}

	public MarkupContainer add(Component... childs) {
		return internal.add(childs);
	}

	public MarkupContainer addOrReplace(Component... childs) {
		return internal.addOrReplace(childs);
	}

	public boolean contains(Component component, boolean recurse) {
		return internal.contains(component, recurse);
	}

	public MarkupStream getAssociatedMarkupStream(boolean throwException) {
		return internal.getAssociatedMarkupStream(throwException);
	}

	public Markup getAssociatedMarkup() {
		return internal.getAssociatedMarkup();
	}

	public IMarkupFragment getMarkup(Component child) {
		return internal.getMarkup(child);
	}

	public MarkupType getMarkupType() {
		return internal.getMarkupType();
	}

	public boolean checkRequired() {
		return internal.checkRequired();
	}

	public void internalAdd(Component child) {
		internal.internalAdd(child);
	}

	public Iterator<Component> iterator() {
		return internal.iterator();
	}

	public void error(IValidationError error) {
		internal.error(error);
	}

	public MarkupContainer remove(Component component) {
		return internal.remove(component);
	}

	public MarkupContainer remove(String id) {
		return internal.remove(id);
	}

	public MarkupContainer removeAll() {
		return internal.removeAll();
	}

	public Form<?> getForm() {
		return internal.getForm();
	}

	public String getInput() {
		return internal.getInput();
	}

	public String[] getInputAsArray() {
		return internal.getInputAsArray();
	}

	public String getInputName() {
		return internal.getInputName();
	}

	public MarkupContainer replace(Component child) {
		return internal.replace(child);
	}

	public String getValidatorKeyPrefix() {
		return internal.getValidatorKeyPrefix();
	}

	public MarkupContainer setDefaultModel(IModel<?> model) {
		return internal.setDefaultModel(model);
	}

	public int size() {
		return internal.size();
	}

	public String toString() {
		return internal.toString();
	}

	public String toString(boolean detailed) {
		return internal.toString(detailed);
	}

	public boolean isInputNullable() {
		return internal.isInputNullable();
	}

	public boolean isMultiPart() {
		return internal.isMultiPart();
	}

	public boolean isRequired() {
		return internal.isRequired();
	}

	public boolean processChildren() {
		return internal.processChildren();
	}

	public FormComponent<T> setLabel(IModel<String> labelModel) {
		return internal.setLabel(labelModel);
	}

	public void setModelValue(String[] value) {
		internal.setModelValue(value);
	}

	public FormComponent<T> setType(Class<?> type) {
		return internal.setType(type);
	}

	public IMarkupFragment getMarkup() {
		return internal.getMarkup();
	}

	public void updateModel() {
		internal.updateModel();
	}

	public void validate() {
		internal.validate();
	}

	public void onComponentTagBody(MarkupStream markupStream,
			ComponentTag openTag) {
		internal.onComponentTagBody(markupStream, openTag);
	}

	public void detachModels() {
		internal.detachModels();
	}

	public <C> IConverter<C> getConverter(Class<C> type) {
		return internal.getConverter(type);
	}

	public String getId() {
		return internal.getId();
	}

	public Locale getLocale() {
		return internal.getLocale();
	}

	public boolean hasAssociatedMarkup() {
		return internal.hasAssociatedMarkup();
	}

	public String getMarkupId(boolean createIfDoesNotExist) {
		return internal.getMarkupId(createIfDoesNotExist);
	}

	public String getMarkupId() {
		return internal.getMarkupId();
	}

	public Session getSession() {
		return internal.getSession();
	}

	public long getSizeInBytes() {
		return internal.getSizeInBytes();
	}

	public String getVariation() {
		return internal.getVariation();
	}

	public boolean isEnabled() {
		return internal.isEnabled();
	}

	public boolean isVersioned() {
		return internal.isVersioned();
	}

	public boolean isVisible() {
		return internal.isVisible();
	}

	public void internalPrepareForRender(boolean setRenderingFlag) {
		internal.internalPrepareForRender(setRenderingFlag);
	}

	public void renderHead(HtmlHeaderContainer container) {
		internal.renderHead(container);
	}

	public Component replaceWith(Component replacement) {
		return internal.replaceWith(replacement);
	}

	public Component setMarkupId(String markupId) {
		return internal.setMarkupId(markupId);
	}

	public Component setVersioned(boolean versioned) {
		return internal.setVersioned(versioned);
	}

	public <M extends Behavior> List<M> getBehaviors(Class<M> type) {
		return internal.getBehaviors(type);
	}

	public IModelComparator getModelComparator() {
		return internal.getModelComparator();
	}

	public boolean canCallListenerInterface(Method method) {
		return internal.canCallListenerInterface(method);
	}

	public void renderHead(IHeaderResponse response) {
		internal.renderHead(response);
	}

	public void onEvent(IEvent<?> event) {
		internal.onEvent(event);
	}

	public Component remove(Behavior... behaviors) {
		return internal.remove(behaviors);
	}

	public Component add(Behavior... behaviors) {
		return internal.add(behaviors);
	}

}
