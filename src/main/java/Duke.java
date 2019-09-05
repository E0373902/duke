import main.java.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
     File file = new File("C:\\Users\\0108s\\OneDrive\\Documents\\duke\\Level 7 help.txt");
     String fileName = "C:\\Users\\0108s\\OneDrive\\Documents\\duke\\Level 7 help.txt";
     ArrayList<Task> tasks = new ArrayList<Task>();
     System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        int j = 0;
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                //FileReader fr = new FileReader("C:\\Users\\0108s\\OneDrive\\Documents\\duke\\Level 7 help.txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String work;
                try {
                    while ((work = br.readLine()) != null) {
                        String [] tr = work.split(" ");
                        String str = "";
                        String u = tr[2];
                        String ut = tr[5];
                        for(int i = 7; i < tr.length; i = i + 1){
                            str = str + tr[i] + " ";
                        }
                        if (str.contains("/")) {
                            String[] arr = str.split("/");
                            String p = arr[0];
                            //String st = "";
                            String sr = "";
                            String q = arr[1];
                            String[] arr2 = q.split(" ");
                            for (int i = 1; i < arr2.length; i = i + 1) {
                                sr = sr + arr2[i] + " ";
                            }
                            if (u.equals("D")) {
                                Task d = new Deadline(p, sr);
                                tasks.add(d);
                                if(ut.equals("\u2713")){
                                    d.markAsDone();
                                }
                                j = j + 1;
                            } else {
                                Task ev = new Event(p, q);
                                tasks.add(ev);
                                if(ut.equals("\u2713")){
                                    ev.markAsDone();
                                }
                                j = j + 1;
                            }
                        } else {
                            Task todo = new ToDo(str);
                            tasks.add(todo);
                            if(ut.equals("\u2713")){
                                todo.markAsDone();
                            }
                            j = j + 1;
                        }
                    }
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
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
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
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
                            Task d = new Deadline(st, sr);
                            tasks.add(d);
                                try {
                                    BufferedWriter out = new BufferedWriter(
                                            new FileWriter(fileName, false));
                                    for(int i = 0; i <= j; i = i + 1) {
                                        out.write((i + 1) + ". " + tasks.get(i).toString() + "\n");
                                    }
                                    out.close();
                                }
                            catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            System.out.println(" Got it. I've added this task: ");
                            System.out.println(tasks.get(j).toString());
                            System.out.println("Now you have " + (j + 1) + " tasks in the list.");
                            j = j + 1;
                        } else {
                            Task ev  = new Event(st, sr);
                            tasks.add(ev);
                            try{
                                BufferedWriter out = new BufferedWriter(
                                        new FileWriter(fileName, false));
                                for(int i = 0; i <= j; i = i + 1) {
                                    out.write((i + 1) + ". " + tasks.get(i).toString() + "\n");
                                }
                                out.close();
                            }
                            catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            System.out.println(" Got it. I've added this task: ");
                            System.out.println(tasks.get(j).toString());
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
                            if (e.equals("")) {
                                DukeException t = new DukeException(fir);
                                System.out.println(t.cannotBeEmpty());
                            } else {
                                Task todo  = new ToDo(e);
                                tasks.add(todo);
                                try{
                                    BufferedWriter out = new BufferedWriter(
                                            new FileWriter(fileName, false));
                                    for(int i = 0; i <= j; i = i + 1) {
                                        out.write((i + 1) + ". " + tasks.get(i).toString());
                                    }
                                    out.close();
                                }
                                catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                System.out.println(" Got it. I've added this task: ");
                                System.out.println(tasks.get(j).toString());
                                System.out.println("Now you have " + (j + 1) + " tasks in the list.");
                                j = j + 1;
                            }
                        } else if (fir.equals("deadline")) {
                            if (e.equals("")) {
                                DukeException t = new DukeException(fir);
                                System.out.println(t.cannotBeEmpty());
                            }
                        } else if (fir.equals("event")) {
                            if (e.equals("")) {
                                DukeException t = new DukeException(fir);
                                System.out.println(t.cannotBeEmpty());
                            }
                        } else if (fir.equals("Done")) {
                            if (e.equals("")) {
                                DukeException t = new DukeException(fir);
                                System.out.println(t.cannotBeEmpty());
                            } else {
                                for (int i = 0; i < j; i = i + 1) {
                                    if (s.equals("Done " + (i + 1))) {
                                        tasks.get(i).markAsDone();
                                        try {
                                            BufferedWriter out = new BufferedWriter(
                                                    new FileWriter(fileName, false));
                                            for(int k = 0; k < j; k = k + 1) {
                                                out.write((k + 1) + ". " + tasks.get(k).toString() + "\n");
                                            }
                                            out.close();
                                        }
                                        catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                        System.out.println("Nice! I've marked this task as done: ");
                                        System.out.println(tasks.get(i).toString());
                                    }
                                }
                            }
                        }else{
                                    DukeException t = new DukeException(fir);
                                    System.out.println(t.NotACommand());
                                }

                            }
                        }
                    }
                }
            }
        }











