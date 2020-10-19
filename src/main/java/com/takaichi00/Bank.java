package com.takaichi00;

public class Bank {
  public Money reduce(Expression source, String to) {
    return source.reduce(to);
  }
}
