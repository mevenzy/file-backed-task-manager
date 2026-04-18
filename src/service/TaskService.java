package service;

import model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();

    // Добавить задачу
    public void addTask(String title) {
        Task task = new Task(title);
        tasks.add(task);
        System.out.println("✅ Задача добавлена\n");
    }

    // Показать задачи
    public void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("⚠️ Список пуст\n");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
            System.out.println();
        }
    }

    // Пометить задачу выполненной
    public void markDone(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.markDone();
                System.out.println("✅ Задача выполнена\n");
                return;
            }
        }

        System.out.println("❌ Задача не найдена\n");
    }

    // Удалить задачу
    public void removeTask(String title) {
        boolean isRemove = tasks.removeIf(task -> task.getTitle() == title);

        if (isRemove) {
            System.out.println("✅ Задача была удалена\n");
        } else {
            System.out.println("❌ Задача не найдена\n");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }
}