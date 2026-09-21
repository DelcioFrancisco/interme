-- V6: Ensure users.role is stored as VARCHAR so enum strings like 'ADMIN' are preserved
ALTER TABLE users ALTER COLUMN role SET DATA TYPE VARCHAR(50);
