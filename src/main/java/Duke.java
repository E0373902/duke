import main.java.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) throws ParseException {
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
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        String str[] = new String[list.size()];
        for (int i = 0; i < list.size(); i = i + 1) {
            String[] add_tasks = list.get(i).split(" ");
            String u = "";
            String ut = "";
            u = add_tasks[2]; //T or D or E
            ut = add_tasks[5]; //X or tick
            String connector = "";
            for (int k = 7; k < add_tasks.length; k = k + 1) {
                connector = connector + add_tasks[k] + " ";
            }
            String st = "";
            String sr = "";
            if (connector.contains("(")) {
                String[] arr = connector.split(":");
                String p = arr[0];
                String[] arr1 = p.split(" ");
                String q = arr[1];
                String[] arr2 = q.split(" ");
                for (int f = 0; f < arr1.length - 1; f = f + 1) {
                    st = st + arr1[f] + " ";
                }
                for (int z = 0; z < arr2.length - 1; z = z + 1) {
                    sr = sr + arr2[z] + " ";
                }
                if (u.equals("D")) {
                    Task d = new Deadline(st, sr);
                    tasks.add(d);
                    if (ut.equals("\u2713")) {
                        d.markAsDone();
                    }
                } else if (u.equals("E")) {
                    Task ev = new Event(st, sr);
                    tasks.add(ev);
                    if (ut.equals("\u2713")) {
                        ev.markAsDone();
                    }
                }
            } else {
                if (u.equals("T")) {
                    Task t = new ToDo(connector);
                    tasks.add(t);
                    if (ut.equals("\u2713")) {
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
                    for (int i = 0; i < tasks.size(); i = i + 1) {
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
                        sr = arr2[1];
                        for (int i = 1; i < arr1.length; i = i + 1) {
                            st = st + arr1[i] + " ";
                        }
                        for (int i = 2; i < arr.length; i = i + 1) {
                            sr = sr + "/" + arr[i];
                        }
                        String[] Date = sr.split(" ");
                        String sDate1 = "";
                        String time = "";
                        String time2 = "";
                        String diff = "";
                        String date_time = "";
                        sDate1 = Date[0];
                        time = Date[1];
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date1);
                        diff = getFormattedDate(cal.getTime());
//Date/time pattern of input date
                        DateFormat df = new SimpleDateFormat("HHmm");
//Date/time pattern of desired output date
                        DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
                        Date date = null;
                        String output = null;
                        try {
                            //Conversion of input String to date
                            date = df.parse(time);
                            //old date format to new date format
                            output = outputformat.format(date);
                        } catch (ParseException pe) {
                            pe.printStackTrace();
                        }
                        date_time = diff + " " + output;
                        if (first.equals("deadline")) {
                            Task w = new Deadline(st, date_time);
                            tasks.add(w);
                            try {
                                BufferedWriter out = new BufferedWriter(
                                        new FileWriter(fileName, false));
                                for (int v = 0; v < tasks.size(); v = v + 1) {
                                    out.write((v + 1) + ". " + tasks.get(v).toString() + "\n");
                                }
                                out.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            System.out.println(" Got it. I've added this task: ");
                            System.out.println(w.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        } else {
                            time2 = Date[3];
                            DateFormat df1 = new SimpleDateFormat("HHmm");
//Date/time pattern of desired output date
                            DateFormat outputformat1 = new SimpleDateFormat("hh:mm aa");
                            Date date2 = null;
                            String output1 = null;
                            try {
                                //Conversion of input String to date
                                date2 = df.parse(time2);
                                //old date format to new date format
                                output1 = outputformat.format(date2);
                            } catch (ParseException pe) {
                                pe.printStackTrace();
                            }
                            date_time = date_time + " - " + output1;
                            Task ev = new Event(st, date_time);
                            tasks.add(ev);
                            try {
                                BufferedWriter out = new BufferedWriter(
                                        new FileWriter(fileName, true));
                                for (int t = 0; t < tasks.size(); t = t + 1) {
                                    out.write((t + 1) + ". " + tasks.get(t).toString() + "\n");
                                }
                                out.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            System.out.println(" Got it. I've added this task: ");
                            System.out.println(ev.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
                                    for (int jk = 0; jk < tasks.size(); jk = jk + 1) {
                                        out.write((jk + 1) + ". " + tasks.get(jk).toString());
                                    }
                                    out.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                System.out.println(" Got it. I've added this task: ");
                                System.out.println(todo.toString());
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
                                for (int i = 0; i < tasks.size(); i = i + 1) {
                                    if (s.equals("Done " + (i + 1))) {
                                        tasks.get(i).markAsDone();
                                        try {
                                            BufferedWriter out = new BufferedWriter(
                                                    new FileWriter(fileName, false));
                                            for (int k = 0; k < tasks.size(); k = k + 1) {
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
                        } else if (fir.equals("delete")) {
                            if (e.equals("")) {
                                DukeException t = new DukeException(fir);
                                System.out.println(t.cannotBeEmpty());
                            } else {
                                for (int i = 0; i < tasks.size(); i = i + 1) {
                                    if (s.equals("delete " + (i + 1))) {
                                        System.out.println("Noted. I've removed this task: ");
                                        System.out.println(tasks.get(i).toString());
                                        tasks.remove(i);
                                        try {
                                            BufferedWriter out = new BufferedWriter(
                                                    new FileWriter(fileName, false));
                                            for (int k = 0; k < tasks.size(); k = k + 1) {
                                                out.write((k + 1) + ". " + tasks.get(k).toString() + "\n");
                                            }
                                            out.close();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                                    }
                                }
                            }
                        }
                    else {
                                            DukeException t = new DukeException(fir);
                                            System.out.println(t.NotACommand());
                                        }
                                    }
                                }
                            }
                        }

                    }

    private static String getFormattedDate(Date date) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        //2nd of march 2015
        int day=cal.get(Calendar.DATE);

        if(!((day>10) && (day<19)))
            switch (day % 10) {
                case 1:
                    return new SimpleDateFormat("d'st' 'of' MMMM yyyy").format(date);
                case 2:
                    return new SimpleDateFormat("d'nd' 'of' MMMM yyyy").format(date);
                case 3:
                    return new SimpleDateFormat("d'rd' 'of' MMMM yyyy").format(date);
                default:
                    return new SimpleDateFormat("d'th' 'of' MMMM yyyy").format(date);
            }
        return new SimpleDateFormat("d'th' 'of' MMMM yyyy").format(date);
    }

}

