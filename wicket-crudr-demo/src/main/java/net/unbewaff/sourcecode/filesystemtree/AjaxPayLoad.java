package net.unbewaff.sourcecode.filesystemtree;

import java.io.File;

import org.apache.wicket.ajax.AjaxRequestTarget;

final class AjaxPayLoad {
	private final AjaxRequestTarget target;
	private final File file;

	public AjaxPayLoad(AjaxRequestTarget target, File file) {
		super();
		this.target = target;
		this.file = file;
	}

	/**
	 * @return the target
	 */
	public AjaxRequestTarget getTarget() {
		return target;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
}