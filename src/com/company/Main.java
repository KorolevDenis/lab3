package com.company;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
  public static void main(String[] args) {
    Queue queue = new Queue();
    Thread generator = new Thread(new Generator(queue));
    Thread robot1 = new Thread(new Robot(Disciplines.MATH, queue));
    Thread robot2 = new Thread(new Robot(Disciplines.OOP, queue));
    Thread robot3 = new Thread(new Robot(Disciplines.PHISICS, queue));

    generator.start();

    robot1.start();
    robot2.start();
    robot3.start();
  }
}
