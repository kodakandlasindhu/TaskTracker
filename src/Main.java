public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        TaskManager manager = new TaskManager();

        String command = args[0];

        try {
            switch (command) {
                case "add":
                    if (args.length < 2) {
                        System.out.println("Usage: add \"task description\"");
                    } else {
                        // Join args after command as description (in case of spaces)
                        String description = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
                        manager.addTask(description);
                    }
                    break;

                case "update":
                    if (args.length < 3) {
                        System.out.println("Usage: update <taskId> \"new description\"");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        String newDesc = String.join(" ", java.util.Arrays.copyOfRange(args, 2, args.length));
                        manager.updateTask(id, newDesc);
                    }
                    break;

                case "delete":
                    if (args.length < 2) {
                        System.out.println("Usage: delete <taskId>");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        manager.deleteTask(id);
                    }
                    break;

                case "mark-in-progress":
                    if (args.length < 2) {
                        System.out.println("Usage: mark-in-progress <taskId>");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        manager.markInProgress(id);
                    }
                    break;

                case "mark-done":
                    if (args.length < 2) {
                        System.out.println("Usage: mark-done <taskId>");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        manager.markDone(id);
                    }
                    break;

                case "list":
                    if (args.length == 1) {
                        manager.listTasks(null);  // list all
                    } else {
                        String status = args[1];
                        manager.listTasks(status);
                    }
                    break;

                default:
                    System.out.println("Unknown command: " + command);
                    printUsage();
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task ID. Please provide a numeric value.");
        }
    }

    private static void printUsage() {
        System.out.println("Task Tracker CLI usage:");
        System.out.println("  add \"task description\"");
        System.out.println("  update <taskId> \"new description\"");
        System.out.println("  delete <taskId>");
        System.out.println("  mark-in-progress <taskId>");
        System.out.println("  mark-done <taskId>");
        System.out.println("  list [status]");
        System.out.println("    status can be todo, in-progress, done");
    }
}