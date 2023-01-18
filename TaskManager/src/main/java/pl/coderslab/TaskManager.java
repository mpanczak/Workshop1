package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

import static pl.coderslab.ConsoleColors.*;

public class TaskManager {
    static String[][] tasks = new String[0][];
    public static void main(String[] args) {

        try {
            loadTasks();
        }
        catch (FileNotFoundException e) {
            System.out.println(RED + "[Error] Failed to load data. The TaskManager application has been stopped." + RESET);
            return;
        }
        displayMainWindow();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            switch (command) {
                case "add" -> {
                    addTask();
                    displayMainWindow();
                }
                case "remove" -> {
                    removeTask();
                    displayMainWindow();
                }
                case "list" -> {
                    displayTasks();
                    displayMainWindow();
                }
                case "exit" -> {
                    saveTasks();
                    System.out.println(RED + "Bye, bye." + RESET);
                    return;
                }
                default ->
                        System.out.println(BLUE + "Please select a correct option." + RESET);
            }

        }

    }

    public static void displayMainWindow() {
        System.out.println(BLUE + "Please select an option:" + RESET);
        System.out.println("add\nremove\nlist\nexit");
    }
    public static void displayTasks() {
        System.out.println("list");
        if (tasks.length == 0) {
            System.out.println("No tasks to do.");
        }

        for (int i = 0; i < tasks.length; i++) {
            System.out.println(YELLOW + i + " : " + tasks[i][0] + " " + tasks[i][1] + " " + tasks[i][2] + RESET);
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
            if (description != null && !description.contains(",")) {
                task[0] = description;
                break;
            }
        }
        while (true) {
            System.out.println("Please add task due date. Use format yyyy-mm-dd");
            date = sc.nextLine();
            if (date != null && date.matches("\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])*")) {
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
        tasks = Arrays.copyOf(tasks, tasks.length +1);
        tasks[tasks.length -1] = task;

        System.out.println(description + " " + date + " " + isImportant);
    }

    public static void removeTask() {
        System.out.println("remove");
        System.out.println("Please select number to remove");

        Scanner sc = new Scanner(System.in);
        int taskID;
        while (true) {
            String input = sc.nextLine();
            if (NumberUtils.isParsable(input)) {
                taskID = Integer.parseInt(input);
                if (taskID >= 0){
                    break;
                }
            }
            System.out.println("Incorrect argument passed. Please give number grater or equal 0");
        }

        try {
            tasks = ArrayUtils.remove(tasks, taskID);
            System.out.println("Value was successfully deleted.");

        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("The task with the given ID was not found.");
        }
    }

    public static void loadTasks() throws FileNotFoundException {
        int i = 0;
        File taskDatabase = new File("tasks.csv");
        Scanner sc = new Scanner(taskDatabase);

        while (sc.hasNextLine()) {
            tasks = Arrays.copyOf(tasks, tasks.length +1);
            String line = sc.nextLine();
            tasks[i] = line.split(", ");
            i++;
        }
    }
    public static void saveTasks() {
        StringBuilder sb = new StringBuilder();

        for (String[] strings : tasks) {
            sb.append(strings[0]);
            for (int j = 1; j < strings.length; j++) {
                sb.append(", ").append(strings[j]);
            }
            sb.append("\n");
        }
        File file = new File("tasks.csv");
        try {
            Files.writeString(file.toPath(), sb.toString());
        }
        catch (IOException e) {
            System.out.println(RED + "[Error] File saving problem." + RESET);
        }
    }

}