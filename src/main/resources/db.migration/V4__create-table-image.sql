CREATE TABLE image (
    id text primary key unique not null,
    ad_id text not null,
    url text not null,
    created_at timestamp default current_timestamp,
    FOREIGN KEY (ad_id) REFERENCES ad(id)
);

-- This allows multiple images for the same ad because ad_id is not unique and each image row is a separate record.