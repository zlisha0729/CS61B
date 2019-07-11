/**

*/

public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            int y = x;
            int total = 0;
            while (y > 0) {
                total = total + y;
                y = y - 1;
            }
            System.out.println(total + " ");
            x = x + 1;
        }
  }
}
