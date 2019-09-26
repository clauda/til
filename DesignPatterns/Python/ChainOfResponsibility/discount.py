# -*- coding: UTF-8 -*-
class DiscountPerItems(object):

  def __init__(self, callback):
    self._callback = callback

  def apply(self, budget):
    if budget.total_items > 5:
      return budget.valor * 0.1
    else:
      return self._callback.apply(budget)


class DiscountPerValue(object):

  def __init__(self, callback):
    self._callback = callback

  def apply(self, budget):
    if budget.amount > 500:
      return budget.amount * 0.07
    else:
      return self._callback.apply(budget)


class NoDiscountAtAll(object):

  def apply(self, budget):
    return 0
