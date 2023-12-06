 
import java.math.BigInteger;
public  class Encryption {
    public  String encryption(String str){
        BigInteger bStr = new BigInteger(str);
        BigInteger B = new BigInteger("654321");
        System.out.println("bStr="+bStr);
        System.out.println(B.xor(bStr));
        return String.valueOf(B.xor(bStr));
    }
}