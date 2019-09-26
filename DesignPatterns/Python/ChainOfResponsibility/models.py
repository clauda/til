# -*- coding: UTF-8 -*-
class Budget(object):

  def __init__(self):
    self.__items = []

  @property
  def amount(self):
    return sum([i.price for i in self.__items])

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
