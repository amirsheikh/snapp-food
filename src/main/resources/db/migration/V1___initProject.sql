create table snappfood.customers(
    id bigserial not null
        constraint customers_pkey
            primary key,
    extuid varchar(255) not null,
    version integer not null,
    create_date timestamp not null,
    last_update_date timestamp not null,
    name varchar(255) not null,
    balance bigint not null default 0
);

create table snappfood.vendors(
      id bigserial not null
          constraint vendors_pkey
              primary key,
      extuid varchar(255) not null,
      version integer not null,
      create_date timestamp not null,
      last_update_date timestamp not null ,
      name varchar(255)
);

create table snappfood.trips(
    id bigserial not null
        constraint trips_pkey
            primary key,
    extuid  varchar(255) not null ,
    version integer not null ,
    create_date timestamp not null ,
    last_update_date timestamp not null ,
    status varchar(255) not null
);

create table snappfood.orders(
    id bigserial not null
        constraint orders_pkey
            primary key,
    extuid varchar(255) not null,
    version integer not null,
    create_date timestamp not null,
    last_update_date timestamp  not null,
    customer_id bigint not null references snappfood.customers(id),
    status varchar(255) not null,
    total_price bigint not null,
    trip_id bigint references snappfood.trips(id),
    delivery_time_in_min int not null,
    vendor_id bigint not null references snappfood.vendors(id),
    action_need_date timestamp
);


create table snappfood.agents(
     id bigserial not null
         constraint agents_pkey
             primary key,
     extuid varchar(255) not null,
     version integer not null,
     create_date timestamp not null,
     last_update_date timestamp not null,
     name varchar(255) not null,
     assigned_order_id bigint references  snappfood.orders(id)
);

create table snappfood.order_items(
      id bigserial not null
          constraint order_items_pkey
              primary key,
      extuid varchar(255) not null ,
      version integer not null,
      create_date timestamp not null,
      last_update_date timestamp not null,
      name varchar(255) not null,
      count integer not null,
      unit_price bigint not null,
      order_id bigint not null references  snappfood.orders(id)
);


create table snappfood.delay_reports(
    id bigserial not null
        constraint delay_reports_pkey
            primary key,
    extuid varchar(255) not null ,
    version integer not null,
    create_date timestamp not null,
    last_update_date timestamp not null,
    order_id bigint not null references  snappfood.orders(id),
    delay_in_min integer not null
);