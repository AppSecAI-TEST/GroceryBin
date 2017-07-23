package yiji.util.config.fieldsetter;
import java.lang.reflect.*;
import yiji.util.config.FieldSetter;
public class BooleanFieldSetter implements FieldSetter
{
@Override
public void setValue(Object obj,Field field, String str)throws IllegalAccessException
{
	field.setBoolean(obj,Boolean.parseBoolean(str));
}

@Override
public String getValue(Object obj, Field field) throws IllegalAccessException
{
	return field.getBoolean(obj)+"";
}

}
