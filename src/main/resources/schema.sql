CREATE TABLE IF NOT EXISTS category (
      categoryID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
      categoryType VARCHAR(255) UNIQUE NOT NULL
);


CREATE TABLE IF NOT EXISTS task (
      id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
      taskName VARCHAR(255) NOT NULL,
      dueDate DATE NOT NULL,
      isCompleted boolean NOT NULL,
      isArchived boolean NOT NULL
);


CREATE TABLE IF NOT EXISTS task_category (
    taskID BIGINT NOT NULL,
    categoryID BIGINT NOT NULL,
    PRIMARY KEY (taskID, categoryID),
    FOREIGN KEY (categoryID) REFERENCES category(categoryID) ON DELETE CASCADE ,
    FOREIGN KEY (taskID) REFERENCES task(id) ON DELETE CASCADE
);