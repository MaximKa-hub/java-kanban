import model.Epic;
import model.Subtask;
import model.Task;
import service.TaskStatus;
import service.TasksManager;

public class Main {

    public static void main(String[] args) {

        TasksManager manager = TasksManager.getTasks();

        Task task1 = new Task("model.Task #1", "Task1 description", TaskStatus.NEW);
        Task task2 = new Task("model.Task #2", "Task2 description", TaskStatus.IN_PROGRESS);
        final int taskId1 = manager.createTask(task1);
        final int taskId2 = manager.createTask(task2);

        Epic epic1 = new Epic("model.Epic #1", "Epic1 description");
        Epic epic2 = new Epic("model.Epic #2", "Epic2 description");
        final int epicId1 = manager.createEpic(epic1);
        final int epicId2 = manager.createEpic(epic2);

        Subtask subtask1 = new Subtask("model.Task #1-1", "SubTask1 description", TaskStatus.DONE, epicId1);
        Subtask subtask2 = new Subtask("model.Task #1-2", "SubTask2 description", TaskStatus.IN_PROGRESS, epicId1);
        Subtask subtask3 = new Subtask("model.Task #2-1", "SubTask3 description", TaskStatus.NEW, epicId2);
        final int subtaskId1 = manager.createSubtask(subtask1);
        final int subtaskId2 = manager.createSubtask(subtask2);
        final int subtaskId3 = manager.createSubtask(subtask3);

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());

        Subtask subtask4 = new Subtask("model.Task #1-3", "SubTask4 description", TaskStatus.DONE, epicId1);
        subtask4.setId(subtaskId2);
        manager.updateSubtask(subtask4);

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());

        manager.removeTaskById(task1.getId());
        manager.removeSubtaskById(subtask4.getId());

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());
    }
}
