package com.takaichi00;

public class Bank {
  public Money reduce(Expression source, String to) {
    return source.reduce(to);
  }

  public void addRate(String from, String to, int rate) {

  }
}
