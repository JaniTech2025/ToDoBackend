INSERT INTO category (categoryType) VALUES ('Personal');
INSERT INTO category (categoryType) VALUES ('Work');
INSERT INTO category (categoryType) VALUES ('Learning');
INSERT INTO category (categoryType) VALUES ('Home');
INSERT INTO category (categoryType) VALUES ('Shopping');
INSERT INTO category (categoryType) VALUES ('Ideas');
INSERT INTO category (categoryType) VALUES ('Planning');
INSERT INTO category (categoryType) VALUES ('Waiting');
INSERT INTO category (categoryType) VALUES ('Recurring');


INSERT INTO Task (id, taskName, dueDate, isCompleted, isArchived) VALUES
                                                                              (1, 'Buy groceries', '2025-06-15', false, false),
                                                                              (2, 'Finish project report', '2025-06-20', false, false),
                                                                              (3, 'Read a book', '2025-06-18', false, false),
                                                                              (4, 'Pay electricity bill', '2025-06-12', true, true),
                                                                              (5,'Clean the garage', '2025-06-16', false, false),
                                                                              (6,'Weekly team sync', '2025-06-14', false, false),
                                                                              (7,'Plan vacation', '2025-06-22', false, false),
                                                                              (8,'Brainstorm app ideas', '2025-06-19', false, false),
                                                                              (9,'Organize files', '2025-06-13', true, true),
                                                                              (10,'Meditation practice', '2025-06-17', false, false),
                                                                              (11,'Follow up with client', '2025-06-20', false, false);


INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (1, 4);
INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (1, 5);

INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (2, 2);
INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (2, 7);

INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (3, 3);
INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (3, 1);

INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (4, 4);
INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (4, 1);

INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (5, 4);

INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (6, 2);
INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (6, 8);

INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (7, 1);
INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (7, 4);

INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (8, 6);

INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (9, 7);
INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (9, 2);

INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (10, 1);
INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (10, 9);

INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (11, 2);
INSERT INTO TASK_CATEGORY (taskID, categoryID) VALUES (11, 8);
