import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskManager{
    private static final String FILE_NAME="tasks.json";
    private List<Task> tasks;

    public TaskManager(){
        tasks = new ArrayList();
        loadTasks();
    }

    private void loadTasks() {
        try{
            if(!Files.exists(Paths.get(FILE_NAME))){
                Files.write(Paths.get(FILE_NAME),"[]".getBytes());
            }
            String content=new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            tasks = parseTasksFromJson(content);
        } catch(IOException e){
            System.out.println("Error reading tasks file: "+e.getMessage());
        }
    }

    private List<Task> parseTasksFromJson(String json) {
        List<Task> taskList=new ArrayList<>();

        json=json.trim();
        if(json.length()<=2) return taskList;

        String trimmed=json.substring(1,json.length()-1).trim();
        if(trimmed.isEmpty()) return taskList;

        String[] taskStrings= trimmed.split("\\},\\s*\\{");

        for(String taskStr : taskStrings){
            if(!taskStr.startsWith("{")) taskStr="{"+ taskStr;
            if(!taskStr.endsWith("}")) taskStr=taskStr + "}";

            Task task=parseSingleTask(taskStr);
            if (task != null) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    private Task parseSingleTask(String taskJson) {
        try{
            Map<String,String> map=new HashMap<>();
            String inner=taskJson.substring(1,taskJson.length()-1);
            String[] fields=inner.split(",(?=\\s*\"\\w+\"\\s*:)");
            for(String field : fields){
                String[] kv=field.split(":",2);
                if(kv.length==2){
                    String key=kv[0].trim().replaceAll("\"","");
                    String value=kv[1].trim().replaceAll("\"","");
                    map.put(key,value);
                }
            }
            int id=Integer.parseInt(map.getOrDefault("id","0"));
            String description=map.getOrDefault("description","");
            String status=map.getOrDefault("status","todo");
            LocalDateTime createdAt= LocalDateTime.parse(map.getOrDefault("createdAt", LocalDateTime.now().toString()));
            LocalDateTime updatedAt= LocalDateTime.parse(map.getOrDefault("updatedAt", LocalDateTime.now().toString()));

            Task task=new Task(id,description);
            task.setStatus(status);

            task.setCreatedAt(createdAt);
            task.setUpdatedAt(updatedAt);

            return task;
        }
        catch(Exception e){
            System.out.println("Failed to parse task: "+ e.getMessage());
            return null;
        }
    }
    private void saveTasks() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("[\n");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                sb.append("  {\n");
                sb.append("    \"id\": ").append(t.getId()).append(",\n");
                sb.append("    \"description\": \"").append(escapeJson(t.getDescription())).append("\",\n");
                sb.append("    \"status\": \"").append(t.getStatus()).append("\",\n");
                sb.append("    \"createdAt\": \"").append(t.getCreatedAt()).append("\",\n");
                sb.append("    \"updatedAt\": \"").append(t.getUpdatedAt()).append("\"\n");
                sb.append("  }");
                if (i != tasks.size() -1) sb.append(",");
                sb.append("\n");
            }
            sb.append("]\n");
            Files.write(Paths.get(FILE_NAME), sb.toString().getBytes());
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private String escapeJson(String s) {
        return s.replace("\"", "\\\"");
    }

    // Add a new task
    public void addTask(String description) {
        int newId = generateNewId();
        Task task = new Task(newId, description);
        tasks.add(task);
        saveTasks();
        System.out.println("Task added successfully (ID: " + newId + ")");
    }

    private int generateNewId() {
        int maxId = 0;
        for (Task t : tasks) {
            if (t.getId() > maxId) maxId = t.getId();
        }
        return maxId + 1;
    }

    public void updateTask(int id, String newDescription) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setDescription(newDescription);
            saveTasks();
            System.out.println("Task updated successfully (ID: " + id + ")");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }
    public void deleteTask(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            saveTasks();
            System.out.println("Task deleted successfully (ID: " + id + ")");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    public void markInProgress(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setStatus("in-progress");
            saveTasks();
            System.out.println("Task marked as in-progress (ID: " + id + ")");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    public void markDone(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setStatus("done");
            saveTasks();
            System.out.println("Task marked as done (ID: " + id + ")");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }


    public void listTasks(String statusFilter) {
        List<Task> filteredTasks = new ArrayList<>();

        if (statusFilter == null || statusFilter.isEmpty()) {
            filteredTasks = tasks;
        } else {
            for (Task task : tasks) {
                if (task.getStatus().equalsIgnoreCase(statusFilter)) {
                    filteredTasks.add(task);
                }
            }
        }

        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found" + (statusFilter == null ? "." : " with status '" + statusFilter + "'."));
        } else {
            for (Task task : filteredTasks) {
                System.out.println(task);
            }
        }
    }


    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

}

