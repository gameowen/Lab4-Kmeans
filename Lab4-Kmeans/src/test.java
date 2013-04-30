
import java.util.EnumMap;
import java.util.EnumSet;
 
public class test {
 
    // 1. 定义枚举类型
    public enum Light {
       // 利用构造函数传参
       RED (1), GREEN (3), YELLOW (2);
 
       // 定义私有变量
       private int nCode ;
 
       // 构造函数，枚举类型只能为私有
       private Light( int _nCode) {
           this . nCode = _nCode;
       }
 
       @Override
       public String toString() {
           return String.valueOf ( this . nCode );
       }
    }
    private static void testTraversalEnum() {
        Light[] allLight = Light.values ();
        for (Light aLight : allLight) {
            System. out .println( " 当前灯 name ： " + aLight.name());
            System. out .println( " 当前灯 ordinal ： " + aLight.ordinal());
            System. out .println( " 当前灯： " + aLight);
        }
     }
    /**
      * @param args
      */
    public static void main(String[] args ) {
 
       // 1. 遍历枚举类型
       System. out .println( " 演示枚举类型的遍历 ......" );
       testTraversalEnum ();
 
    }}