package com.takaichi00;

public class Dollar extends Money {
  public Dollar(int amount, String currency) {
    super(amount, currency);
  }

  public Money times(int multiplier) {
    return Money.dollar(this.amount * multiplier);
  }
}
