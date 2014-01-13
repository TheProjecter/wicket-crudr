package net.unbewaff.wicketcrudr.datablocks;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.EditorType;

public class SingleValuePrototypeData implements IPrototypeData {
    private List<IProperty> properties = new ArrayList<IProperty>();

    public SingleValuePrototypeData(Class<?> inner, Method m) {
        IProperty property = new Property("StringRepresentation", null, null, null);
        properties.add(property);
    }

    @Override
    public List<IProperty> getProperties() {
        return properties;
    }

    @Override
    public String getLabelResourcePrefix() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getCss() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IProperty getResourceKeyProperty() {
        // TODO Auto-generated method stub
        return null;
    }

    private static class SingleValueProperty implements IProperty {

        @Override
        public String getProperty() {
            return "value";
        }

        @Override
        public boolean isReadOnly() {
            return true;
        }

        @Override
        public int getOrder() {
            return 0;
        }

        @Override
        public boolean isUseStringResource() {
            return false;
        }

        @Override
        public String getStringResourcePrefix() {
            return null;
        }

        @Override
        public boolean isIterable() {
            return false;
        }

        @Override
        public EditorType getEditorType() {
            return null;
        }

    }

}
