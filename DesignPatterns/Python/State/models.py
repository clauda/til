# -*- coding: UTF-8 -*-
from statuses import *


class Budget(object):

  def __init__(self):
    self.__items = []
    self.status = UnderReview()
    self.__bonus = 0
    self.__was_discount_applied = False

  def approve(self):
    self.status.approve(self)

  def disapprove(self):
    self.status.disapprove(self)

  def finish(self):
    self.status.finish(self)

  def apply_bonus(self):
    if self.__was_discount_applied:
      raise Exception('Discount already applied!')
    else:
      self.__was_discount_applied = True
      return self.status.apply_discount(self)

  def add_bonus(self, bonus):
    self.__bonus += bonus
    print('Discount applied: %s' % (self.__bonus))
    return self.__bonus

  @property
  def amount(self):
    return sum([i.price for i in self.__items]) - self.__bonus

  def items(self):
    return tuple(self.__items)

  @property
  def total_items(self):
    return len(self.__items)

  def add(self, item):
    self.__items.append(item)


class Item(object):

  def __init__(self, name, price):
    self.__name = name
    self.__price = price

  @property
  def price(self):
    return self.__price

  @property
  def name(self):
    return self.__name
