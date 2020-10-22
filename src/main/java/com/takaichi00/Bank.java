package com.takaichi00;

import java.util.HashMap;
import java.util.Map;

public class Bank {

  private Map<Pair, Integer> rates = new HashMap<>();

  public Money reduce(Expression from, String to) {
    return from.reduce(this, to);
  }

  public void addRate(String from, String to, int rate) {
    rates.put(new Pair(from, to), rate);
  }

  int rate(String from, String to) {
    return rates.get(new Pair(from, to));
  }
}
