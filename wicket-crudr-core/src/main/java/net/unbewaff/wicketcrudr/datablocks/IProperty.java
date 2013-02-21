package net.unbewaff.wicketcrudr.datablocks;

import net.unbewaff.wicketcrudr.annotations.EditorType;

public interface IProperty {

    /**
     * @return the property
     */
    public abstract String getProperty();

    /**
     * @return the readOnly
     */
    public abstract boolean isReadOnly();

    /**
     * @return the order
     */
    public abstract int getOrder();

    /**
     * @return the useStringResource
     */
    public abstract boolean isUseStringResource();

    /**
     * @return the stringResourcePrefix
     */
    public abstract String getStringResourcePrefix();

    /**
     * @return the iterable
     */
    public abstract boolean isIterable();

    /**
     * @return the editorType
     */
    public abstract EditorType getEditorType();

}