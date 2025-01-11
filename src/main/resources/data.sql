-- Insert categories
INSERT INTO categories (id, name) VALUES
                                    (1, 'Sneakers'),
                                    (2, 'Boots'),
                                    (3, 'Sandals');

-- Insert shoes
INSERT INTO shoes (id, name, brand, price, stock_quantity, category_id) VALUES
                                                                           (1, 'Air Max 90', 'Nike', 120.00, 50, 1),
                                                                           (2, 'Classic Boot', 'Timberland', 180.00, 30, 2),
                                                                           (3, 'Beach Sandal', 'Reef', 45.00, 100, 3),
                                                                           (4, 'UltraBoost', 'Adidas', 160.00, 40, 1);

INSERT INTO users (email, password, first_name, last_name) VALUES
                                                                       ('victormitev23@gmail.com','alomaikatasie','Victor','Mitev'),
                                                                       ('atanascho@gmail.com','zdrasti','Atanas','Yurukov');