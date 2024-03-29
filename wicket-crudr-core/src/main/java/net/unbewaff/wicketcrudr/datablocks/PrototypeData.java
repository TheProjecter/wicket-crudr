/**
 * 
 */
package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.unbewaff.wicketcrudr.annotations.member.Ignore;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.member.ResourceKey;
import net.unbewaff.wicketcrudr.annotations.type.Css;
import net.unbewaff.wicketcrudr.annotations.type.LabelResourcePrefix;
import net.unbewaff.wicketcrudr.annotations.type.Prototype;
import net.unbewaff.wicketcrudr.tools.PropertyCleaner;

/**
 * @author DavidH
 * 
 */
public final class PrototypeData implements Serializable, IPrototypeData {

    private static final long serialVersionUID = -5424894181097200308L;
    private final List<IProperty> properties = new ArrayList<IProperty>();
    private final String labelResourcePrefix;
    private final String css;
    private final IProperty resourceKeyProperty;

    public PrototypeData(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Prototype.class)) {
            throw new IllegalArgumentException(clazz.getName() + " is no @Prototype");
        }

        labelResourcePrefix = getLabelResourcePrefix(clazz);

        css = getCss(clazz);

        Map<String, Method> methods = scanMethods(clazz);

        IProperty resourceKey = null;
        for (Entry<String, Method> entry : methods.entrySet()) {
            if (!entry.getKey().startsWith("s")) {
                Property property = createProperty(methods, entry.getKey(), clazz);
                properties.add(property);
                if (resourceKey == null && entry.getValue().isAnnotationPresent(ResourceKey.class)) {
                    resourceKey = property;
                }
            }
        }

        resourceKeyProperty = resourceKey;

        Collections.sort(properties, new Comparator<IProperty>() {

            @Override
            public int compare(IProperty o1, IProperty o2) {
                return o1.getOrder() - o2.getOrder();
            }
        });
    }

    /**
     * @param methods Map of methodNames - method objects
     * @param methodName the current name
     * @param clazz the currently inspected class
     * @return a property object representing the property defined by methodName
     */
    private Property createProperty(Map<String, Method> methods, String methodName, Class<?> clazz) {
        Method method = methods.get(methodName);
        Property property;
        if (Iterable.class.isAssignableFrom(method.getReturnType())) {
            property = new IterableProperty(PropertyCleaner.getCleanPropertyName(methodName), method, methods, clazz, method.getAnnotation(InnerPrototype.class));
        } else {
            property = new Property(PropertyCleaner.getCleanPropertyName(methodName), method, methods, clazz);
        }
        return property;
    }

    /**
     * @param clazz
     * @return the blank-prefixed value of the css class defined in the Prototype or an empty String
     */
    private String getCss(Class<?> clazz) {
        String css;
        if (clazz.isAnnotationPresent(Css.class)) {
            css = " " + clazz.getAnnotation(Css.class).value();
        } else {
            css = "";
        }
        return css;
    }

    /**
     * @param clazz
     * @return
     */
    private Map<String, Method> scanMethods(Class<?> clazz) {
        Map<String, Method> methods = new HashMap<String, Method>();
        Class<?> currentClass = clazz;
        do {
            for (Method m : currentClass.getDeclaredMethods()) {
                String name = m.getName();
                if (!m.isAnnotationPresent(Ignore.class) && (name.startsWith("get") || name.startsWith("is") || name.startsWith("set"))) {
                    methods.put(name, m);
                }
            }
            currentClass = currentClass.getSuperclass();
        } while (currentClass.isAnnotationPresent(Prototype.class));
        return methods;
    }

    /**
     * @param clazz
     */
    private String getLabelResourcePrefix(Class<?> clazz) {
        String prefix;
        if (clazz.isAnnotationPresent(LabelResourcePrefix.class)) {
            prefix = clazz.getAnnotation(LabelResourcePrefix.class).value();
        } else {
            prefix = clazz.getName();
        }
        return prefix + ".";
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IPrototypeData#getProperties()
     */
    @Override
    public List<IProperty> getProperties() {
        return Collections.unmodifiableList(properties);
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IPrototypeData#getLabelResourcePrefix()
     */
    @Override
    public String getLabelResourcePrefix() {
        return labelResourcePrefix;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IPrototypeData#getCss()
     */
    @Override
    public String getCss() {
        return css;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IPrototypeData#getResourceKeyProperty()
     */
    @Override
    public IProperty getResourceKeyProperty() {
        return resourceKeyProperty;
    }

    @Override
    public String toString() {
        final int maxLen = 10;
        StringBuilder builder = new StringBuilder();
        builder.append("PrototypeData [properties=")
        .append(properties != null ? properties.subList(0,
                Math.min(properties.size(), maxLen)) : null)
                .append(", labelResourcePrefix=").append(labelResourcePrefix)
                .append(", css=").append(css).append(", resourceKeyProperty=")
                .append(resourceKeyProperty).append("]");
        return builder.toString();
    }

}
