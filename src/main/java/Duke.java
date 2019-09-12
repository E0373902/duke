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
        ArrayList<Task> tasks = new ArrayList<Task>(); //ArrayList of type Task called tasks
        List<String> list = new ArrayList<String>(); //List of type string to store the tasks read from txt file
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        String work;
        //read tasks from txt file and add to the List called list
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
        //add the tasks from list to the tasks arraylist i.e. adding tasks from the txt file to arraylist
        for (int i = 0; i < list.size(); i = i + 1) {
            String[] add_tasks = list.get(i).split(" ");//splits each line in the txt file by space
            String u = "";
            String ut = "";
            u = add_tasks[2]; //T or D or E
            ut = add_tasks[5]; //X or tick
            String connector = "";
            for (int k = 7; k < add_tasks.length; k = k + 1) {
                connector = connector + add_tasks[k] + " ";/*to store the part of the string after deadline/todo/event
                                                             in a string called connector
                                                           */
            }
            String st = "";
            String sr = "";
            if (connector.contains("(")) { /*this if condition is to further split connector if it contains brackets(which means that it is a deadline
                                             or event) to separate the description and the date/timing
                                            */
                String[] arr = connector.split(":");//split by ":" as the date and timing comes after the ":"
                String p = arr[0];//consists of the description + "(by" or "(at"
                String[] arr1 = p.split(" ");//split by space to get the description only and not "(by" or "(at"
                String q = arr[1];//consists of the date and time + ")"
                String[] arr2 = q.split(" ");//split by space to get the description only and not "(by" or "(at"
                for (int f = 0; f < arr1.length - 1; f = f + 1) {
                    st = st + arr1[f] + " ";
                    /*join from 0th element to 2nd last element as we don't want the last element i.e.
                    "(by" or "(at". This way just the description is stored in one string called st
                    */
                }
                for (int z = 0; z < arr2.length - 1; z = z + 1) {
                    sr = sr + arr2[z] + " ";
                    /*join from 0th element to 2nd last element as we don't want the last element i.e.
                    ")". This way just the date and time is stored in one string called sr
                    */
                }
                if (u.equals("D")) { //this means that the Task is a deadline
                    Task d = new Deadline(st, sr);//initialize a new Deadline object with the strings st(description) and sr(date&time)
                    tasks.add(d); //add the Deadline object to the arraylist of tasks
                    if (ut.equals("\u2713")) { //if ut is a tick, it means the task is done.
                        d.markAsDone();//call the method markAsDone in the Task class in order to set the boolean value of isDone to true and return a tick in the getStatusIcon method
                    }
                } else if (u.equals("E")) { //this means that the Task is an event
                    Task ev = new Event(st, sr);//initialize a new Event object with the strings st(description) and sr(date&time)
                    tasks.add(ev);//add the Event object to the arraylist of tasks
                    if (ut.equals("\u2713")) { //if ut is a tick, it means the task is done.
                        ev.markAsDone();//call the method markAsDone in the Task class in order to set the boolean value of isDone to true and return a tick in the getStatusIcon method
                    }
                }
            } else { //if connector doesn't contain a bracket
                if (u.equals("T")) { //this means the task is a todo
                    Task t = new ToDo(connector); //initialize a new todo object with the string connector as a todo only consists of description
                    tasks.add(t);
                    if (ut.equals("\u2713")) { //if ut is a tick, it means the task is done.
                        t.markAsDone();//call the method markAsDone in the Task class in order to set the boolean value of isDone to true and return a tick in the getStatusIcon method
                    }
                }
            }
        }
        Scanner input = new Scanner(System.in);//takes input
        while (true) {//while true means while there is an input or the input is not blank or null
            String s = input.nextLine();//new line of input
            String st = "";
            String sr = "";
            String e = "";
            if (s.equals("bye")) { //if the input equals to bye exit the program after printing out the following line
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                if (s.equals("list")) { //if the input equals to list print the ArrayList tasks
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i = i + 1) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                } else {
                    if (s.contains("/")) {/*this if condition is to split the string s(which is the input) if it contains a "/"(which means that it is a deadline
                                             or event) to separate the description and the date/timing
                                            */
                        String[] arr = s.split("/");//split by "/" as the date and timing comes after "/by" or "/at"
                        String p = arr[0];//consists of the type of task and the description
                        String[] arr1 = p.split(" ");//split by space to separate the type of task and description
                        String first = arr1[0];//the type of task(ex: deadline) assigned to String first
                        String q = arr[1];// "by" + date and time
                        String[] arr2 = q.split(" ");//split by space to get date&time
                        sr = arr2[1];
                        for (int i = 1; i < arr1.length; i = i + 1) {
                            st = st + arr1[i] + " ";
                        }
                        for (int i = 2; i < arr.length; i = i + 1) {
                            sr = sr + "/" + arr[i]; //store date and time in the format 2/12/2019 1800
                        }
                        String[] Date = sr.split(" "); //separate date and time
                        String sDate1 = "";
                        String time = "";
                        String time2 = "";
                        String diff = "";
                        String date_time = "";
                        sDate1 = Date[0];//date
                        time = Date[1];//time
                        //convert date and time in the format 2nd of December 2019 6:00pm
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
                        date_time = diff + " " + output;//join the changed format of date and the changed format of time and store it one string
                        if (first.equals("deadline")) {
                            Task w = new Deadline(st, date_time);//initialize a new Deadline object with the strings st(description) and date_time(date&time in a different format from the one in the input)
                            tasks.add(w);//add the Deadline object to the arraylist of tasks

                            //overwrite txt file
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
                            time2 = Date[3]; /*if the task is an event there will be 2 times starting and ending. time2 is the
                            ending time while time is the starting time*/
                            DateFormat df1 = new SimpleDateFormat("HHmm");
//Date                      /time pattern of desired output date
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
                            date_time = date_time + " - " + output1;//join the date and starting time and ending time with a dash in between
                            Task ev = new Event(st, date_time);
                            tasks.add(ev);
                            //overwrite txt file
                            try {
                                BufferedWriter out = new BufferedWriter(
                                        new FileWriter(fileName, true));

                                for (int t = 0; t <= tasks.size(); t = t + 1) {

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
                                //overwrite txt file
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
                                    if (s.equals("Done " + (i + 1))) { //means the (i+1)th index of arraylist tasks has to be marked as done
                                        tasks.get(i).markAsDone(); //marks the (i + 1)th index of arraylist tasks is marked as done

                                        //overwrite txt file so that there are no repetitions
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
                                    if (s.equals("delete " + (i + 1))) { //means the (i+1)th index of arraylist tasks has to be deleted
                                        System.out.println("Noted. I've removed this task: ");
                                        System.out.println(tasks.get(i).toString());
                                        tasks.remove(i);//removes the (i + 1)th index of arraylist tasks

                                        //overwrite txt file
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
                        } else if (fir.equals("find")) {
                            if (e.equals("")) {
                                DukeException t = new DukeException(fir);
                                System.out.println(t.cannotBeEmpty());
                            } else {
                                String[] str = s.split(" ");
                                String g = str[1];
                                List<String> list1 = new ArrayList<String>();//a List called list1 to store the strings that contain the word that comes after find/contain String g
                                for (int i = 0; i < tasks.size(); i = i + 1) {
                                    if (tasks.get(i).toString().contains(g)) {
                                        list1.add(tasks.get(i).toString());
                                    }
                                }
                                System.out.println("Here are the matching tasks in your list:");
                                for (int k = 0; k < list1.size(); k = k + 1) {
                                    System.out.println((k + 1) + ". " + list1.get(k));
                                }
                            }
                        }else { //if the string typed doesn't match with any of the commands above i.e. it is not a Duke command
                            DukeException t = new DukeException(fir);
                            System.out.println(t.NotACommand());
                        }
                    }
                }
            }
        }
    }
    //to get the required format for the date
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

