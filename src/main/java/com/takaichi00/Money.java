package com.takaichi00;

public abstract class Money {
  protected int amount;
  protected String currency;

  Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public boolean equals(Object o) {
    Money money = (Money) o;
    return amount == money.amount && getClass().equals(money.getClass());
  }

  abstract Money times(int multiplier);

  String currency() {
    return currency;
  }

  static Money dollar(int amount) {
    return new Dollar(amount, "USD");
  }

  static Money franc(int amount) {
    return new Franc(amount, "CHF");
  }
}

