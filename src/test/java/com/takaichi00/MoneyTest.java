package com.takaichi00;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoneyTest {

  private Bank bank;
  private Expression fiveBucks;
  private Expression tenFrances;

  @BeforeEach
  void beforeEach() {
    bank = new Bank();
    fiveBucks = Money.dollar(5);
    tenFrances = Money.franc(10);
  }

  @Test
  public void testEquality() {
    assertTrue(Money.dollar(5).equals(Money.dollar(5)));
    assertFalse(Money.dollar(5).equals(Money.dollar(6)));
    assertFalse(Money.franc(5).equals(Money.dollar(5)));
  }

  @Test
  void testFrancMultiplication() {
    Money five = Money.franc(5);
    assertEquals(Money.franc(10), five.times(2));
    assertEquals(Money.franc(15), five.times(3));
  }

  @Test
  void testCurrency() {
    assertEquals("USD", Money.dollar(1).currency());
    assertEquals("CHF", Money.franc(1).currency());
  }

  @Test
  void testSimpleAddition() {
    Money five = Money.dollar(5);
    Expression sum = five.plus(five);
    Money reduced = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(10), reduced);
  }

  @Test
  void testPlusReturnsSum() {
    Money five = Money.dollar(5);
    Expression result = five.plus(five);
    Sum sum = (Sum) result;
    assertEquals(five, sum.augend);
    assertEquals(five, sum.addend);
  }

  @Test
  void testReduceSum() {
    Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(7), result);
  }

  @Test
  void testReduceMoney() {
    Money result = bank.reduce(Money.dollar(1), "USD");
    assertEquals(Money.dollar(1), result);
  }

  @Test
  void testReduceMoneyDifferentCurrency() {
    bank.addRate("CHF", "USD", 2);
    Money result = bank.reduce(Money.franc(2), "USD");
    assertEquals(Money.dollar(1), result);
  }

  @Test
  void testIdentityRate() {
    assertEquals(1, bank.rate("USD", "USD"));
  }

  @Test
  void testMixedAddition() {
    bank.addRate("CHF", "USD", 2);
    Money result = bank.reduce(fiveBucks.plus(tenFrances), "USD");
    assertEquals(Money.dollar(10), result);
  }

  @Test
  public void testSumPlusMoney() {
    bank.addRate("CHF", "USD", 2);
    Expression sum = new Sum(fiveBucks, tenFrances).plus(fiveBucks);
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(15), result);
  }

  @Test
  void testSumTimes() {
    bank.addRate("CHF", "USD", 2);
    Expression sum = new Sum(fiveBucks, tenFrances).times(2);
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(20), result);
  }
}
