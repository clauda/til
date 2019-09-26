# -*- coding: UTF-8 -*-
# State is a behavioral design pattern that lets an
# object alter its behavior when its internal state
# changes. It appears as if the object changed its class.
from models import Budget, Item


if __name__ == '__main__':
  budget = Budget()
  budget.add(Item('Car', 100.0))
  budget.add(Item('Laundry', 50.0))
  budget.add(Item('Cake', 400))

  print(budget.amount)
  print(budget.status)

  budget.approve()      # or budget.disapprove()
  print(budget.status)

  budget.apply_bonus()
  print(budget.amount)

  budget.finish()
  print(budget.status)
