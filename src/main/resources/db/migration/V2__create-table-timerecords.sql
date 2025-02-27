CREATE TABLE timerecords (
    id TEXT PRIMARY KEY NOT NULL UNIQUE,
    user_id TEXT NOT NULL,
    check_in TIMESTAMP DEFAULT NULL,
    check_out TIMESTAMP DEFAULT NULL, 
    hours_worked DECIMAL(5,2) DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(id)
);