ALTER TABLE users
    ADD COLUMN email text null,
    ADD COLUMN address text null,
    ADD COLUMN email text null,
    ADD COLUMN phone text null,
    ADD COLUMN social-media text null,
    ADD COLUMN created_at timestamp default current_timestamp