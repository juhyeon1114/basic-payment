CREATE TABLE `app_user` (
                            `user_id` bigint unsigned PRIMARY KEY AUTO_INCREMENT,
                            `email` varchar(100) UNIQUE COMMENT '유저 이메일',
                            `updated_at` timestamp DEFAULT (now()),
                            `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `balance` (
                           `id` bigint unsigned PRIMARY KEY AUTO_INCREMENT,
                           `user_id` bigint unsigned,
                           `currency` varchar(10) COMMENT 'KRW, USD',
                           `balance` float,
                           `updated_at` timestamp DEFAULT (now()),
                           `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `merchant` (
                            `id` bigint unsigned PRIMARY KEY AUTO_INCREMENT,
                            `name` varchar(255) COMMENT '결제처 이름',
                            `updated_at` timestamp DEFAULT (now()),
                            `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `payment` (
                           `id` bigint unsigned PRIMARY KEY AUTO_INCREMENT,
                           `user_id` bigint unsigned,
                           `merchant_id` bigint unsigned,
                           `payment_method` varchar(50) COMMENT 'creditCard',
                           `amount` float COMMENT '지불 금액',
                           `currency` varchar(10) COMMENT 'KRW, USD',
                           `status` varchar(20) COMMENT 'failed, pending, approved',
                           `reason` varchar(200) COMMENT '결제 실패 이유',
                           `updated_at` timestamp DEFAULT (now()),
                           `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `payment_details` (
                                   `id` bigint unsigned PRIMARY KEY AUTO_INCREMENT,
                                   `payment_id` bigint unsigned,
                                   `card_number` varchar(100) COMMENT '카드 번호',
                                   `expire_year` integer,
                                   `expire_month` integer,
                                   `cvc` varchar(10) COMMENT 'CVC 번호',
                                   `updated_at` timestamp DEFAULT (now()),
                                   `created_at` timestamp DEFAULT (now())
);

CREATE INDEX `currency_index` ON `balance` (`currency`);

CREATE INDEX `payment_method_index` ON `payment` (`payment_method`);

CREATE INDEX `currency_index` ON `payment` (`currency`);

CREATE INDEX `card_number_index` ON `payment_details` (`card_number`);

ALTER TABLE `balance` ADD FOREIGN KEY (`user_id`) REFERENCES `app_user` (`user_id`);

ALTER TABLE `payment` ADD FOREIGN KEY (`user_id`) REFERENCES `app_user` (`user_id`);

ALTER TABLE `payment` ADD FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`);

ALTER TABLE `payment_details` ADD FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`);
