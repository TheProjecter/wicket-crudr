package net.unbewaff.tools;

import java.lang.reflect.Field;
import java.util.Set;

public class BackLinkEditingSet<T, V> extends BacklinkEditingCollection<T, V> implements Set<T> {

	public BackLinkEditingSet(Set<T> data, Field backLink, V parent) {
		super(data, backLink, parent);
	}

}
