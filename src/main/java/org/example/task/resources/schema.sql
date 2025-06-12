CREATE TABLE IF NOT EXISTS category (
      categoryID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
      categoryType ENUM(
        'Personal',
        'Work',
        'Learning',
        'Home',
        'Shopping',
        'Ideas',
        'Planning',
        'Waiting',
        'Recurring'
    ) NOT NULL
);


CREATE TABLE IF NOT EXISTS task (
      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
      taskName VARCHAR(255) NOT NULL,
      dueDate DATE NOT NULL,
      isCompleted boolean NOT NULL,
      isArchived boolean NOT NULL
);


CREATE TABLE IF NOT EXISTS task_category (
    taskID INT NOT NULL,
    categoryID INT NOT NULL,
    PRIMARY KEY (taskID, categoryID),
    FOREIGN KEY (categoryID) REFERENCES category(categoryID) ON DELETE CASCADE ,
    FOREIGN KEY (taskID) REFERENCES Task(id) ON DELETE CASCADE
);