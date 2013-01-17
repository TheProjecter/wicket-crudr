/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.member.DisplayType;
import net.unbewaff.wicketcrudr.annotations.member.Ignore;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.type.Prototype;
import net.unbewaff.wicketcrudr.tools.OrderIndexComparator;

import org.apache.log4j.Logger;


/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class LabelModelProviderFactory {

    private final static transient Logger logger = Logger.getLogger(LabelModelProviderFactory.class);

    private LabelModelProviderFactory() {
        //static use only
    }

    /**
     * @param <T>
     * @param cleanProperty
     * @param d The DisplayType Annotation defining the Metadata
     * @return
     */
    public static <T> ILabelModelProvider<T> getLabelModelProvider(String cleanProperty, DisplayType d) {
        ILabelModelProvider<T> provider;
        if (d != null && d.value().equals(DisplayType.Display.RESOURCE)) {
            provider = new StringResourcePropertyModelProvider<T>(cleanProperty, d.resourcePrefix());
        } else {
            provider = new PropertyModelProvider<T>(cleanProperty);
        }
        return provider;
    }

    public static <T> ILabelModelProvider<T> getLabelModelProvider(String resourcePrefix, InnerPrototype innerType) {
        ILabelModelProvider<T> labelModelProvider;
        List<Method> methods = new ArrayList<Method>();
        for (Method m :innerType.type().getMethods()) {
            String name = m.getName();
            if (m.getDeclaringClass().isAnnotationPresent(Prototype.class)) {
                if (!m.isAnnotationPresent(Ignore.class) && (name.startsWith("get") || name.startsWith("is"))) {
                    methods.add(m);
                }
            }
        }

        Collections.sort(methods, new OrderIndexComparator());

        if (methods.size() > 0) {
            labelModelProvider = new ConcatenatedLabelModelProvider<T>(methods, " ");
        } else {
            labelModelProvider = new StringResourceModelProvider<T>(resourcePrefix, innerType);
        }

        return labelModelProvider;
    }

}
