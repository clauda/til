# -*- coding: UTF-8 -*-
from datetime import date
from observers import Observer, Subject
from random import randrange
from enum import Enum
from typing import List
# Builder is a creational design pattern,
# which allows constructing complex objects step by step.


class Statuses(Enum):
  waiting = 'waiting'
  deferred = 'deferred'
  rejected = 'rejected'


class Item(object):

  def __init__(self, description, price):
    self.__description = description
    self.__price = price

  @property
  def description(self):
    return self.__description

  @property
  def price(self):
    return self.__price

  def __str__(self):
    return self.description


class Company(object):

  def __init__(self, uuid, name):
    self.__number = uuid
    self.__name = name

  @property
  def document(self):
    return self.__number

  def __str__(self):
    return self.__name


class Invoice(Subject):
  """
  The Subject owns some important state and notifies observers when the state
  changes.
  """

  _state: int = Statuses.waiting
  """
  For the sake of simplicity, the Subject's state, essential to all
  subscribers, is stored in this variable.
  """

  _observers: List[Observer] = []
  """
  List of subscribers. In real life, the list of subscribers can be stored
  more comprehensively (categorized by event type, etc.).
  """

  def __init__(self, company, items, issued_at=date.today(), remarks=''):
    self.__provider = company
    self.__issued_at = issued_at
    self.__items = items
    self.__remarks = remarks

  @property
  def provider(self):
    return self.__provider

  @property
  def provider_number(self):
    return self.provider.document

  @property
  def items(self):
    return self.__items

  @property
  def issued_at(self):
    return self.__issued_at

  @property
  def remarks(self):
    return self.__remarks

  def update(self, status) -> None:
    """
    Usually, the subscription logic is only a fraction of what a Subject can
    really do. Subjects commonly hold some important business logic, that
    triggers a notification method whenever something important is about to
    happen (or after it).
    """

    self._state = Statuses(status)

    print(f"Invoice says: State has just changed to: {self._state}")
    self.notify()

  """
  Subject methods
  """

  def attach(self, observer: Observer) -> None:
    print(f"Invoice says: Attached an observer: #{observer}")
    self._observers.append(observer)

  def detach(self, observer: Observer) -> None:
    self._observers.remove(observer)

  """
  The subscription management methods.
  """

  def notify(self) -> None:
    """
    Trigger an update in each subscriber.
    """
    print("Invoice says: Notifying observers...")
    for observer in self._observers:
      observer.update(self)
