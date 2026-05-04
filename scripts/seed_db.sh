#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
DB_PATH="$DIR/../app.db"

rm -f "$DB_PATH"
sqlite3 "$DB_PATH" <<EOF
CREATE TABLE users (id INT, username TEXT, role TEXT);
INSERT INTO users VALUES (1, 'standard_user', 'customer');
INSERT INTO users VALUES (2, 'admin_user', 'admin');

CREATE TABLE products (id INT, name TEXT, price REAL);
INSERT INTO products VALUES (1, 'Sauce Labs Backpack', 29.99);
EOF

echo "✅ Java Database Seeded."
