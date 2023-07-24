INSERT INTO snappfood.customers (extuid, version, create_date, last_update_date, name, balance)
VALUES
    ('customer-1', 1, '2023-07-24 10:00:00', '2023-07-24 10:00:00', 'John Doe', 50000),
    ('customer-2', 1, '2023-07-24 11:00:00', '2023-07-24 11:00:00', 'Jane Smith', 75000),
    ('customer-3', 1, '2023-07-24 12:00:00', '2023-07-24 12:00:00', 'Michael Johnson', 30000);

INSERT INTO snappfood.trips (extuid, version, create_date, last_update_date, status)
VALUES
    ('trip-1', 1, '2023-07-24 13:00:00', '2023-07-24 13:00:00', 'ASSIGNED'),
    ('trip-2', 1, '2023-07-24 14:00:00', '2023-07-24 14:00:00', 'AT_VENDOR');

INSERT INTO snappfood.vendors (extuid, version, create_date, last_update_date, name)
VALUES
    ('vendor-1', 1, '2023-07-24 16:00:00', '2023-07-24 16:00:00', 'Vendor A'),
    ('vendor-2', 1, '2023-07-24 17:00:00', '2023-07-24 17:00:00', 'Vendor B');

INSERT INTO snappfood.orders (extuid, version, create_date, last_update_date, customer_id, status, total_price, trip_id, delivery_time_in_min, vendor_id, action_need_date)
VALUES
    ('order-1', 1, '2023-07-24 19:00:00', '2023-07-24 19:00:00', 1, 'pending', 1500, null, 30, 1, null),
    ('order-2', 1, '2023-07-24 20:30:00', '2023-07-24 20:30:00', 2, 'accepted', 2000, 1, 45, 2, null),
    ('order-3', 1, '2023-07-24 22:00:00', '2023-07-24 22:00:00', 1, 'transfer_to_driver', 1000, 2, 20, 1, null),
    ('order-4', 1, '2023-07-24 19:10:00', '2023-07-24 19:10:00', 1, 'pending', 1500, null, 10, 1, null);


INSERT INTO snappfood.agents (extuid, version, create_date, last_update_date, name, assigned_order_id)
VALUES
    ('agent-1', 1, '2023-07-24 09:00:00', '2023-07-24 09:00:00', 'Agent John', null),
    ('agent-2', 1, '2023-07-24 10:00:00', '2023-07-24 10:00:00', 'Agent Jane', null),
    ('agent-3', 1, '2023-07-24 11:00:00', '2023-07-24 11:00:00', 'Agent Michael', null),
    ('agent-4', 1, '2023-07-24 12:00:00', '2023-07-24 12:00:00', 'Agent Emily', null);



