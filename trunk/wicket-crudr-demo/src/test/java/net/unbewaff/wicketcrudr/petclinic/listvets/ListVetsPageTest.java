/**
 *
 */
package net.unbewaff.wicketcrudr.petclinic.listvets;

import net.unbewaff.petclinic.WicketApplication;
import net.unbewaff.petclinic.listvets.ListVetsPage;
import net.unbewaff.wicketcrudr.components.AutoLister;

import org.apache.log4j.Logger;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author DavidH
 *
 */
public class ListVetsPageTest {

	private static WicketTester tester = new WicketTester(new WicketApplication());
	private final static transient Logger logger = Logger.getLogger(ListVetsPageTest.class);

	@BeforeClass
	public static void setUp() {

	}

	@Test
	public void testRendering() {
		tester.startPage(new ListVetsPage());
		tester.assertRenderedPage(ListVetsPage.class);
		tester.assertComponent("vetsList", AutoLister.class);
		tester.assertComponent("vetsList:lister", DataTable.class);
		tester.debugComponentTrees();
	}

}
