package com.company;

public class Generator implements Runnable {

  private static Queue queue;

  public Generator(Queue queue) {
    Generator.queue = queue;
  }

  @Override
  public void run() {
    while (true) {
      queue.put();
    }
  }
}
