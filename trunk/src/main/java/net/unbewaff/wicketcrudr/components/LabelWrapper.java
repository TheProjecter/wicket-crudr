package net.unbewaff.wicketcrudr.components;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
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

public class LabelWrapper extends WebComponent {

	private static final long serialVersionUID = 6991182183271562673L;
	private final WebComponent internal;
	
	public LabelWrapper(WebComponent internal) {
		super(internal.getId());
		this.internal = internal;
	}

	public int hashCode() {
		return internal.hashCode();
	}

	public boolean equals(Object obj) {
		return internal.equals(obj);
	}

	public IMarkupFragment getMarkup() {
		return internal.getMarkup();
	}

	public void internalInitialize() {
		internal.internalInitialize();
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

	public Component setDefaultModel(IModel<?> model) {
		return internal.setDefaultModel(model);
	}

	public Component setVersioned(boolean versioned) {
		return internal.setVersioned(versioned);
	}

	public String toString() {
		return internal.toString();
	}

	public String toString(boolean detailed) {
		return internal.toString(detailed);
	}

	public <M extends Behavior> List<M> getBehaviors(Class<M> type) {
		return internal.getBehaviors(type);
	}

	public IModelComparator getModelComparator() {
		return internal.getModelComparator();
	}

	public void onComponentTagBody(MarkupStream markupStream,
			ComponentTag openTag) {
		internal.onComponentTagBody(markupStream, openTag);
	}

	public Component get(String path) {
		return internal.get(path);
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
