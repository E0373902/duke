import main.java.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\0108s\\OneDrive\\Documents\\duke\\Level 7 help.txt");
        String fileName = "C:\\Users\\0108s\\OneDrive\\Documents\\duke\\Level 7 help.txt";
        ArrayList<Task> tasks = new ArrayList<Task>();
        List<String> list = new ArrayList<String>();
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        String work;
        try {
            //FileReader fr = new FileReader("C:\\Users\\0108s\\OneDrive\\Documents\\duke\\Level 7 help.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                while ((work = br.readLine()) != null) {
                    list.add(work);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        int j = list.size();
        for(int i = 0; i < list.size(); i = i + 1){
            String u = "";
            String ut = "";
            String[] add_tasks = list.get(i).split(" ");
            u = add_tasks[2]; //T or D or E
            ut = add_tasks[5]; //X or tick
            String connector = "";
            for (int k = 7; k < add_tasks.length; k = k + 1){
                connector = connector + add_tasks[k] + " ";
            }
            String st = "";
            String sr = "";
            if (connector.contains("(")) {
                String[] arr= connector.split(":");
                String p = arr[0];
                String[] arr1 = p.split(" ");
                String q = arr[1];
                String[] arr2 = q.split(" ");
                for (int f = 0; f < arr1.length - 1; f = f + 1) {
                    st = st + arr1[f] + " ";
                }
                for(int z = 0; z < arr2.length - 1; z = z + 1 ){
                    sr = sr + arr2[z] + " ";
                }
                if(u.equals("D")){
                    Task d = new Deadline(st,sr);
                    tasks.add(d);
                    if(ut.equals("\u2713")){
                        d.markAsDone();
                    }

                }
                else if(u.equals("E")){
                    Task ev = new Event(st, sr);
                    tasks.add(ev);
                    if(ut.equals("\u2713")){
                        ev.markAsDone();
                    }
                }
            }
            else{
                if(u.equals("T")){
                    Task t = new ToDo(connector);
                    tasks.add(t);
                    if(ut.equals("\u2713")) {
                        t.markAsDone();
                    }
                }
            }
        }
        Scanner input = new Scanner(System.in);
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
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
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
                            Task w = new Deadline(st, sr);
                            tasks.add(w);
                            try {
                                BufferedWriter out = new BufferedWriter(
                                        new FileWriter(fileName, false));
                                for (int v = 0; v <= j; v = v + 1) {
                                    out.write((v + 1) + ". " + tasks.get(v).toString() + "\n");
                                }
                                out.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            System.out.println(" Got it. I've added this task: ");
                            System.out.println(tasks.get(j).toString());
                            System.out.println("Now you have " + (j + 1) + " tasks in the list.");
                            j = j + 1;
                        } else {
                            Task ev = new Event(st, sr);
                            tasks.add(ev);
                            try {
                                BufferedWriter out = new BufferedWriter(
                                        new FileWriter(fileName, true));
                                for (int t = 0; t <= j; t = t + 1) {
                                    out.write((t + 1) + ". " + tasks.get(t).toString() + "\n");
                                }
                                out.close();
                            } catch (IOException e1) {
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
                               Task todo = new ToDo(e);
                               tasks.add(todo);
                                try {
                                    BufferedWriter out = new BufferedWriter(
                                            new FileWriter(fileName, true));
                                    for (int jk = 0; jk <= j; jk = jk + 1) {
                                        out.write((jk + 1) + ". " + tasks.get(jk).toString());
                                    }
                                    out.close();
                                } catch (IOException e1) {
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
                                            for (int k = 0; k < j; k = k + 1) {
                                                out.write((k + 1) + ". " + tasks.get(k).toString() + "\n");
                                            }
                                            out.close();
                                        } catch (IOException e1) {
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
