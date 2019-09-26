# -*- coding: UTF-8 -*-
from models import Item, Company, Invoice
from builders import InvoiceBuilder

if __name__ == '__main__':

  items = [Item('ITEM A', 100),
           Item('ITEM B', 200)]

  provider = Company('012345678901234', 'Google')
  invoice = Invoice(company=provider,
                    items=items, 
                    remarks="No necessary builder")
  
  print(invoice.provider)
  print(invoice.provider_number)
  print(invoice.items)
  print(invoice.remarks)
  print('----')

  print('Using builders...')
  invoice2 = (InvoiceBuilder()
    .define_provider(provider)
    .set_items(items)
    .build())
  print(invoice2.provider_number)
  print(invoice2.items)
