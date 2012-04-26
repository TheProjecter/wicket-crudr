package net.unbewaff.wicketcrudr.annotations;

import java.lang.annotation.Annotation;

import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;

public class TestLister implements net.unbewaff.wicketcrudr.annotations.Lister, Annotation {

    private int position = -1;
    private String headerKey = "";
    private String displayKey = "";
    private boolean editInPlace = false;
    private boolean escapeModelString = false;
    private Class<? extends ILabelProvider> customLabelProvider = ILabelProvider.class;

    public TestLister() {

    }

    /**
     * @param position
     * @param headerKey
     * @param displayKey
     * @param editInPlace
     * @param escapeModelString
     * @param customLabelProvider
     */
    public TestLister(Integer position, String headerKey, String displayKey, Boolean editInPlace,
            Boolean escapeModelString, Class<? extends ILabelProvider> customLabelProvider) {
        if (position != null) {
            this.position = position;
        }
        if (headerKey != null) {
            this.headerKey = headerKey;
        }
        if (displayKey != null) {
            this.displayKey = displayKey;
        }
        if (editInPlace != null) {
            this.editInPlace = editInPlace;
        }
        if (escapeModelString != null) {
            this.escapeModelString = escapeModelString;
        }
        if (customLabelProvider != null) {
            this.customLabelProvider = customLabelProvider;
        }
    }


    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public int position() {
        return position;
    }

    @Override
    public String headerKey() {
        return headerKey;
    }

    @Override
    public String displayKey() {
        return displayKey;
    }

    @Override
    public boolean editInPlace() {
        return editInPlace;
    }

    @Override
    public boolean escapeModelString() {
        return escapeModelString;
    }

    @Override
    public Class<? extends ILabelProvider> customLabelProvider() {
        return customLabelProvider;
    }

    @Override
    public int hashCode() {
        final int prime = 127;
        int result = 0;
        result += prime * "customLabelProvider".hashCode() ^ ((customLabelProvider == null) ? 0 : customLabelProvider.hashCode());
        result += prime * "displayKey".hashCode() ^ ((displayKey == null) ? 0 : displayKey.hashCode());
        result += prime * "editInPlace".hashCode() ^ (Boolean.valueOf(editInPlace).hashCode());
        result += prime * "escapeModelString".hashCode() ^ (Boolean.valueOf(escapeModelString).hashCode());
        result += prime * "headerKey".hashCode() ^ ((headerKey == null) ? 0 : headerKey.hashCode());
        result += prime * "position".hashCode() ^ (Integer.valueOf(position).hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TestLister other = (TestLister) obj;
        if (customLabelProvider == null) {
            if (other.customLabelProvider != null)
                return false;
        } else if (!customLabelProvider.equals(other.customLabelProvider))
            return false;
        if (displayKey == null) {
            if (other.displayKey != null)
                return false;
        } else if (!displayKey.equals(other.displayKey))
            return false;
        if (editInPlace != other.editInPlace)
            return false;
        if (escapeModelString != other.escapeModelString)
            return false;
        if (headerKey == null) {
            if (other.headerKey != null)
                return false;
        } else if (!headerKey.equals(other.headerKey))
            return false;
        if (!Integer.valueOf(position).equals(Integer.valueOf(other.position)))
            return false;
        return true;
    }

}