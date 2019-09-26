# -*- coding: UTF-8 -*-


class FeeCalculator(object):

  def calculate(self, budget, callback):
    amount = callback.apply(budget)
    print(amount)


if __name__ == '__main__':
  from models import Budget, Item
  from fee import VehicleFee, ProductFee, ServiceFee, BreathingFee, ThinkingFee

  calculator = FeeCalculator()

  budget = Budget()
  budget.add(Item('Car', 100.0))
  budget.add(Item('Laundry', 50.0))
  budget.add(Item('Cake', 350.0))

  print('Applying BreathingFee:')
  calculator.calculate(budget, BreathingFee())
  print('Applying ThinkingFee:')
  calculator.calculate(budget, ThinkingFee())
  print('Applying ServiceFee:')
  calculator.calculate(budget, ServiceFee())
  print('Applying VehicleFee:')
  calculator.calculate(budget, VehicleFee())
  print('Applying ProductFee:')
  calculator.calculate(budget, ProductFee())
  print('Applying VehicleFee over ProductFee:')
  calculator.calculate(budget, ProductFee(VehicleFee()))
