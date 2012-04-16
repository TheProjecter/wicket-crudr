/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unbewaff.wicketcrudr.labels;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.JavaScriptUtils;
import org.apache.wicket.validation.IValidator;


/**
 * An implementation of ajaxified edit-in-place component using a {@link Palette} as it's editor.
 * <p>
 * There are several methods that can be overridden for customization.
 * <ul>
 * <li>{@link #onEdit(AjaxRequestTarget)} is called when the label is clicked and the editor is to
 * be displayed. The default implementation switches the label for the editor and places the caret
 * at the end of the text.</li>
 * <li>{@link #onSubmit(AjaxRequestTarget)} is called when in edit mode, the user submitted new
 * content, that content validated well, and the model value successfully updated. This
 * implementation also clears any <code>window.status</code> set.</li>
 * <li>{@link #onError(AjaxRequestTarget)} is called when in edit mode, the user submitted new
 * content, but that content did not validate. Get the current input by calling
 * {@link FormComponent#getInput()} on {@link #getEditor()}, and the error message by calling:
 *
 * <pre>
 * String errorMessage = editor.getFeedbackMessage().getMessage();
 * </pre>
 *
 * The default implementation of this method displays the error message in
 * <code>window.status</code>, redisplays the editor, selects the editor's content and sets the
 * focus on it.
 * <li>{@link #onCancel(AjaxRequestTarget)} is called when in edit mode, the user choose not to
 * submit the contents (he/she pressed escape). The default implementation displays the label again
 * without any further action.</li>
 * </ul>
 * </p>
 *
 * @author Igor Vaynberg (ivaynberg)
 * @author Eelco Hillenius
 * @author David Hendrix
 * @param <T>
 */
// TODO wonder if it makes sense to refactor this into a formcomponentpanel
public class AjaxEditablePaletteLabel<T> extends Panel
{
	private static final long serialVersionUID = 1L;

	/** editor component. */
	private Palette<T> editor;

	/** label component. */
	private Component label;

	protected class EditorAjaxBehavior extends AbstractDefaultAjaxBehavior
	{
		private static final long serialVersionUID = 1L;

