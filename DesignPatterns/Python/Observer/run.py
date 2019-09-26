# -*- coding: UTF-8 -*-
from models import Statuses, Item, Company, Invoice
from observers import EmailSender, Imprint
# Observer is a behavioral design pattern that
# lets you define a subscription mechanism to
# notify multiple objects about any events that
# happen to the object they’re observing.


if __name__ == "__main__":
  items = [Item('ITEM A', 100),
           Item('ITEM B', 200)]

  provider = Company('012345678901234', 'Guido')
  subject = Invoice(company=provider, items=items)

  notification = EmailSender()
  subject.attach(notification)

  printer = Imprint()
  subject.attach(printer)

  subject.update('deferred')

  subject.detach(printer)

  subject.update('rejected')
