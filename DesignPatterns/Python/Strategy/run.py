# -*- coding: UTF-8 -*-
# By definition, the Strategy Pattern defines a family of
# an algorithm and encapsulate each of them in you own class,
# that way it will enable that the strategy can be interchanged.


class FeeCalculator(object):

  def calculate(self, budget, callback):
    amount = callback.apply(budget)
    print(amount)


if __name__ == '__main__':
  from models import Budget
  from fee import ServiceFee, ProductFee

  budget = Budget(500.0)
  calculator = FeeCalculator()
  print('Applying ServiceFee:')
  calculator.calculate(budget, ServiceFee())
  print('Applying ProductFee:')
  calculator.calculate(budget, ProductFee())
