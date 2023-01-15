package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {

        String[][] tasks;
        try {
            tasks = loadTasks();
        }
        catch (FileNotFoundException e) {
            System.out.println(ConsoleColors.RED + "[Error] Failed to load data. The TaskManager application has been stopped." + ConsoleColors.RESET);
            return;
        }
        displayMainWindow();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            switch (command) {
                case "add":
                    addTask();
                    displayMainWindow();
                    break;
                case "remove":
                    tasks = removeTask(tasks);
                    displayMainWindow();
                    break;
                case "list":
                    displayTasks(tasks);
                    displayMainWindow();
                    break;
                case "exit":
                    System.out.println(ConsoleColors.RED + "Bye, bye." + ConsoleColors.RESET);
                    return;
                default:
                    System.out.println(ConsoleColors.BLUE + "Please select a correct option." + ConsoleColors.RESET);
            }

        }

    }

    public static void displayMainWindow() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        System.out.println("add\nremove\nlist\nexit");
    }
    public static void displayTasks(String[][] tasks) {
        System.out.println("list");
        if (tasks.length == 0) {
            System.out.println("No tasks to do.");
        }

        for (int i = 0; i < tasks.length; i++) {
            System.out.println(ConsoleColors.YELLOW
                    + i + " : " + tasks[i][0] + " " + tasks[i][1] + " " + tasks[i][2]
                    + ConsoleColors.RESET);
        }
        System.out.println();
    }

    public static void addTask() {
        System.out.println("add");

        String[] task = new String[3];
        String description;
        String date;
        String isImportant;


        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("Please add task description. Please do not use comma");
            description = sc.nextLine();
            if (description != null && !description.matches(",")) {
                break;
            }

        }
        while (true) {
            System.out.println("Please add task due date. Use format yyyy-mm-dd");
            date = sc.nextLine();
            if (date != null && date.matches("\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])*")) {
                task[1] = date;
                break;
            }
        }

        while (true) {
            System.out.println("Is your task important: true/false");
            isImportant = sc.nextLine();

            if ("true".equals(isImportant) || "false".equals(isImportant)) {
                task[2] = isImportant;
                break;
            }
        }
        System.out.println(description + " " + date + " " + isImportant);
    }

    public static String[][] removeTask(String[][] array) {
        System.out.println("remove");
        System.out.println("Please select number to remove");

        Scanner sc = new Scanner(System.in);
        int taskID = sc.nextInt();

        try {
            array = ArrayUtils.remove(array, taskID);
            System.out.println("Value was successfully deleted.");

        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("The task with the given id was not found.");
        }
        return array;
    }

    public static String[][] loadTasks() throws FileNotFoundException {
        String[][] tasks = new String[0][];
        int i = 0;
        File taskDatabase = new File("tasks.csv");
        Scanner sc = new Scanner(taskDatabase);

        while (sc.hasNextLine()) {
            tasks = Arrays.copyOf(tasks, tasks.length +1);
            String line = sc.nextLine();
            tasks[i] = line.split(", ");
            i++;
        }

        return tasks;
    }
    public static void saveTasks() {
        System.out.println("Zapisuje taski");
    }

}