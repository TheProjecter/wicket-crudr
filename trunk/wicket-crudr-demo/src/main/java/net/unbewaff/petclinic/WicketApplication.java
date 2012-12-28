package net.unbewaff.petclinic;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see net.unbewaff.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		getResourceSettings().setResourcePollFrequency(null);
		getMarkupSettings().setStripWicketTags(true);
		// add your configuration here
	}

	/**
	 * @see org.apache.wicket.Application#newSession(org.apache.wicket.request.Request,
	 *      org.apache.wicket.request.Response)
	 */
	@Override
	public Session newSession(Request request, Response response) {
		return new WebSession(request);
	}
	
	
}
