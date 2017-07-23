package yiji.util.config.fieldsetter;
import java.lang.reflect.*;
import yiji.util.config.FieldSetter;
public class FloatFieldSetter implements FieldSetter
{
@Override
public void setValue(Object obj,Field field, String str)throws IllegalAccessException
{
	field.setFloat(obj,Float.parseFloat(str));
}

@Override
public String getValue(Object obj, Field field) throws IllegalAccessException
{
	return field.getFloat(obj)+"";
}


}
