package yiji.util.config;
import java.util.*;
import java.lang.reflect.*;
import java.io.*;
import yiji.util.config.fieldsetter.*;
public class ConfigUtil
{
	public ConfigUtil(){
		knowntype.put(int.class,new IntFieldSetter());
		knowntype.put(long.class,new LongFieldSetter());
		knowntype.put(boolean.class,new BooleanFieldSetter());
		knowntype.put(float.class,new FloatFieldSetter());
		knowntype.put(double.class,new DoubleFieldSetter());
		knowntype.put(String.class,new StringFieldSetter());
	}
	private ArrayList<Properties> pset=new ArrayList<Properties>();
	private HashMap<String,Field> fieldmap=new HashMap<String,Field>();
	private HashMap<String,Object> objmap=new HashMap<String,Object>();
	private HashMap<String,Class> fieldtype=new HashMap<String,Class>();
	private HashMap<Class,FieldSetter> knowntype=new HashMap<Class,FieldSetter>();
	public ConfigUtil load(InputStream i)throws IOException{
		Properties p=new Properties();
		p.load(i);
		loadp(p);
		return this;
	}
	public ConfigUtil load(Reader r)throws IOException{
		Properties p=new Properties();
		p.load(r);
		loadp(p);
		return this;
	}
	public ConfigUtil loadFromXML(InputStream i)throws IOException{
		Properties p=new Properties();
		p.loadFromXML(i);
		loadp(p);
		return this;
	}
	private ConfigUtil loadp(Properties p){
		pset.add(p);
		return this;
	}
	public ConfigUtil addClass(Class cls,Object obj){
		assert cls!=null;
		ConfigableClass cc=(ConfigableClass) cls.getAnnotation(ConfigableClass.class);
		assert cc!=null;
		String clsname=cc.value();
		Field[] fields=cls.getFields();
		for(Field field:fields){
			field.setAccessible(true);
			ConfigableValue cv=(ConfigableValue)field.getAnnotation(ConfigableValue.class);
			if(cv==null){
				continue;
			}
			String fieldname=cv.value();
			if(fieldname.charAt(0)=='$'){
				String okfieldname=fieldname.substring(1,fieldname.length());
				putField(okfieldname,field,obj);
			}else{
				putField(clsname+"."+fieldname,field,obj);
			}
		}
		return this;
	}
	private void putField(String name,Field field,Object obj){
		fieldmap.put(name,field);
		fieldtype.put(name,field.getType());
		if(obj!=null){
			objmap.put(name,obj);
		}
	}
	public ConfigUtil save(){
		return this;
	}
	public ConfigUtil go(){
		for(Properties p:pset){
			Set<String> nset=p.stringPropertyNames();
			for(String name:nset){
				setField(name,p.getProperty(name));
			}
		}
		return this;
	}
	private void setField(String name,String value){
		try{
			FieldSetter fs=knowntype.get(fieldtype.get(name));
			fs.setValue(objmap.get(name),fieldmap.get(name),value);
		}catch(IllegalAccessException ex){
			throw new RuntimeException(ex);
		}
	}
	private String getField(String name,String value){
		try{
			return knowntype.get(fieldtype.get(name)).getValue(objmap.get(name),fieldmap.get(name));
		}catch(IllegalAccessException ex){
			throw new RuntimeException(ex);
		}
	}
}
