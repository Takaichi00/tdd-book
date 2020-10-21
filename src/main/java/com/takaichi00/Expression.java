package com.takaichi00;

public interface Expression {
  Money reduce(Bank bank, String to);
}
