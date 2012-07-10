/**
 *
 */
package net.unbewaff.wicketcrudr.events;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Payload container for throwing Ajax-based events
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class AjaxEventPayload implements Serializable {

    private static final long serialVersionUID = 8993653600871469016L;
    final private AjaxRequestTarget target;

    /**
     * @param target
     */
    public AjaxEventPayload(AjaxRequestTarget target) {
        this.target = target;
    }

    /**
     * @return the target
     */
    public AjaxRequestTarget getTarget() {
        return target;
    }

}
