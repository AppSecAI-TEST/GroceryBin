package yiji.util.config.fieldsetter;
import java.lang.reflect.*;
import yiji.util.config.FieldSetter;
public class StringFieldSetter implements FieldSetter
{
@Override
public void setValue(Object obj,Field field, String str)throws IllegalAccessException
{
	field.set(obj,str);
}

@Override
public String getValue(Object obj, Field field) throws IllegalAccessException
{
	return (String)field.get(obj);
}

}
