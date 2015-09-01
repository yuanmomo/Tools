package net.yuanmomo.tools.jfinal;

import java.beans.PropertyDescriptor;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.plugin.activerecord.Model;

public class JfinalBeanUtil {
    private static Logger log = LoggerFactory.getLogger(JfinalBeanUtil.class);
     
    /**
     * 将Model对象的数据转移到Bean对象中<br>
     * Bean中的属性要与Model中的属性名称相同
     * @param t:Bean
     * @param m:Model
     * @return
     * @throws Exception
     */
    public static <T,M extends Model<?>> T revertModel2Bean(M m,T t) throws Exception{
        if (t!=null && m!=null) {
            PropertyDescriptor[] propertyDess = PropertyUtils.getPropertyDescriptors(t);
            if (ArrayUtils.isNotEmpty(propertyDess)) {
                for (PropertyDescriptor propertyDescriptor : propertyDess) {
                    String propertyName = propertyDescriptor.getName();
                    Object mProObj = PropertyUtils.getProperty(m, propertyName);
                    //如果Model中的属性为字符串
                    if (mProObj instanceof String && !propertyName.equals("tableName") 
                    		&& !propertyName.equals("class")) {
                        Object proObj = PropertyUtils.getProperty(t, propertyName);
                        String mproValue = BeanUtils.getProperty(m, propertyName);
                        if (proObj instanceof Integer) {	// Integer
                            Integer intValue =m.getInt(mproValue);
                            if (intValue!=null) {
                                BeanUtils.setProperty(t, propertyName, intValue);
                            }
                        }else if(proObj instanceof Double){//  Double
                            Double dobValue = m.getDouble(mproValue);
                            if (dobValue!=null) {
                                BeanUtils.setProperty(t, propertyName, dobValue);
                            }
                        }else if(proObj instanceof Date){// Date
                            Timestamp timestamp = m.getTimestamp(mproValue);
                            Date datValue = new Date();
                            if (timestamp != null) {
                                datValue = new Date(timestamp.getTime());
                            }
                            if (datValue!=null) {
                                BeanUtils.setProperty(t, propertyName, datValue);
                            }
                        }else if(proObj instanceof String){// String
                            String strValue = m.getStr(mproValue);
                            if(strValue!=null){
                                BeanUtils.setProperty(t, propertyName, strValue);
                            }
                        }else if(proObj instanceof Long){// Long
                            Long longValue = m.getLong(mproValue);
                            if(longValue!=null){
                                BeanUtils.setProperty(t, propertyName, longValue);
                            }
                        }else if(proObj instanceof Float){// Float
                        	Float floatValue = m.getFloat(mproValue);
                            if(floatValue!=null){
                                BeanUtils.setProperty(t, propertyName, floatValue);
                            }
                        }else if(proObj instanceof Short){// Short
                            Short shortValue = m.getLong(mproValue).shortValue();
                            if(shortValue!=null){
                                BeanUtils.setProperty(t, propertyName, shortValue);
                            }
                        }else if(proObj instanceof Byte){// Byte
                        	Byte byteValue = m.getLong(mproValue).byteValue();
                            if(byteValue!=null){
                                BeanUtils.setProperty(t, propertyName, byteValue);
                            }
                        }else if(proObj instanceof Character){// Char
                        	Character charValue = (Character)proObj;
                            if(charValue!=null){
                                BeanUtils.setProperty(t, propertyName, charValue);
                            }
                        }else if(proObj instanceof Boolean){// Boolean
                            Boolean booelanValue = m.getBoolean(mproValue);
                            if(booelanValue!=null){
                                BeanUtils.setProperty(t, propertyName, booelanValue);
                            }
                        }else {// 
                            log.info("不支持的Bean数据类型，请修改代码。");
                        }
                    }else {
                        log.info("不支持Model的属性");
                    }
                }
                return t;
            }
        }
        return null;
    }
     
    /**
     * 将Bean中值转移到Model中
     * @param t
     * @param m
     * @return
     * @throws Exception
     */
    public static <T,M extends Model<?>> M revertBean2Model(T t,M m) throws Exception{
        if(t!=null && m!=null){
            PropertyDescriptor[] proDess = PropertyUtils.getPropertyDescriptors(t);
            if (ArrayUtils.isNotEmpty(proDess)) {
                for (PropertyDescriptor pro : proDess) {
                    String propertyName = pro.getName();
                    if(!StringUtils.equals(propertyName, "class")){
                        String proValue = BeanUtils.getProperty(t, propertyName);
                        Object proObj = PropertyUtils.getProperty(t, propertyName);
                        if(proObj instanceof String){   // String 
                            m.set(propertyName, proValue);
                        }else if(proObj instanceof Integer){ // Integer
                            Integer intValue = 0;
                            try {
                                intValue = Integer.parseInt(proValue);
                            } catch (NumberFormatException e) {
                                intValue = 0;
                            }
                            m.set(propertyName, intValue);
                        }else if(proObj instanceof Date){	// Date
                            if(StringUtils.isNotEmpty(proValue)){
                                Timestamp timestamp = Timestamp.valueOf(proValue);
                                m.set(propertyName, timestamp);
                            }
                        }else if( proObj instanceof Long){ // Long
                            Long lng = new Long(proValue);
                            m.set(propertyName, lng);
                        }else if(proObj instanceof Double){	// Double
                            Double dob = new Double(proValue);
                            m.set(propertyName, dob);
                        }else if(proObj instanceof Float){	// Float
                        	Float dob = new Float(proValue);
                            m.set(propertyName, dob);
                        }else if(proObj instanceof Byte){	// Byte
                        	Byte dob = new Byte(proValue);
                            m.set(propertyName, dob);
                        }else if(proObj instanceof Short){	// Short
                        	Short dob = new Short(proValue);
                            m.set(propertyName, dob);
                        }else if(proObj instanceof Character){ // Char
                        	Character dob = ((Character) proObj);
                            m.set(propertyName, dob);
                        }else if(proObj instanceof Boolean){	// Boolean
                        	Boolean dob = new Boolean(proValue);
                            m.set(propertyName, dob);
                        }else {
                            log.info("无法支持的数据类型");
                        }
                    }
                }
            }
        }
        return null;
    }
}
