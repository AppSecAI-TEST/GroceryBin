package yiji.util.config.fieldsetter;
import java.lang.reflect.*;
import yiji.util.config.FieldSetter;
public class IntFieldSetter implements FieldSetter
{
@Override
public void setValue(Object obj,Field field, String str)throws IllegalAccessException
{
	field.setInt(obj,Integer.parseInt(str));
}

@Override
public String getValue(Object obj, Field field) throws IllegalAccessException
{
	return field.getInt(obj)+"";
}
}
