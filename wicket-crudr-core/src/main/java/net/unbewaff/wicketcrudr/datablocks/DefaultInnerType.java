/**
 * 
 */
package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;

/**
 * @author DavidH
 *
 */
public class DefaultInnerType implements InnerPrototype {
	
	private Class<? extends Serializable> clazz;

	public DefaultInnerType(Class<? extends Serializable> clazz) {
		this.clazz = clazz;
	}
	/* (non-Javadoc)
	 * @see java.lang.annotation.Annotation#annotationType()
	 */
	@Override
	public Class<? extends Annotation> annotationType() {
		return null;
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.annotations.InnerType#resourcePrefix()
	 */
	@Override
	public String resourcePrefix() {
		return "";
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.annotations.InnerType#type()
	 */
	@Override
	public Class<?> value() {
		return clazz;
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.annotations.InnerType#separator()
	 */
	@Override
	public String separator() {
		return "<br />";
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.annotations.InnerType#displayAs()
	 */
	@Override
	public DisplayType displayAs() {
		return DisplayType.LIST;
	}

}
