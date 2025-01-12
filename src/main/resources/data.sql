INSERT INTO categories (id, name) VALUES
                                      (1, 'Sneakers'),
                                      (2, 'Boots'),
                                      (3, 'Sandals');

INSERT INTO shoes (id, name, brand, price, stock_quantity, category_id) VALUES
                                                                            (1, 'Air Max 90', 'Nike', 120.00, 50, 1),
                                                                            (2, 'Classic Boot', 'Timberland', 180.00, 30, 2),
                                                                            (3, 'Beach Sandal', 'Reef', 45.00, 100, 3),
                                                                            (4, 'UltraBoost', 'Adidas', 160.00, 40, 1);

INSERT INTO users (email, password, first_name, last_name) VALUES
                                                               ('victormitev23@gmail.com','parola123','Victor','Mitev'),
                                                               ('atanascho@gmail.com','zdrasti','Atanas','Yurukov');

INSERT INTO orders (id, user_id, order_date, total_price) VALUES
                                                              (1, 1, '2025-01-10 10:00:00', 390.00),
                                                              (2, 2, '2025-01-11 12:00:00', 200.00);