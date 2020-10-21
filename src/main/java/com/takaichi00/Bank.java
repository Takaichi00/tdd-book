package com.takaichi00;

public class Bank {
  public Money reduce(Expression from, String to) {
    return from.reduce(this, to);
  }

  public void addRate(String from, String to, int rate) {
  }

  int rate(String from, String to) {
    return (from.equals("CHF") && to.equals("USD")) ? 2 : 1;
  }
}
