package cn.tx.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map map, String[] vals, Class class1) {
		Date date = null;
		if(vals != null && vals.length > 0){
			String dateStr = vals[0];
			if(StringUtils.isNotBlank(dateStr) && class1 == Date.class){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date = sdf.parse(dateStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return date;
	}

	@Override
	public String convertToString(Map map, Object obj) {
		String result = "";
		if(obj != null && (obj.getClass() == Date.class || obj.getClass() == Timestamp.class)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			result = sdf.format(obj);
		}
		return result;
	}

}
