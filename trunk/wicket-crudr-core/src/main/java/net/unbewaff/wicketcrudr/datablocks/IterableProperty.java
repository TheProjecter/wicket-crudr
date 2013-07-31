/**
 * 
 */
package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype.DisplayType;
import net.unbewaff.wicketcrudr.annotations.type.Prototype;

/**
 * @author DavidH
 *
 */
public class IterableProperty extends Property implements Serializable {

    private static final long serialVersionUID = -2384742374238089291L;
    private final IPrototypeData prototype;
    private final String stringResourcePrefix;
    private final DisplayType displayType;
    private final String separator;

    public IterableProperty(String property, Method method, Map<String, Method> methods, Class<?> clazz, InnerPrototype innerPrototype) {
        super(property, method, methods, clazz);
        Class<?> value = innerPrototype.value();
        if (value.isAnnotationPresent(Prototype.class)) {
            this.prototype = new PrototypeData(value);
        } else {
            //TODO figure out
            this.prototype = null;
        }
        stringResourcePrefix = innerPrototype.resourcePrefix();
        displayType = innerPrototype.displayAs();
        separator = innerPrototype.separator();
    }

    @Override
    public boolean isIterable() {
        return true;
    }

    /**
     * @return the innerPrototype
     */
    public IPrototypeData getInnerPrototype() {
        return prototype;
    }

    /**
     * @return the stringResourcePrefix
     */
    @Override
    public String getStringResourcePrefix() {
        return stringResourcePrefix;
    }

    /**
     * @return the displayType
     */
    public DisplayType getDisplayType() {
        return displayType != null? displayType : DisplayType.LIST;
    }

    /**
     * @return the separator
     */
    public String getSeparator() {
        return separator != null? separator : "";
    }



}
