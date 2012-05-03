package net.unbewaff.wicketcrudr.annotations;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
         * Edit by CheckBox (only for Boolean properties)
         */
        CHECKBOX,
        /**
         * Edit by AjaxLink (only for Boolean properties)
         */
        AJAXLINK
    }

    EditorType editAs() default EditorType.DEFAULT;

}
