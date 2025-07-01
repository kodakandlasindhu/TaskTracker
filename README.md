# Task Tracker CLI

## Overview
Task Tracker is a simple and efficient application for managing tasks. It allows users to create, update, and delete tasks while storing them persistently. This project is built using Java and follows an Object-Oriented Programming (OOP) approach.

## Features
- Add new tasks with a title and description
- View all tasks in a structured format
- Update task details
- Delete completed or unwanted tasks
- Save tasks in a JSON format for persistence

## Technologies Used
- Java 
- JSON for data storage
- File Handling for saving tasks persistently

## Installation
### Prerequisites
Ensure you have the following installed on your system:
- Java JDK 8 or later
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## How to Use
To use Task Tracker CLI, clone the repository, after that you must compile the app.
```
git clone https://github.com/kodakandlasindhu/TaskTracker.git
cd TaskTracker/src
javac TaskTracker.java
```
## Usage

- **Add a new task**
  - `java TaskTracker add [description] `
- **Delete task by ID**
  - `java TaskTracker update [id] [description]`
- **Update task by ID**
   - `java TaskTracker delete [id] `
- **Mark a task as todo** 
   - `java TaskTracker mark-todo [id]`
- **Mark a task as in progress**
   - `java TaskTracker mark-in-progress [id]`
- **Mark a task as done** 
   - `java TaskTracker mark-done [id]`
- **List all tasks**
   - `java TaskTracker list`
- **List all Todo tasks**
   - `java TaskTracker list todo`
-  **List all In-Progress tasks**
   - `java TaskTracker list in-progress`
-  **List all Done tasks**
   - `java TaskTracker list done`
 
## Contributing
Contributions are welcome! Follow these steps to contribute:
1. Fork the repository.
2. Create a new branch:
   ```sh
   git checkout -b feature-branch
   ```
3. Commit your changes:
   ```sh
   git commit -m "Add new feature"
   ```
4. Push to your branch:
   ```sh
   git push origin feature-branch
   ```
5. Open a Pull Request.
   

## URL Project
  [Roadmap project](https://roadmap.sh/projects/task-tracker)


