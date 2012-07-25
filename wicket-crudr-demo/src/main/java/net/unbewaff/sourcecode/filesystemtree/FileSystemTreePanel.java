/**
 *
 */
package net.unbewaff.sourcecode.filesystemtree;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.tree.BaseTree;
import org.apache.wicket.markup.html.tree.LinkTree;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author davidh
 *
 */
public class FileSystemTreePanel extends Panel implements Serializable {

	private File root;
	private List<String> validExtensions = Arrays.asList(new String[]{".java", ".xml", ".html"});
	private String path;

	/**
	 * @param id
	 */
	public FileSystemTreePanel(String id, String rootDir) {
		super(id);
		root = new File(rootDir);
		if (!root.isDirectory()) {
			throw (new IllegalArgumentException("Rootdir has to be a directory"));
		}
		path = root.getPath();
	}

	public FileSystemTreePanel(String id, URL url) {
		super(id);
		root = new File(url.getFile());
		if (!root.isDirectory()) {
			throw (new IllegalArgumentException("Rootdir has to be a directory"));
		}
		path = root.getPath();
	}

	/**
	 * @param id
	 * @param model
	 */
	public FileSystemTreePanel(String id, IModel<?> model) {
		super(id, model);
	}

	@Override
	protected void onInitialize() {
		DefaultMutableTreeNode rootNode = processDir(new DefaultMutableTreeNode(root), root);
		LinkTree tree = new LinkTree("tree", Model.of(new DefaultTreeModel(rootNode))) {

			private static final long serialVersionUID = 8728090149423130901L;

			/*
			 * (non-Javadoc)
			 *
			 * @see
			 * org.apache.wicket.markup.html.tree.LinkTree#onNodeLinkClicked
			 * (java.lang.Object, org.apache.wicket.markup.html.tree.BaseTree,
			 * org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			@Override
			protected void onNodeLinkClicked(Object node, BaseTree tree, AjaxRequestTarget target) {
				if (node instanceof DefaultMutableTreeNode) {
					if (((DefaultMutableTreeNode) node).getUserObject() instanceof File) {
						File f = (File) ((DefaultMutableTreeNode) node).getUserObject();
						send(getPage(), Broadcast.BREADTH, new AjaxPayLoad(target, f));
					}
				}
			};

			@Override
			protected IModel<?> getNodeTextModel(IModel<?> nodeModel) {
				Object o = nodeModel.getObject();
				IModel<?> retValue = super.getNodeTextModel(nodeModel);
				if (o instanceof DefaultMutableTreeNode) {
					o = ((DefaultMutableTreeNode) o).getUserObject();
					if (o instanceof File) {
						File f = (File) o;
						retValue = Model.of(f.getName());

					}
				}
				return retValue;
			}
		};
		tree.getTreeState().expandAll();
		add(tree);
		super.onInitialize();
	}

	/**
	 * @param rootNode
	 * @return
	 */
	private DefaultMutableTreeNode processDir(DefaultMutableTreeNode rootNode, final File file) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
		rootNode.add(node);
		List<String> fileList = new ArrayList<String>(Arrays.asList(file.list()));
		Collections.sort(fileList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				String path = file.getPath();
				File f1 = new File(path + "/" + o1);
				File f2 = new File(path + "/" + o2);
				int compareTo = o1.compareTo(o2);
				if (f1.isDirectory()) {
					if (!f2.isDirectory()) {
						compareTo = -1;
					}
				} else {
					if (f2.isDirectory()) {
						compareTo = 1;
					}
				}
				return compareTo;
			}

		});
		for (String f : fileList) {
			rootNode = process(node, new File(path + "/" + f));
		}
		return rootNode;
	}

	private DefaultMutableTreeNode process(DefaultMutableTreeNode parent, File file) {
		if (file.isDirectory()) {
			processDir(parent, file);
		} else {
			if (file.getName().contains(".") && validExtensions.contains(file.getName().substring(file.getName().lastIndexOf(".")))) {
				parent.add(new DefaultMutableTreeNode(file));
			}
		}
		return parent;
	}
}
