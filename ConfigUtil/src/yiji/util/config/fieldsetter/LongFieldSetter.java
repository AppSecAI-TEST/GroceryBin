package yiji.util.config.fieldsetter;
import java.lang.reflect.*;
import yiji.util.config.FieldSetter;
public class LongFieldSetter implements FieldSetter
{
@Override
public void setValue(Object obj,Field field, String str)throws IllegalAccessException
{
	field.setLong(obj,Long.parseLong(str));
}

@Override
public String getValue(Object obj, Field field) throws IllegalAccessException
{
	return field.getLong(obj)+"";
}

}
