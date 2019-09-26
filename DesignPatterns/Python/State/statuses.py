# -*- coding: UTF-8 -*-
from abc import ABCMeta, abstractmethod


class BudgetState(object):
  __metaclass__ = ABCMeta

  @abstractmethod
  def apply_discount(self, budget): pass

  @abstractmethod
  def approve(self, budget): pass

  @abstractmethod
  def disapprove(self, budget): pass

  @abstractmethod
  def finish(self, budget): pass


class UnderReview(BudgetState):

  def apply_discount(self, budget):
    budget.add_bonus(budget.amount * 0.02)

  def approve(self, budget):
    budget.status = Approved()

  def disapprove(self, budget):
    budget.status = Disapproved()

  def finish(self, budget):
    raise Exception('UnderReview: Cannot be finished')

  def __str__(self):
    return "Em Análise"


class Approved(BudgetState):

  def apply_discount(self, budget):
    budget.add_bonus(budget.amount * 0.05)

  def approve(self, budget):
    raise Exception('Already approved')

  def disapprove(self, budget):
    raise Exception('Approved: once approved cannot be disapproved')

  def finish(self, budget):
    budget.status = Finished()

  def __str__(self):
    return "Aprovado"


class Disapproved(BudgetState):

  def apply_discount(self, budget):
    raise Exception('Disapproved: cannot apply discount')

  def approve(self, budget):
    raise Exception('Disapproved: once disapproved cannot be approved')

  def disapprove(self, budget):
    raise Exception('Already disapproved')

  def finish(self, budget):
    budget.status = Finished()

  def __str__(self):
    return "Negado"


class Finished(BudgetState):

  def apply_discount(self, budget):
    raise Exception('Finished: cannot apply discount')

  def approve(self, budget):
    raise Exception('Finished: cannot be approved')

  def disapprove(self, budget):
    raise Exception('Finished: cannot be disapproved')

  def finish(self, budget):
    raise Exception('Already finished')

  def __str__(self):
    return "Finalizado"
