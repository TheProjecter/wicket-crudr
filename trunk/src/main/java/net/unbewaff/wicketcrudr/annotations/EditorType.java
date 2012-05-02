/**
 *
 */
package net.unbewaff.wicketcrudr.annotations;

/**
 * @author davidh
 *
 */
public @interface EditorType {

    public enum Type {
        TextField,
        CheckBox,
        DropDown;
    }

    Type type() default Type.TextField;


}
