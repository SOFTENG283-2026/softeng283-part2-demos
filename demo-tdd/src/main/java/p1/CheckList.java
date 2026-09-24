package p1;

import java.util.HashSet;
import java.util.Set;

/** CheckList */
public class CheckList {

  final Set<String> taskPending;

  public CheckList() {
    taskPending = new HashSet<>();
  }

  public int getIncompleteTasks() {
    return taskPending.size();
  }

  public void addTask(String string) {
    taskPending.add(string);
  }

  public void completeTask(String id) {
    taskPending.remove(id);
  }
}
