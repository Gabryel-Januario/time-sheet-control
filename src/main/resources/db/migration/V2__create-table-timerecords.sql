CREATE TABLE timerecord (
    id TEXT PRIMARY KEY NOT NULL UNIQUE,
    user_id TEXT NOT NULL,
    check_in DATE,
    check_out DATE,
    hours_worked DECIMAL(5,2),
    FOREIGN KEY (user_id) REFERENCES users(id)
);