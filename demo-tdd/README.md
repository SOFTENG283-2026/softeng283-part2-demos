# SOFTENG 283 — TDD lecture demo

Implement a task checklist using **strict TDD**: write one failing test, add only enough code to make it pass, then refactor while the tests remain green. Repeat for each requirement.

## Requirements

- A new checklist has zero incomplete tasks.
- Adding a task increases the number of incomplete tasks by one.
- Completing a task reduces the number of incomplete tasks by one. Completing the same task again does not change the count.

Assume each task has a unique ID and only tasks that have been added can be completed.
