# -*- coding: UTF-8 -*-


class ProductFee(object):

  def apply(self, budget):
    return budget.amount * 0.1


class ServiceFee(object):

  def apply(self, budget):
    return budget.amount * 0.06
