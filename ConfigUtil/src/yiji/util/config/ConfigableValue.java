package yiji.util.config;
import java.lang.annotation.*;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigableValue
{
	public String value() default "";
}
