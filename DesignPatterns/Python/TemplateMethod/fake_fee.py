# -*- coding: UTF-8 -*-
# Abstract Classes
from abc import ABCMeta, abstractmethod


class Overtax(object):

  def apply(self, budget):
    if self.should_apply_max_fee(budget):
      return self.max_fee(budget)
    else:
      return self.min_fee(budget)

  @abstractmethod
  def should_apply_max_fee(self, budget): pass

  @abstractmethod
  def max_fee(self, budget): pass

  @abstractmethod
  def min_fee(self, budget): pass


class BreathingFee(Overtax):

  def should_apply_max_fee(self, budget):
    return budget.amount > 500

  def max_fee(self, budget):
    return budget.amount * 0.07

  def min_fee(self, budget):
    return budget.amount * 0.05


class ThinkingFee(Overtax):

  def should_apply_max_fee(self, budget):
    return budget.amount > 500 and self.__has_expensive_item(budget)

  def max_fee(self, budget):
    return budget.amount * 0.10

  def min_fee(self, budget):
    return budget.amount * 0.06

  def __has_expensive_item(self, budget):
    for item in budget.items():
      if item.price > 100:
        return True
    return False
