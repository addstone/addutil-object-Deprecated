package com.soucod.addutil.object;

/**
 * @author addstone
 */
public class ObjectUtil {

 
  
    /**
     * 将Map对象里面的属性和值转化成Object对象
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
        Map<String, Object> map = new HashMap<String, Object>();    
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
        return map;  
    }   

    /**
     * 将Map对象里面的属性和值转化成Object对象
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null){
            return null; 
        }  
        Object obj = beanClass.newInstance();  
        Field[] fields = obj.getClass().getDeclaredFields();   
        for (Field field : fields) {    
            int mod = field.getModifiers();    
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                continue;    
            }    
            field.setAccessible(true);    
            field.set(obj, map.get(field.getName()));   
        }   
        return obj;    
    }   

}
