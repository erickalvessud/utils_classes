package teste;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;

public class ExemploConversaoReflection {
    
    private final String PACOTE_APACHE_COMMONS_BEANUTILS_CONVERTERS = "org.apache.commons.beanutils.converters";
    
    private final String PONTO = ".";

    public <T> T converterValorParametro(String valor, Class<T> clazz){
        T retorno = null;
        String className = clazz.getSimpleName();
        String classConverterName = className + "Converter";
        
        Class<?> converterClass = null;
        
        Object converterInstance = null;
     
        try {
            converterClass = Class.forName(this.PACOTE_APACHE_COMMONS_BEANUTILS_CONVERTERS + this.PONTO + classConverterName);
            
            Constructor<?> constructor = converterClass.getConstructor(Object.class);
            
            converterInstance = constructor.newInstance(new Object[]{ null });
            
            ConvertUtils.register((Converter)converterInstance, clazz); 
            
            Object valorConvertido = ConvertUtils.convert(valor, clazz);
            
            retorno =  clazz.cast(valorConvertido);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return retorno;
    }
    
    public static void main(String[] args) {
        ParametroSistemaBean p = new ParametroSistemaBean();
        
		Boolean i = p.buscaParametroSistemaPelaSigla("", Boolean.class);
		if (i == null) {
			System.out.println("Problemas na conversão");
			return;
		}
		System.out.println("Valor convertido: " + i);        
    }
}
