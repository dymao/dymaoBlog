/**
 * @author Mervin
 * @Description:
 * @date 2018-03-30 23:33
 */
public class RandomTest {

    public static void main(String[] args) {
        String str = ",";
        int count = 0;
        while (count < 6){
            int m = (int)(Math.random() * 10) ;
            System.out.println(m);
            if(str.indexOf("," + m + ",") < 0 ){
                str = str + m + ",";
                System.out.println("count = " + count +", str = " + str);
                count ++;
            }else{
                System.out.println("发现重复字符串");
            }
        }
    }
}
