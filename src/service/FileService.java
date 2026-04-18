package service;

import model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static final String FILE_NAME = "tasks.txt";

    public static void save(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME)))
        {
            String isDone;

            for (Task task : tasks) {
                if (task.isDone()) {
                    isDone = "[✔] ";
                } else {
                    isDone = "[ ] ";
                }

                writer.write(isDone + task.getTitle());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    public static List<Task> load() {
        List<Task> tasks = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME)))
        {
            String line;

            while ((line = reader.readLine()) != null) {
                boolean isDone = line.startsWith("[✔]");
                String title = line.substring(4).trim();

                Task task = new Task(title);

                if (isDone) {
                    task.markDone();
                }

                tasks.add(task);
            }

        } catch (IOException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        }

        return tasks;
    }
}