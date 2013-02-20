/**
 * 
 */
package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.unbewaff.wicketcrudr.annotations.member.Ignore;
import net.unbewaff.wicketcrudr.annotations.type.Css;
import net.unbewaff.wicketcrudr.annotations.type.LabelResourcePrefix;
import net.unbewaff.wicketcrudr.annotations.type.Prototype;
import net.unbewaff.wicketcrudr.tools.PropertyCleaner;

/**
 * @author DavidH
 * 
 */
public final class PrototypeData implements Serializable {

    private final List<Property> properties = new ArrayList<Property>();
    private final String labelResourcePrefix;
    private final String css;

    public PrototypeData(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Prototype.class)) {
            throw new IllegalArgumentException(clazz.getName() + " is no @Prototype");
        }

        labelResourcePrefix = getLabelResourcePrefix(clazz);

        css = getCss(clazz);

        Map<String, Method> methods = scanMethods(clazz);

        for (Entry<String, Method> entry : methods.entrySet()) {
            if (!entry.getKey().startsWith("s")) {
                properties.add(new Property(PropertyCleaner.getCleanPropertyName(entry.getKey()), entry.getValue(), methods, clazz));
            }
        }

        //TODO sort
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
    protected Map<String, Method> scanMethods(Class<?> clazz) {
        Map<String, Method> methods = new HashMap<String, Method>();
        for (Method m : clazz.getMethods()) {
            String name = m.getName();
            if (!m.isAnnotationPresent(Ignore.class) && (name.startsWith("get") || name.startsWith("is") || name.startsWith("set"))) {
                methods.put(name, m);
            }
        }
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

    /**
     * @return an immutable copy of the Properties present in the Prototype
     */
    public List<Property> getProperties() {
        return Collections.unmodifiableList(properties);
    }

    /**
     * @return the labelResourcePrefix defined in the Prototype
     */
    public String getLabelResourcePrefix() {
        return labelResourcePrefix;
    }

    /**
     * @return the css
     */
    public String getCss() {
        return css;
    }

}
