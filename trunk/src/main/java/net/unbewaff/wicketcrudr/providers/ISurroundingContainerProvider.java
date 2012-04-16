package net.unbewaff.wicketcrudr.providers;

import java.io.Serializable;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;

public interface ISurroundingContainerProvider extends Serializable {

	public WebMarkupContainer newSurroundingPanel(MarkupContainer parent, String componentId);
	
	public WebMarkupContainer show(AjaxRequestTarget target, WebMarkupContainer container);
	
	public WebMarkupContainer hide(AjaxRequestTarget target, WebMarkupContainer container);
}
