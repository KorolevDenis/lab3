package com.company;

public class Robot implements Runnable {

  private Disciplines discipline;
  private static Queue queue;

  public Robot(Disciplines discipline, Queue queue) {
    Robot.queue = queue;
    this.discipline = discipline;
  }

  @Override
  public void run() {
      while (true) {
        queue.get(discipline);
      }
  }
}
