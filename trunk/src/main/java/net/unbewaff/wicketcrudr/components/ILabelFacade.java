package net.unbewaff.wicketcrudr.components;

import org.apache.wicket.util.convert.IConverter;

public interface ILabelFacade {

    /**
     * Implementation that returns null by default (panels don't typically need
     * converters anyway). This is used by the embedded default instances of
     * label and form field to determine whether they should use a converter
     * like they normally would (when this method returns null), or whether they
     * should use a custom converter (when this method is overridden and returns
     * not null).
     */
    public abstract <C> IConverter<C> getConverter(final Class<C> type);

    /**
     * Override this to display a different value when the model object is null.
     * Default is <code>...</code>
     *
     * @return The string which should be displayed when the model object is
     *         null.
     */
    public abstract String defaultNullLabel();

}