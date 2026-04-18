package ui;

import service.FileService;
import service.TaskService;
import java.util.Scanner;

public class ConsoleUI {
    private final TaskService taskService;

    Scanner scanner = new Scanner(System.in);

    public ConsoleUI(TaskService taskService) {
        this.taskService = taskService;
    }

    public void start() {
        taskService.getTasks().addAll(FileService.load());

        while (true) {
            printMenu();

            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> addTask();
                    case 2 -> showTasks();
                    case 3 -> markDone();
                    case 4 -> removeTask();
                    case 5 -> saveTasks();
                    case 0 -> {
                        System.out.println("Выход...");
                        return;
                    }
                    default -> System.out.println("❌ Вы ввели неправильную команду. Попробуйте еще раз\n");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Введите число\n");
            } catch (Exception e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("==== TODO LIST ====");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Показать задачи");
        System.out.println("3. Пометить задачу выполненной");
        System.out.println("4. Удалить задачу");
        System.out.println("5. Сохранить задачи в файл");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void addTask() {
        System.out.print("Введите название: ");
        String titleForAdd = scanner.nextLine();
        taskService.addTask(titleForAdd);
    }

    private void showTasks() {
        taskService.showTasks();
    }

    private void markDone() {
        System.out.println("Введите название: ");
        String titleForMarkDone = scanner.nextLine();
        taskService.markDone(titleForMarkDone);
    }

    private void removeTask() {
        System.out.println("Введите название: ");
        String titleForRemove = scanner.nextLine();
        taskService.removeTask(titleForRemove);
    }

    private void saveTasks() {
        FileService.save(taskService.getTasks());
        System.out.println("✅ Сохранено\n");
    }
}