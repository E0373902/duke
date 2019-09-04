import main.java.Deadline;
import main.java.Task;
import main.java.Event;
import main.java.ToDo;

import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        Scanner input = new Scanner(System.in);
        int j = 0;
        while (true) {
            String s = input.nextLine();
            String st = "";
            String sr = "";
            String e = "";
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                if (s.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < j; i = i + 1) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }

                } else {
                    if (s.contains("/")) {
                        String[] arr = s.split("/");
                        String p = arr[0];
                        String[] arr1 = p.split(" ");
                        String first = arr1[0];
                        String q = arr[1];
                        String[] arr2 = q.split(" ");
                        for (int i = 1; i < arr1.length; i = i + 1) {
                            st = st + arr1[i] + " ";
                        }
                        for (int i = 1; i < arr2.length; i = i + 1) {
                            sr = sr + arr2[i] + " ";
                        }
                        if (first.equals("deadline")) {
                            tasks[j] = new Deadline(st, sr);
                            System.out.println(" Got it. I've added this task: ");
                            System.out.println(tasks[j].toString());
                            System.out.println("Now you have " + (j + 1) + " tasks in the list.");
                            j = j + 1;
                        } else {
                            tasks[j] = new Event(st, sr);
                            System.out.println(" Got it. I've added this task: ");
                            System.out.println(tasks[j].toString());
                            System.out.println("Now you have " + (j + 1) + " tasks in the list.");
                            j = j + 1;
                        }
                    } else {
                        String[] arr = s.split(" ");
                        String fir = arr[0];
                        for (int i = 1; i < arr.length; i = i + 1) {
                            e = e + arr[i] + " ";
                        }
                        if (fir.equals("todo")) {
                            tasks[j] = new ToDo(e);
                            System.out.println(" Got it. I've added this task: ");
                            System.out.println(tasks[j].toString());
                            System.out.println("Now you have " + (j + 1) + " tasks in the list.");
                            j = j + 1;
                        } else {
                            for (int i = 0; i < j; i = i + 1) {
                                if (s.equals("Done " + (i + 1))) {
                                    tasks[i].markAsDone();
                                    System.out.println("Nice! I've marked this task as done: ");
                                    System.out.println(tasks[i].toString());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}





