package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.model.Task;
import tr.com.huseyinaydin.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(task);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTask(String taskId) {
        return repository.findById(taskId).get();
    }

    public Task updateTask(Task taskRequest) {
        Task existingTask = repository.findById(taskRequest.getTaskId()).get();// DB data
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setPriority(taskRequest.getPriority());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        return repository.save(existingTask);
    }

    public String deleteTask(String taskId) {
        repository.deleteById(taskId);
        return taskId + " görev silindi.";
    }

    public List<Task> getTaskByAssigneeAndPriority(String assignee, String priority){
        //return repository.findByAssigneeAndPriority(assignee,priority);
        return repository.findTaskWithAssigneeAndPriority(assignee, priority);
    }
}