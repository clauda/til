# -*- coding: UTF-8 -*-


def bribery(func):
  def wrapper(self, budget):
    return func(self, budget) + 50.0
  return wrapper
