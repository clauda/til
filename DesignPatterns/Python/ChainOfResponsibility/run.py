# -*- coding: UTF-8 -*-
from discount import DiscountPerItems, DiscountPerValue, NoDiscountAtAll


class DiscountCalculator(object):

  def calculate(self, budget):
    discounter = DiscountPerItems(DiscountPerValue(NoDiscountAtAll()))

    return discounter.apply(budget)


if __name__ == '__main__':
  from models import Budget, Item

  budget = Budget()
  budget.add(Item('Item A', 100.0))
  budget.add(Item('Item B', 50.0))
  budget.add(Item('Item C', 400.0))

  calculator = DiscountCalculator()
  discount = calculator.calculate(budget)
  print('Discount applied: %s' % (discount))
