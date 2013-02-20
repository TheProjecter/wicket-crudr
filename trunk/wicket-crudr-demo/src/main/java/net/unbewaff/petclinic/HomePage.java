package net.unbewaff.petclinic;

import net.unbewaff.petclinic.listowner.DisplayOwner;
import net.unbewaff.petclinic.listvets.ListVetsPage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        add(new BookmarkablePageLink<ListVetsPage>("listVets", ListVetsPage.class));
        add(new BookmarkablePageLink<DisplayOwner>("showOwner", DisplayOwner.class));
    }
}
