package net.unbewaff.wicketcrudr.annotations;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Editor {

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
        PALETTE
    }

    EditorType editAs() default EditorType.DEFAULT;

}
