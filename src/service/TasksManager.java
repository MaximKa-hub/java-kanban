package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TasksManager {

    List<Task> getAllTasks();
    List<Subtask> getAllSubtasks();
    List<Epic> getAllEpics();
    List<Task> getHistory();

    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubtasks();

    Task getTaskById(int id);
    Epic getEpicById(int id);
    Subtask getSubtaskById(int id);

    int createTask(Task task);
    int createEpic(Epic epic);
    int createSubtask(Subtask subtask);

    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subtask);

    void removeTaskById(int id);
    void removeEpicById(int id);
    void removeSubtaskById(int id);

    List<Subtask> getSubtasksByEpicId(int epicId);

}
