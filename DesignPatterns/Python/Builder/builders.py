# -*- coding: UTF-8 -*-
from models import Invoice


class InvoiceBuilder(object):

  def __init__(self):
    self.__provider = None
    self.__issued_at = None
    self.__items = None
    self.__remarks = None

  def define_provider(self, provider):
    self.__provider = provider
    return self

  def issued_date(self, issued_at):
    self.__issued_at = issued_at
    return self

  def set_items(self, items):
    self.__items = items
    return self

  def remarks(self, remarks):
    self.__remarks = remarks
    return self

  def build(self):
    if self.__provider is None:
      raise Exception('Provider is required')
    if self.__items is None:
      raise Exception('No items')

    return Invoice(company=self.__provider, 
                  issued_at=self.__issued_at,
                  items=self.__items, 
                  remarks=self.__remarks)     
