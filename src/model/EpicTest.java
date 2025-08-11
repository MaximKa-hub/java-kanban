package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    private TasksManager taskManager;
    private HistoryManager historyManager;

    @BeforeEach
    void setUp() {
        taskManager = Managers.getDefault();
        historyManager = Managers.getDefaultHistory();
    }

    @Test
    void TasksWithIdShouldBeEquals() {
        Task task1 = new Task("Task-1", "Description-1", TaskStatus.NEW);
        task1.setId(1);
        Task task2 = new Task("Task-2", "Description-2", TaskStatus.NEW);
        task2.setId(1);

        assertEquals(task1, task2);
    }

    @Test
    void SubtasksWithIdShouldBeEquals() {
        Epic epic1 = new Epic("Epic-1", "Description - 1");
        epic1.setId(1);

        Subtask subtask1 = new Subtask("Subtask-1", "Description-1.1", TaskStatus.DONE, 1);
        subtask1.setId(2);
        Subtask subtask2 = new Subtask("Subtask-2", "Description-1.2", TaskStatus.DONE, 1);
        subtask2.setId(2);

        assertEquals(subtask1, subtask2);
    }

    @Test
    void ManagersReturnInitializedManager() {
        TasksManager tasksManager = Managers.getDefault();

        assertNotNull(tasksManager);
        assertTrue(tasksManager instanceof TasksManager);

    }

    @Test
    void shouldAddAndFindDifferentTaskTypesById() {
        Task task = new Task("Task-1", "Description-1", TaskStatus.NEW);
        Epic epic = new Epic("Epic-1", "Description-2");
        epic.setId(2);
        Subtask subtask = new Subtask("Subtask-1", "Description-3", TaskStatus.NEW, 2);

        int taskId = taskManager.createTask(task);
        int epicId = taskManager.createEpic(epic);
        int subtaskId = taskManager.createSubtask(subtask);


        assertNotNull(taskManager.getTaskById(taskId));
        assertNotNull(taskManager.getEpicById(epicId));
        assertNotNull(taskManager.getSubtaskById(subtaskId));

        assertEquals(task, taskManager.getTaskById(taskId));
        assertEquals(epic, taskManager.getEpicById(epicId));
        assertEquals(subtask, taskManager.getSubtaskById(subtaskId));

        assertTrue(taskManager.getTaskById(taskId) instanceof Task);
        assertTrue(taskManager.getEpicById(epicId) instanceof Epic);
        assertTrue(taskManager.getSubtaskById(subtaskId) instanceof Subtask);
    }

    @Test
    void taskFieldsShouldRemainUnchangedAfterAdding() {
        Task originalTask = new Task("Task", "Description", TaskStatus.IN_PROGRESS);

        String originalName = originalTask.getName();
        String originalDescription = originalTask.getDescription();
        TaskStatus originalStatus = originalTask.getStatus();

        int taskId = taskManager.createTask(originalTask);

        Task retrievedTask = taskManager.getTaskById(taskId);

        assertEquals(originalName, retrievedTask.getName());
        assertEquals(originalDescription, retrievedTask.getDescription());
        assertEquals(originalStatus, retrievedTask.getStatus());
        assertEquals(taskId, retrievedTask.getId());
        assertSame(originalTask, retrievedTask);
    }

    @Test
    void shouldPreservePreviousTaskVersionsInHistory() {
        Task originalTask = new Task("Original", "Description", TaskStatus.NEW);
        originalTask.setId(1);
        historyManager.add(originalTask);

        Task modifiedTask = new Task("Modified", "Updated description", TaskStatus.IN_PROGRESS);
        modifiedTask.setId(1);
        historyManager.add(modifiedTask);

        ArrayList<Task> history = historyManager.getHistory();

        assertEquals(2, history.size());

        Task lastVersion = history.getLast();
        assertEquals("Modified", lastVersion.getName());
        assertEquals("Updated description", lastVersion.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, lastVersion.getStatus());

        Task firstVersion = history.getFirst();
        assertEquals("Original", firstVersion.getName());
        assertEquals("Description", firstVersion.getDescription());
        assertEquals(TaskStatus.NEW, firstVersion.getStatus());

        assertNotSame(originalTask, lastVersion);
        assertNotSame(modifiedTask, firstVersion);
    }
}
