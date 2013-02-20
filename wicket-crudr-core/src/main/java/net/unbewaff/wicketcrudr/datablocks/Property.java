package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

import net.unbewaff.wicketcrudr.annotations.member.Order;
import net.unbewaff.wicketcrudr.annotations.member.StringResource;

public final class Property implements Serializable {

    private final String property;
    private final boolean readOnly;
    private final int order;
    private final boolean useStringResource;
    private final String stringResourcePrefix;
    private final boolean iterable;

    public Property(String property, Method method, Map<String, Method> methods, Class<?> clazz) {
        this.property = property;
        readOnly = !methods.containsKey("set" + property);
        order = getOrder(method);
        useStringResource = method.isAnnotationPresent(StringResource.class);
        stringResourcePrefix = getStringResourcePerfix(method);
        iterable = Iterable.class.isAssignableFrom(method.getReturnType());
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

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @return the readOnly
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * @return the useStringResource
     */
    public boolean isUseStringResource() {
        return useStringResource;
    }

    /**
     * @return the stringResourcePrefix
     */
    public String getStringResourcePrefix() {
        return stringResourcePrefix;
    }

    /**
     * @return the iterable
     */
    public boolean isIterable() {
        return iterable;
    }

}