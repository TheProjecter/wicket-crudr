/**
 * 
 */
package net.unbewaff.wicketcrudr.annotations.type;

import java.io.Serializable;

/**
 * Defines the way a Prototype annotated entity is displayed. 
 * 
 * LONG uses a fact sheet layout displaying the data as key-value-pairs
 * SHORT uses a tabular layout arranging the data in collumns
 * DEFAULT uses LONG if the class is the root of a data tree and SHORT if not.
 * 
 * @author David Hendrix (Nicktarix)
 *
 */
public enum Prototypes implements Serializable {
	LONG,
	SHORT,
	DEFAULT
}
