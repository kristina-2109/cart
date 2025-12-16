db = db.getSiblingDB("shopping_cart_db");

db.createCollection("items");

db.items.insertMany([
  {
    itemId: 1,
    name: "iPhone 15",
    type: "DEVICE",
    offers: [
        { offerId: 101, offerName: "iPhone 15 - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 999.99 }}},
        { offerId: 102, offerName: "iPhone 15 - 24-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 49.99 }, recurrence: { count: 20, unit: "MONTHS" }}}
    ]
  },
  {
    itemId: 2,
    name: "Dell XPS 13",
    type: "DEVICE",
    offers: [
      { offerId: 103, offerName: "Dell XPS 13 - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 1199.99 }}},
      { offerId: 104, offerName: "Dell XPS 13 - 12-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 99.99 }, recurrence: { count: 12, unit: "MONTHS" }}}
    ]
  },
  {
    itemId: 3,
    name: "Samsung Galaxy S23",
    type: "DEVICE",
    offers: [
      { offerId: 105, offerName: "Samsung Galaxy S23 - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 899.99 } } },
      { offerId: 106, offerName: "Samsung Galaxy S23 - 24-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 44.99 }, recurrence: { count: 24, unit: "MONTHS" } } }
    ]
  },
  {
    itemId: 4,
    name: "Google Pixel 8",
    type: "DEVICE",
    offers: [
      { offerId: 107, offerName: "Google Pixel 8 - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 799.99 } } },
      { offerId: 108, offerName: "Google Pixel 8 - 24-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 39.99 }, recurrence: { count: 24, unit: "MONTHS" } } }
    ]
  },
  {
    itemId: 5,
    name: "OnePlus 11",
    type: "DEVICE",
    offers: [
      { offerId: 109, offerName: "OnePlus 11 - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 699.99 } } },
      { offerId: 110, offerName: "OnePlus 11 - 24-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 34.99 }, recurrence: { count: 24, unit: "MONTHS" } } }
    ]
  },
  {
    itemId: 6,
    name: "MacBook Air M2",
    type: "DEVICE",
    offers: [
      { offerId: 111, offerName: "MacBook Air M2 - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 1249.99 } } },
      { offerId: 112, offerName: "MacBook Air M2 - 12-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 69.99 }, recurrence: { count: 12, unit: "MONTHS" } } }
    ]
  },
  {
    itemId: 7,
    name: "MacBook Pro 14",
    type: "DEVICE",
    offers: [
      { offerId: 113, offerName: "MacBook Pro 14 - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 1999.99 } } },
      { offerId: 114, offerName: "MacBook Pro 14 - 12-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 109.99 }, recurrence: { count: 12, unit: "MONTHS" } } }
    ]
  },
  {
    itemId: 8,
    name: "HP Spectre x360",
    type: "DEVICE",
    offers: [
      { offerId: 115, offerName: "HP Spectre x360 - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 1399.99 } } },
      { offerId: 116, offerName: "HP Spectre x360 - 12-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 79.99 }, recurrence: { count: 12, unit: "MONTHS" } } }
    ]
  },
  {
    itemId: 9,
    name: "Asus ROG Zephyrus",
    type: "DEVICE",
    offers: [
      { offerId: 117, offerName: "Asus ROG Zephyrus - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 1599.99 } } },
      { offerId: 118, offerName: "Asus ROG Zephyrus - 12-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 89.99 }, recurrence: { count: 12, unit: "MONTHS" } } }
    ]
  },
  {
    itemId: 10,
    name: "Lenovo ThinkPad X1",
    type: "DEVICE",
    offers: [
      { offerId: 119, offerName: "Lenovo ThinkPad X1 - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 1499.99 } } },
      { offerId: 120, offerName: "Lenovo ThinkPad X1 - 12-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 84.99 }, recurrence: { count: 12, unit: "MONTHS" } } }
    ]
  },
  {
    itemId: 11,
    name: "Sony Xperia 1 IV",
    type: "DEVICE",
    offers: [
      { offerId: 121, offerName: "Sony Xperia 1 IV - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 949.99 } } },
      { offerId: 122, offerName: "Sony Xperia 1 IV - 24-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 46.99 }, recurrence: { count: 24, unit: "MONTHS" } } }
    ]
  },
  {
    itemId: 12,
    name: "Xiaomi Mi 13",
    type: "DEVICE",
    offers: [
      { offerId: 123, offerName: "Xiaomi Mi 13 - One-time purchase", price: { type: "ONE_TIME", amount: { currency: "EUR", value: 799.99 } } },
      { offerId: 124, offerName: "Xiaomi Mi 13 - 24-month subscription", price: { type: "RECURRING", amount: { currency: "EUR", value: 36.99 }, recurrence: { count: 24, unit: "MONTHS" } } }
    ]
  },
  { itemId: 101, name: "Home Internet 500Mbps", type: "SUBSCRIPTION",
    offers: [
      { offerId: 10101, offerName: "Home Internet 500Mbps - 12 months", price: { type: "RECURRING", amount: { currency: "EUR", value: 29.99 }, recurrence: { count: 12, unit: "MONTHS" }}},
      { offerId: 10102, offerName: "Home Internet 500Mbps - 24 months", price: { type: "RECURRING", amount: { currency: "EUR", value: 24.99 }, recurrence: { count: 24, unit: "MONTHS" }}}
    ]
  },
  {
    itemId: 102,
    name: "Fiber Internet 1Gbps",
    type: "SUBSCRIPTION",
    offers: [
      { offerId: 10201, offerName: "Fiber Internet 1Gbps - 12 months", price: { type: "RECURRING", amount: { currency: "EUR", value: 39.99 }, recurrence: { count: 12, unit: "MONTHS" }}},
      { offerId: 10202, offerName: "Fiber Internet 1Gbps - 2 years", price: { type: "RECURRING", amount: { currency: "EUR", value: 34.99 }, recurrence: { count: 24, unit: "MONTHS" }}},
      { offerId: 10203, offerName: "Fiber Internet 1Gbps - 1 month", price: { type: "RECURRING", amount: { currency: "EUR", value: 44.99 }, recurrence: { count: 1, unit: "MONTHS" }}}
    ]
  }
]);

db.createCollection("carts");

db.carts.insertMany([
    {
      customerId: "d2418407-db91-4b1e-b6c8-d04c693ae6d8",
      createdAt: new Date(),
      updatedAt: new Date(),
      items: [
        {
          itemId: 1,
          offerId: 102,
          action: "ADD",
          quantity: 2
        },
        {
          itemId: 2,
          offerId: 103,
          action: "ADD",
          quantity: 1
        },
        {
          itemId: 102,
          oldOfferId: 10203,
          offerId: 10201,
          action: "MODIFY",
          quantity: 1
        }
      ]
    }
]);

db.createCollection("statistics");

db.statistics.insertMany([
  {
    offerId: 103,
    itemId: 1,
    action: "ADD",
    quantity: 5,
    time: ISODate("2025-12-16T12:07:19.734Z")
  },
  {
    offerId: 102,
    itemId: 1,
    action: "ADD",
    quantity: 1,
    time: ISODate("2025-12-16T10:30:12.322Z")
  },
  {
    offerId: 105,
    itemId: 3,
    action: "ADD",
    quantity: 1,
    time: ISODate("2025-12-16T09:23:05.878Z")
  },
  {
    offerId: 10201,
    itemId: 102,
    action: "REMOVE",
    quantity: 1,
    time: ISODate("2025-12-16T16:22:18.112Z")
  }
]);

