INSERT INTO `app_user` (id, email, updated_at, created_at)
VALUES (12345, 'hello@world.com', NOW(), NOW());

INSERT INTO `balance` (id, balance, currency, user_id, updated_at, created_at)
VALUES (1, 1000, 'USD', 12345, NOW(), NOW());

INSERT INTO `balance` (id, balance, currency, user_id, updated_at, created_at)
VALUES (2, 10000, 'KRW', 12345, NOW(), NOW());

INSERT INTO `merchant` (id, name, updated_at, created_at)
VALUES ('merchantId123', 'merchantName123', NOW(), NOW());