import java.util.*;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        List<String>list = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        while (true) {
            String s = input.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (s.equals("list")) {
                for(int j = 0; j < list.size(); j = j + 1){
                    System.out.println((j + 1) + "." + list.get(j));
                }
            } else {
                list.add(s);
                System.out.println("added:" + s);
            }
        }
    }
}