		/**
		 * Constructor.
		 */
		public EditorAjaxBehavior()
		{
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onComponentTag(final ComponentTag tag)
		{
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
		protected void respond(final AjaxRequestTarget target)
		{
			RequestCycle requestCycle = RequestCycle.get();
			boolean save = requestCycle.getRequest()
				.getRequestParameters()
				.getParameterValue("save")
				.toBoolean(false);

			if (save)
			{
				editor.getRecorderComponent().processInput();

				if (editor.getRecorderComponent().isValid())
				{
					onSubmit(target);
				}
				else
				{
					onError(target);
				}
			}
			else
			{
				onCancel(target);
			}
		}
	}

	protected class LabelAjaxBehavior extends AjaxEventBehavior
	{
		private static final long serialVersionUID = 1L;

		/**
		 * Construct.
		 *
		 * @param event
		 */
		public LabelAjaxBehavior(final String event)
		{
			super(event);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void onEvent(final AjaxRequestTarget target)
		{
			onEdit(target);
		}
	}

	/**
	 * Constructor
	 *
	 * @param id
	 */
	public AjaxEditablePaletteLabel(final String id)
	{
		super(id);
		setOutputMarkupId(true);
	}

	/**
	 * Constructor
	 *
	 * @param id
	 * @param model
	 */
	public AjaxEditablePaletteLabel(final String id, final IModel<List<T>> model)
	{
		super(id, model);
		setOutputMarkupId(true);
	}

	/**
	 * Adds a validator to this form component. A model must be available for this component before
	 * Validators can be added. Either add this Component to its parent (already having a Model), or
	 * provide one before this call via constructor {@link #AjaxEditableLabel(String,IModel)} or
	 * {@link #setDefaultModel(IModel)}.
	 *
	 * @param validator
	 *            The validator
	 * @return This
	 */
	public final AjaxEditablePaletteLabel<T> add(final IValidator<T> validator)
	{
		FormComponent<T> recorderComponent = getFormComponent();
		recorderComponent.add((IValidator<? super Object>) validator);
		return this;
	}

	/**
	 * @return
	 */
	public FormComponent<T> getFormComponent() {
		return ((FormComponent<T>)getEditor().getRecorderComponent());
	}

	/**
	 * Implementation that returns null by default (panels don't typically need converters anyway).
	 * This is used by the embedded default instances of label and form field to determine whether
	 * they should use a converter like they normally would (when this method returns null), or
	 * whether they should use a custom converter (when this method is overridden and returns not
	 * null).
	 */
	@Override
	public <C> IConverter<C> getConverter(final Class<C> type)
	{
		return null;
	}

	/**
	 * The value will be made available to the validator property by means of ${label}. It does not
	 * have any specific meaning to FormComponent itself.
	 *
	 * @param labelModel
	 * @return this for chaining
	 */
	public final AjaxEditablePaletteLabel<T> setLabel(final IModel<String> labelModel)
	{
		getFormComponent().setLabel(labelModel);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final AjaxEditablePaletteLabel<T> setDefaultModel(final IModel<?> model)
	{
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
	public final AjaxEditablePaletteLabel<T> setRequired(final boolean required)
	{
		getFormComponent().setRequired(required);
		return this;
	}

	/**
	 * Sets the type that will be used when updating the model for this component. If no type is
	 * specified String type is assumed.
	 *
	 * @param type
	 * @return this for chaining
	 */
	public final AjaxEditablePaletteLabel<T> setType(final Class<?> type)
	{
		getFormComponent().setType(type);
		return this;
	}

	/**
	 * Create a new form component instance to serve as editor.
	 *
	 * @param parent
	 *            The parent component
	 * @param componentId
	 *            Id that should be used by the component
	 * @param model
	 *            The model
	 * @return The editor
	 */
	protected Palette<T> newEditor(final MarkupContainer parent, final String componentId, final IModel<List<T>> model) {
		IModel<Collection<T>> list = null;
		IChoiceRenderer<T> renderer = null;
		Palette<T> editor = new Palette<T>(componentId, model, list, renderer, 10, false) {
			private static final long serialVersionUID = 1L;

			@Override
			public <C> IConverter<C> getConverter(final Class<C> type)
			{
				IConverter<C> c = AjaxEditablePaletteLabel.this.getConverter(type);
				return c != null ? c : super.getConverter(type);
			}

			@Override
			protected void onModelChanged()
			{
				super.onModelChanged();
				AjaxEditablePaletteLabel.this.onModelChanged();
			}

			@Override
			protected void onModelChanging()
			{
				super.onModelChanging();
				AjaxEditablePaletteLabel.this.onModelChanging();
			}
		};
		editor.setOutputMarkupId(true);
		editor.setVisible(false);
		editor.add(new EditorAjaxBehavior());
		return editor;
	}

	/**
	 * Create a new form component instance to serve as label.
	 *
	 * @param parent
	 *            The parent component
	 * @param componentId
	 *            Id that should be used by the component
	 * @param model
	 *            The model
	 * @return The editor
	 */
	protected Component newLabel(final MarkupContainer parent, final String componentId,
		final IModel<String> model)
	{
		Label label = new Label(componentId, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public <C> IConverter<C> getConverter(final Class<C> type)
			{
				IConverter<C> c = AjaxEditablePaletteLabel.this.getConverter(type);
				return c != null ? c : super.getConverter(type);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onComponentTagBody(final MarkupStream markupStream,
				final ComponentTag openTag)
			{
				Object modelObject = getDefaultModelObject();
				if ((modelObject == null) || "".equals(modelObject))
				{
					replaceComponentTagBody(markupStream, openTag, defaultNullLabel());
				}
				else
				{
					super.onComponentTagBody(markupStream, openTag);
				}
			}
		};
		label.setOutputMarkupId(true);
		label.add(new LabelAjaxBehavior(getLabelAjaxEvent()));
		return label;
	}

	/**
	 * By default this returns "onclick" uses can overwrite this on which event the label behavior
	 * should be triggered
	 *
	 * @return The event name
	 */
	protected String getLabelAjaxEvent()
	{
		return "onclick";
	}


	/**
	 * Gets the editor component.
	 *
	 * @return The editor component
	 */
	protected final Palette<T> getEditor()
	{
		if (editor == null)
		{
			initLabelAndEditor(getDelegatingParentModel());
		}
		return editor;
	}

	/**
	 * Gets the label component.
	 *
	 * @return The label component
	 */
	protected final Component getLabel()
	{
		if (label == null)
		{
			initLabelAndEditor(getDelegatingParentModel());
		}
		return label;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onBeforeRender()
	{
		super.onBeforeRender();
		// lazily add label and editor
		if (editor == null)
		{
			initLabelAndEditor(getDelegatingParentModel());
		}
		// obsolete with WICKET-1919
		// label.setEnabled(isEnabledInHierarchy());
	}

	/**
	 * Invoked when the label is in edit mode, and received a cancel event. Typically, nothing
	 * should be done here.
	 *
	 * @param target
	 *            the ajax request target
	 */
	protected void onCancel(final AjaxRequestTarget target)
	{
		label.setVisible(true);
		editor.setVisible(false);
		target.add(AjaxEditablePaletteLabel.this);
	}

	/**
	 * Called when the label is clicked and the component is put in edit mode.
	 *
	 * @param target
	 *            Ajax target
	 */
	public void onEdit(final AjaxRequestTarget target)
	{
		label.setVisible(false);
		editor.setVisible(true);
		target.add(AjaxEditablePaletteLabel.this);
		// put focus on the textfield and stupid explorer hack to move the
		// caret to the end
		target.appendJavaScript("{ var el=wicketGet('" + editor.getMarkupId() + "');" +
			"   if (el.createTextRange) { " +
			"     var v = el.value; var r = el.createTextRange(); " +
			"     r.moveStart('character', v.length); r.select(); } }");
		target.focusComponent(editor);
	}

	/**
	 * Invoked when the label is in edit mode, received a new input, but that input didn't validate
	 *
	 * @param target
	 *            the ajax request target
	 */
	protected void onError(final AjaxRequestTarget target)
	{
		Serializable errorMessage = editor.getFeedbackMessage().getMessage();
		if (errorMessage != null)
		{
			target.appendJavaScript("window.status='" +
				JavaScriptUtils.escapeQuotes(errorMessage.toString()) + "';");
		}
		target.appendJavaScript("{var el=wicketGet('" + editor.getMarkupId() +
			"'); el.select(); el.focus();}");
	}

	/**
	 * Invoked when the editor was successfully updated. Use this method e.g. to persist the changed
	 * value. This implementation displays the label and clears any window status that might have
	 * been set in onError.
	 *
	 * @param target
	 *            The ajax request target
	 */
	protected void onSubmit(final AjaxRequestTarget target)
	{
		label.setVisible(true);
		editor.setVisible(false);
		target.add(AjaxEditablePaletteLabel.this);

		target.appendJavaScript("window.status='';");
	}

	/**
	 * Lazy initialization of the label and editor components and set tempModel to null.
	 *
	 * @param model
	 *            The model for the label and editor
	 */
	private void initLabelAndEditor(final IModel<List<T>> model)
	{
		editor = newEditor(this, "editor", model);
		label = newLabel(this, "label", getStringModel(model));
		add(label);
		add(editor);
	}

	private IModel<String> getStringModel(IModel<List<T>> model) {
		String separator = "";
		StringBuilder sb = new StringBuilder();
		for (T t : model.getObject()) {
			sb.append(separator).append(t);
			separator = "\n";
		}
		return Model.of(sb.toString());
	}

	/**
	 * get a model that accesses the parent model lazily. this is required since we eventually
	 * request the parents model before the component is added to the parent.
	 *
	 * @return model
	 */
	private IModel<List<T>> getDelegatingParentModel()
	{
		return new IModel<List<T>>()
		{
			private static final long serialVersionUID = 1L;

			public List<T> getObject()
			{
				return getParentModel().getObject();
			}

			public void setObject(final List<T> object)
			{
				getParentModel().setObject(object);
			}

			public void detach()
			{
				getParentModel().detach();
			}
		};
	}


	/**
	 * @return Gets the parent model in case no explicit model was specified.
	 */
	@SuppressWarnings("unchecked")
	private IModel<List<T>> getParentModel()
	{
		// the #getModel() call below will resolve and assign any inheritable
		// model this component can use. Set that directly to the label and
		// editor so that those components work like this enclosing panel
		// does not exist (must have that e.g. with CompoundPropertyModels)
		IModel<List<T>> m = (IModel<List<T>>)getDefaultModel();

		// check that a model was found
		if (m == null)
		{
			Component parent = getParent();
			String msg = "No model found for this component, either pass one explicitly or "
				+ "make sure an inheritable model is available.";
			if (parent == null)
			{
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
	 * Override this to display a different value when the model object is null. Default is
	 * <code>...</code>
	 *
	 * @return The string which should be displayed when the model object is null.
	 */
	protected String defaultNullLabel()
	{
		return "...";
	}

	/**
	 * Dummy override to fix WICKET-1239
	 */
	@Override
	protected void onModelChanged()
	{
		super.onModelChanged();
	}

	/**
	 * Dummy override to fix WICKET-1239
	 */
	@Override
	protected void onModelChanging()
	{
		super.onModelChanging();
	}
}