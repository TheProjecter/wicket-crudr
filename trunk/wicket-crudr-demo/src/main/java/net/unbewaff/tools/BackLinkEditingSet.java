package net.unbewaff.tools;

import java.lang.reflect.Method;
import java.util.Set;

public class BackLinkEditingSet<T, V> extends BacklinkEditingCollection<T, V> implements Set<T> {

	public BackLinkEditingSet(Set<T> data, Method backlinkSetter, V parent) {
		super(data, backlinkSetter, parent);
	}

}
