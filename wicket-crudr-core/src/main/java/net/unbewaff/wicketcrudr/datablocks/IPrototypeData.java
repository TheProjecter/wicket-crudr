package net.unbewaff.wicketcrudr.datablocks;

import java.util.List;

public interface IPrototypeData {

    /**
     * @return an immutable copy of the Properties present in the Prototype
     */
    public abstract List<Property> getProperties();

    /**
     * @return the labelResourcePrefix defined in the Prototype
     */
    public abstract String getLabelResourcePrefix();

    /**
     * @return the css
     */
    public abstract String getCss();

    /**
     * @return the resourceKeyProperty
     */
    public abstract Property getResourceKeyProperty();

}