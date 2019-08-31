import java.util.*;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        Scanner input = new Scanner(System.in);
        while(true){
            String s = input.nextLine();
            if(s.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(s);
        }
    }
}
