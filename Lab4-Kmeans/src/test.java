
import java.util.EnumMap;
import java.util.EnumSet;
 
public class test {
 
    // 1. ����ö������
    public enum Light {
       // ���ù��캯������
       RED (1), GREEN (3), YELLOW (2);
 
       // ����˽�б���
       private int nCode ;
 
       // ���캯����ö������ֻ��Ϊ˽��
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
            System. out .println( " ��ǰ�� name �� " + aLight.name());
            System. out .println( " ��ǰ�� ordinal �� " + aLight.ordinal());
            System. out .println( " ��ǰ�ƣ� " + aLight);
        }
     }
    /**
      * @param args
      */
    public static void main(String[] args ) {
 
       // 1. ����ö������
       System. out .println( " ��ʾö�����͵ı��� ......" );
       testTraversalEnum ();
 
    }}