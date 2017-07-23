package yiji.util.config;
import java.lang.reflect.Field;
public interface FieldSetter
{
	public void setValue(Object obj,Field field,String str)throws IllegalAccessException;
	public String getValue(Object obj,Field field)throws IllegalAccessException
	
}
