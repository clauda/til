# -*- coding: UTF-8 -*-
from abc import ABC, abstractmethod
# https://refactoring.guru/design-patterns/observer/python/example


class Observer(ABC):
  """
  The Observer interface declares the update method, used by subjects.
  """

  @abstractmethod
  def update(self, subject: 'Subject') -> None:
    """
    Receive update from subject.
    """
    pass


class Subject(ABC):
  """
  The Subject interface declares a set of methods for managing subscribers.
  """

  @abstractmethod
  def attach(self, observer: Observer) -> None:
    """
    Attach an observer to the subject.
    """
    pass

  @abstractmethod
  def detach(self, observer: Observer) -> None:
    """
    Detach an observer from the subject.
    """
    pass

  @abstractmethod
  def notify(self) -> None:
    """
    Notify all observers about an event.
    """
    pass


"""
Concrete Observers react to the updates issued by the Subject they had been
attached to.
"""


class EmailSender(Observer):

  def update(self, subject: Subject) -> None:
    print("EmailSender: Sending email notification...")


class Imprint(Observer):

  def update(self, subject: Subject) -> None:
    print("Imprint: Sending to printer...")
