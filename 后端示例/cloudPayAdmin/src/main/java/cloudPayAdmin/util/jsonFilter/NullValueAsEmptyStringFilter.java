package cloudPayAdmin.util.jsonFilter;

import com.alibaba.fastjson.serializer.ValueFilter;

public class NullValueAsEmptyStringFilter implements ValueFilter {

	@Override
	public Object process(Object object, String name, Object value) {
		
		if(value == null) {
			return "";
		} else {
			return value;
		}
		
		
	}

}
