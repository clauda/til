# -*- coding: UTF-8 -*-


class FeeCalculator(object):

  def calculate(self, budget, callback):
    amount = callback.apply(budget)
    print(amount)


if __name__ == '__main__':
  from models import Budget, Item
  from fake_fee import BreathingFee, ThinkingFee

  calculator = FeeCalculator()

  budget = Budget()
  budget.add(Item('Item A', 100.0))
  budget.add(Item('Item B', 50.0))
  budget.add(Item('Item C', 400.0))

  print('Applying BreathingFee:')
  calculator.calculate(budget, BreathingFee())
  print('Applying ThinkingFee:')
  calculator.calculate(budget, ThinkingFee())
