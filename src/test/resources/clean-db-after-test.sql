DELETE FROM users WHERE id != 1;
INSERT IGNORE INTO users (id, email, last_name, name, password, username) VALUES (2, 'alberto.caro@sperantus.com', 'Caro', 'Alberto', '$2a$10$.bCuK6EgezwYbKpmAnHPauGAQzYWbbhKMHgxrJtngwywVFe6zlzHa', 'alberto');

ALTER TABLE users AUTO_INCREMENT = 3;