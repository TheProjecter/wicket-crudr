package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import net.unbewaff.wicketcrudr.annotations.EditorType;
import net.unbewaff.wicketcrudr.annotations.member.Order;
import net.unbewaff.wicketcrudr.annotations.member.StringResource;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

public class Property implements Serializable, IProperty {

    private final String property;
    private final boolean readOnly;
    private final int order;
    private final boolean useStringResource;
    private final String stringResourcePrefix;
    private final EditorType editorType;

    public Property(String property, Method method, Map<String, Method> methods, Class<?> clazz) {
        this.property = property;
        readOnly = isReadOnly(property, method, methods);
        order = getOrder(method);
        useStringResource = method.isAnnotationPresent(StringResource.class);
        stringResourcePrefix = getStringResourcePerfix(method);
        editorType = guessEditorType(method.getReturnType());
    }

    /**
     * @param property
     * @param method TODO
     * @param methods
     * @return
     */
    private boolean isReadOnly(String property, Method method, Map<String, Method> methods) {
        return !methods.containsKey("set" + property);
    }

    /**
     * @param sr
     * @return
     */
    private String getStringResourcePerfix(Method m) {
        String key = "";
        if (m.isAnnotationPresent(StringResource.class)) {
            key = m.getAnnotation(StringResource.class).value();
        }
        if (!key.isEmpty()) {
            key = key + ".";
        }
        return key;
    }

    /**
     * @param method
     * @return the order defined on the current Property or -1
     */
    private int getOrder(Method method) {
        int value;
        if (method.isAnnotationPresent(Order.class)) {
            value = method.getAnnotation(Order.class).value();
        } else {
            value = -1;
        }
        return value;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IProperty#getProperty()
     */
    @Override
    public String getProperty() {
        return property;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IProperty#isReadOnly()
     */
    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IProperty#getOrder()
     */
    @Override
    public int getOrder() {
        return order;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IProperty#isUseStringResource()
     */
    @Override
    public boolean isUseStringResource() {
        return useStringResource;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IProperty#getStringResourcePrefix()
     */
    @Override
    public String getStringResourcePrefix() {
        return stringResourcePrefix;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IProperty#isIterable()
     */
    @Override
    public boolean isIterable() {
        return false;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IProperty#getEditorType()
     */
    @Override
    public EditorType getEditorType() {
        return editorType;
    }

    private EditorType guessEditorType(Class returnType) {
        EditorType type = EditorType.TEXTFIELD;
        if (returnType.equals(boolean.class) || Boolean.class.isAssignableFrom(returnType)) {
            type = EditorType.CHECKBOX;
        } else if (Number.class.isAssignableFrom(returnType)) {
            type = EditorType.NUMBER;
        } else if (ICrudrListProvider.class.isAssignableFrom(returnType)) {
            type = EditorType.DROPDOWNCHOICE;
        } else if (Collection.class.isAssignableFrom(returnType)) {
            type = EditorType.PALETTE;
        } else if (Date.class.isAssignableFrom(returnType) || Time.class.isAssignableFrom(returnType)) {
            type = EditorType.DATE;
        }
        return type;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Property [property=").append(property)
				.append(", readOnly=").append(readOnly).append(", editorType=")
				.append(editorType).append("]");
		return builder.toString();
	}

}