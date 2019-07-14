package ru.job4j.ch01_Collections.task2;

import ru.job4j.ch01_Collections.task2.*;

import java.util.LinkedList;

public class PriorityQueue {
   private LinkedList<Task> tasks = new LinkedList<Task>();

   public int getTasksSize() {
      return tasks.size();
   }

   /**
    * Метод должен вставлять в нужную позицию элемент.
    * Позиция определять по полю приоритет.
    * Для вставик использовать add(int index, E value)
    * @param task задача
    */
   public void put(Task task) {
      int count = 0;

      //if (!tasks.isEmpty()) {
         int queueSize = tasks.size();
         for (int i = 0; i < queueSize; i++) {
            if (task.getPriority() < tasks.get(i).getPriority()) {
               count = i;
               break;
            } /*else if (i == queueSize - 1) {
               count = i;
               break;
            }*/
         }
      //}
      tasks.add(count, task);
   }

   public Task take() {
      return this.tasks.poll();
   }
}
