package net.unbewaff.wicketcrudr.annotations;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;

public enum EditorType implements Serializable {
    /**
     * Determines the editor matching to the type of the property
     */
    DEFAULT,
    /**
     * Edit by TextField
     */
    TEXTFIELD,
    /**
     * Edit by TextArea
     */
    TEXTAREA,
    /**
     * Edit by CheckBox (only for {@link Boolean} properties)
     */
    CHECKBOX,
    /**
     * Edit by AjaxLink (only for {@link Boolean} properties)
     */
    AJAXLINK,
    /**
     * Edit by DropDownChoice (only for properties of any types implementing {@link ICrudrDataProvider}
     */
    DROPDOWNCHOICE,
    /**
     * Edit by Palette (for Collection-type members)
     */
    PALETTE,
    /**
     * Edit by DatePicker
     */
    DATE,
    /**
     * For editing Passwords
     */
    PASSWORD,
    /**
     * For numerical Values
     */
    NUMBER
}