package p1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CheckListTest {

  // - A new checklist has zero incomplete tasks.
  @Test
  public void checklist_whenNew_zeroIncompleteTasks() {
    // ARRANGE
    CheckList checklist = new CheckList();
    // ACT
    int result = checklist.getIncompleteTasks();
    // ASSERT
    assertEquals(0, result);
  }

  // - Adding a task increases the number of incomplete tasks by one.
  @Test
  public void checklist_addingNewTask_plusOneIncompleteTasks() {
    // ARRANGE
    CheckList checklist = new CheckList();
    // ACT
    checklist.addTask("ID1");
    int result = checklist.getIncompleteTasks();
    // ASSERT
    assertEquals(1, result);
  }

  @Test
  public void checklist_addingTwoNewTask_plusOneIncompleteTasks() {
    // ARRANGE
    CheckList checklist = new CheckList();
    // ACT
    checklist.addTask("ID1");
    checklist.addTask("ID2");

    int result = checklist.getIncompleteTasks();
    // ASSERT
    assertEquals(2, result);
  }

  @Test
  public void checklist_addingThreeNewTask_plusOneIncompleteTasks() {
    // ARRANGE
    CheckList checklist = new CheckList();
    // ACT
    checklist.addTask("ID1");
    checklist.addTask("ID2");
    checklist.addTask("ID3");
    int result = checklist.getIncompleteTasks();
    // ASSERT
    assertEquals(3, result);
  }

  @Test
  public void checklist_completingTask_reduceIncompleteTasksByOne() {
    // ARRANGE
    CheckList checklist = new CheckList();
    // ACT
    checklist.addTask("ID1");
    checklist.completeTask("ID1");

    int result = checklist.getIncompleteTasks();
    // ASSERT
    assertEquals(0, result);
  }

    @Test
  public void checklist_completingTheSameTask_doesNotReduceTheCounter() {
    // ARRANGE
    CheckList checklist = new CheckList();
    // ACT
    checklist.addTask("ID1");
    checklist.completeTask("ID1");
    checklist.completeTask("ID1");

    int result = checklist.getIncompleteTasks();
    // ASSERT
    assertEquals(0, result);
  }
}
