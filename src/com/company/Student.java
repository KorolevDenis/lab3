package com.company;

import java.util.*;

public class Student {

  final private static Random random = new Random();

  private int labsCount;
  private Disciplines discipline;

  public Student() {
    discipline = Disciplines.values()[random.nextInt(Disciplines.values().length)];
    int[] labs = {10, 20, 100};
    this.labsCount = labs[random.nextInt(3)];
  }

  public Disciplines getDiscipline() {
    return discipline;
  }

  public int getLabsCount() {
    return labsCount;
  }
}
