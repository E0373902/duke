import main.java.Task;

import java.util.*;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> myArray = new ArrayList();
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner input = new Scanner(System.in);
        while (true) {
            String s = input.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you soon again.");
                break;
            } else {
                if (s.equals("list")) {
                    Task r = new Task("");
                    for (int i = 0; i < myArray.size(); i = i + 1) {
                        r = myArray.get(i);
                        System.out.println((i + 1) + ".[" + r.getStatusIcon() + "]" + r.description);
                    }
                } else {
                    Task t = new Task(s);
                    myArray.add(t);
                    Task f = new Task("");
                    for (int i = 0; i < myArray.size(); i = i + 1) {
                        if (s.equals("Done " + (i + 1))) {
                            myArray.remove(myArray.size() - 1);
                            f = myArray.get(i);
                            f.markAsDone();
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println((i + 1) + ".[" + f.getStatusIcon() + "]" + f.description);
                        }
                    }
                }
            }
        }
    }
}




