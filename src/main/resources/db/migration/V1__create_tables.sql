CREATE TABLE users(
    user_id VARCHAR UNIQUE NOT NULL,
    current_limit DECIMAL(15, 2) NOT NULL,
    reserved_amount DECIMAL(15, 2) NOT NULL DEFAULT 0.00
);

INSERT INTO users (user_id, current_limit, reserved_amount) VALUES
('1c02afe1-386c-4ffe-8e51-2b754a9aedac', 100000.00, 0.00),
('de1769ea-4a22-4598-a5c9-bf78865986a6', 100000.00, 0.00),
('c6fd444f-5a97-496d-8969-4600963711cb', 100000.00, 0.00);