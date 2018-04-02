package com.ruifucredit.cloud.kit.po;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

public class PoKit {

	@SuppressWarnings("unchecked")
	public static <T> T join(T from, T to) {

		Object result = BeanUtils.instantiate(to.getClass());

		if (to != null) {
			BeanUtils.copyProperties(to, result, nullNames(to));
		}

		if (from != null) {
			BeanUtils.copyProperties(from, result, nullNames(from));
		}

		return (T) result;
	}

	private static <T> String[] nullNames(T t) {

		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(t.getClass());

		Set<String> names = new HashSet<>();

		for (PropertyDescriptor pd : pds) {

			Object value = ReflectionUtils.invokeMethod(pd.getReadMethod(), t);

			if (value == null)
				names.add(pd.getName());

		}

		return names.toArray(new String[] {});

	}

}
