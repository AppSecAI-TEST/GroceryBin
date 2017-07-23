package yiji.util.config;
import java.lang.annotation.*;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigableClass
{
	public String value() default "";
}
