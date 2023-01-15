package pl.coderslab;


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
                    removeTask();
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
                    displayMainWindow();
            }

        }

    }

    public static void displayMainWindow() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        System.out.println("add\nremove\nlist\nexit");
    }
    public static void displayTasks(String[][] tasks) {
        System.out.println("list");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(tasks[i][0] + tasks[i][1] + " " + tasks[i][2] + " " + tasks[i][3]);
        }
        System.out.println();
    }

    public static void addTask() {
        System.out.println("add");
        System.out.println("dodaaje taska");
    }

    public static void removeTask() {
        System.out.println("remove");
        System.out.println("usuwa taska");
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