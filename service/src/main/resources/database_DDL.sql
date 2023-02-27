create table public.categories
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(32)
);

create table public.change_types
(
    change_type BIGSERIAL PRIMARY KEY,
    description VARCHAR(64) NOT NULL
);

create table public.brands
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(32),
    category INTEGER,
    foreign key (category) references public.categories (id)
        match simple on update no action on delete no action
);

create table public.users
(
    id           BIGSERIAL PRIMARY KEY,
    full_name    VARCHAR(32) NOT NULL,
    phone_number VARCHAR(32) NOT NULL,
    password     VARCHAR(16) NOT NULL,
    address      VARCHAR(64),
    role         VARCHAR(16) NOT NULL,
    username     VARCHAR(32)
);

create table public.guitars
(
    id               BIGSERIAL PRIMARY KEY,
    model            VARCHAR(32)      NOT NULL,
    description      TEXT             NOT NULL,
    year             INTEGER          NOT NULL,
    country          VARCHAR(32)      NOT NULL,
    pick_ups         VARCHAR(32),
    fingerboard_wood VARCHAR(32),
    media_name       VARCHAR(1024),
    timestamp        timestamp without time zone,
    user_id          INTEGER          NOT NULL,
    price            DOUBLE PRECISION NOT NULL,
    brand            INTEGER          NOT NULL,
    is_closed        BOOLEAN default false,
    change_type      INTEGER          NOT NULL,
    change_value     DOUBLE PRECISION,
    change_wish      VARCHAR(32),
    foreign key (change_type) references public.change_types (change_type)
        match simple on update no action on delete no action,
    foreign key (brand) references public.brands (id)
        match simple on update no action on delete no action,
    foreign key (user_id) references public.users (id)
        match simple on update no action on delete no action
);
create table public.offers
(
    id           BIGSERIAL PRIMARY KEY,
    buyer        INTEGER,
    seller       INTEGER,
    change_type  INTEGER,
    change_value DOUBLE PRECISION,
    timestamp    timestamp without time zone,
    accepted     BOOLEAN default false,
    foreign key (buyer) references public.users (id)
        match simple on update no action on delete no action,
    foreign key (change_type) references public.change_types (change_type)
        match simple on update no action on delete no action
);

create table public.offers_products
(
    offer_id         INTEGER NOT NULL,
    product_id       INTEGER NOT NULL,
    owner            INTEGER,
    product_category INTEGER NOT NULL,
    foreign key (owner) references public.users (id),
    foreign key (offer_id) references public.offers (id)
        match simple on update no action on delete no action
);

create table public.brands
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(32),
    category INTEGER,
    foreign key (category) references public.categories (id)
        match simple on update no action on delete no action
);

create table public.pedals
(
    id           BIGSERIAL PRIMARY KEY,
    model        VARCHAR(32)                 NOT NULL,
    description  text                        NOT NULL,
    media_name   VARCHAR(1024),
    timestamp    timestamp without time zone NOT NULL,
    user_id      INTEGER                     NOT NULL,
    price        DOUBLE PRECISION            NOT NULL,
    brand        INTEGER                     NOT NULL,
    is_closed    BOOLEAN default false,
    change_type  INTEGER                     NOT NULL,
    change_value DOUBLE PRECISION,
    change_wish  VARCHAR(32),
    foreign key (change_type) references public.change_types (change_type)
        match simple on update no action on delete no action,
    foreign key (brand) references public.brands (id)
        match simple on update no action on delete no action,
    foreign key (user_id) references public.users (id)
        match simple on update no action on delete no action
);


create table public.users_liked_products
(
    user_id  INTEGER NOT NULL,
    product  INTEGER NOT NULL,
    category INTEGER NOT NULL,
    foreign key (category) references public.categories (id)
        match simple on update no action on delete no action,
    foreign key (user_id) references public.users (id)
        match simple on update no action on delete no action
);




