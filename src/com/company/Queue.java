package com.company;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Queue {

  private BlockingQueue<Student> students = new ArrayBlockingQueue<Student>(10);

  private static ReentrantLock locker = new ReentrantLock();
  private static Condition conditionRobo = locker.newCondition();
  private static Condition conditionGen = locker.newCondition();

  public Queue() {
  }

  public void put() {
    Student student = new Student();
    locker.lock();
    try {
      while (students.size() == 10) {
        conditionGen.await();
      }
      sleep(150);
      conditionRobo.signalAll();
      students.add(student);
      System.out.println("Student generation " + student.getLabsCount() + " " + student.getDiscipline());
    } catch (InterruptedException | NullPointerException e) {
      e.printStackTrace();
    } finally {
      locker.unlock();
    }
  }

  public void get(Disciplines discipline) {
    Student student = null;
    try {
      locker.lock();
      while (students.peek() == null || !students.peek().getDiscipline().equals(discipline)) {
        System.out.println("Robot wait" + " " + Thread.currentThread().getName());
        conditionRobo.await();
      }
      sleep(20);
      conditionGen.signalAll();
      student = students.remove();

    } catch (InterruptedException | NullPointerException e) {
      e.printStackTrace();
    } finally {
      locker.unlock();
      if (student == null) {
        System.err.println("Error! Student == null ");
      }
    }

    int labsCount = student.getLabsCount();
    Disciplines disciplines = student.getDiscipline();
    System.out.println("Robot generation " + labsCount + " " + disciplines + " " + Thread.currentThread().getName());
    while (labsCount > 0) {
      System.out.println("Robot completion " + labsCount + " " + disciplines + " " + Thread.currentThread().getName());
      labsCount -= 5;
      try {
        sleep(40);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
