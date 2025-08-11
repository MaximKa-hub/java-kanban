import model.Epic;
import model.Subtask;
import model.Task;
import service.*;

public class Main {

    public static void main(String[] args) {

        TasksManager tasksManager = Managers.getDefault();

        Task task1 = new Task("Task #1", "Task1 description", TaskStatus.NEW);
        Task task2 = new Task("Task #2", "Task2 description", TaskStatus.IN_PROGRESS);
        final int taskId1 = tasksManager.createTask(task1);
        final int taskId2 = tasksManager.createTask(task2);

        Epic epic1 = new Epic("Epic #1", "Epic1 description");
        Epic epic2 = new Epic("Epic #2", "Epic2 description");
        final int epicId1 = tasksManager.createEpic(epic1);
        final int epicId2 = tasksManager.createEpic(epic2);
        tasksManager.getEpicById(epicId1);
        tasksManager.getEpicById(epicId2);

        Subtask subtask1 = new Subtask("Task #1-1", "SubTask1 description", TaskStatus.DONE, epicId1);
        Subtask subtask2 = new Subtask("Task #1-2", "SubTask2 description", TaskStatus.IN_PROGRESS, epicId1);
        Subtask subtask3 = new Subtask("Task #2-1", "SubTask3 description", TaskStatus.NEW, epicId2);
        final int subtaskId1 = tasksManager.createSubtask(subtask1);
        final int subtaskId2 = tasksManager.createSubtask(subtask2);
        final int subtaskId3 = tasksManager.createSubtask(subtask3);
        tasksManager.getSubtaskById(subtaskId1);
        tasksManager.getSubtaskById(subtaskId2);
        tasksManager.getSubtaskById(subtaskId3);

        Subtask subtask4 = new Subtask("Task #1-3", "SubTask4 description", TaskStatus.DONE, epicId1);
        subtask4.setId(subtaskId2);
        tasksManager.updateSubtask(subtask4);

        tasksManager.removeTaskById(task1.getId());
        tasksManager.removeSubtaskById(subtask4.getId());

        printAllTasks(tasksManager);
    }

    public static void printAllTasks(TasksManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getAllEpics()) {
            System.out.println(epic);

            for (Task task : manager.getSubtasksByEpicId(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }

}
