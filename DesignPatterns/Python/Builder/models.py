# -*- coding: UTF-8 -*-
from datetime import date
# Builder is a creational design pattern,
# which allows constructing complex objects step by step.


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


class Invoice(object):

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
