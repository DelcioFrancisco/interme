CREATE TABLE ad (
    id text primary key unique not null,
    title text not null,
    description text not null,
    price real not null,
    created_at timestamp default current_timestamp
);

-- One ad can have many image rows. The image table references ad(id) without a UNIQUE constraint on ad_id.