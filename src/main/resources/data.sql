DELETE FROM category;

INSERT INTO category (categoryID, categorytype) VALUES
                    (10, 'Personal'),
                    (11, 'Work'),
                    (12, 'Home');

DELETE FROM task;

INSERT INTO task(id, taskName, dueDate, isCompleted, isArchived) VALUES
                  ( 1, 'Buy groceries', '2025-06-15', false, false),
                  ( 2, 'Finish project report', '2025-06-20', false, false),
                  ( 3, 'Read a book', '2025-06-18', false, false),
                  ( 4, 'Pay electricity bill', '2025-06-12', true, true),
                  ( 5, 'Clean the garage', '2025-06-16', false, false),
                  ( 6, 'Weekly team sync', '2025-06-14', false, false),
                  ( 7, 'Plan vacation', '2025-06-22', false, false),
                  ( 8, 'Brainstorm app ideas', '2025-06-19', false, false),
                  ( 9, 'Organize files', '2025-06-13', true, true),
                  ( 10, 'Meditation practice', '2025-06-17', false, false),
                  ( 11, 'Follow up with client', '2025-06-20', false, false);


DELETE FROM task_category;


INSERT INTO task_category(taskID, categoryID) VALUES
                  (1, 10),
                  (1, 11),
                  (2, 12);