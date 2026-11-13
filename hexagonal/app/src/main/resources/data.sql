CREATE TABLE IF NOT EXISTS medicinal_property(
    id Long PRIMARY KEY AUTOINCREMENT,
    title VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS essential_oil(
    id Long PRIMARY KEY AUTOINCREMENT,
    name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS oil_property (
    oil_id Long NOT NULL,
    property_id Long NOT NULL,
    PRIMARY KEY (oil_id, property_id),
    FOREIGN KEY (oil_id) REFERENCES essential_oil(id)
            ON DELETE CASCADE,
    FOREIGN KEY (property_id) REFERENCES medicinal_property(id)
            ON DELETE RESTRICT
);

INSERT INTO medicinal_property (id, title)
VALUES (1, 'Antiinflammatoire'),
       (2, 'Galactogène'),
       (3, 'Stimulant immunitaire'),
       (4, 'Drainant hépatique'),
       (5, 'Diurétique'),
       (6, 'Antalgique'),
       (7, 'Cicatrisant'),
       (8, 'Antispasmodique');

INSERT INTO essential_oil (id, name)
VALUES (1, 'Lavandin'),
       (2, 'Cannelle'),
       (3, 'Litsée citronnée'),
       (4, 'Cyprès'),
       (5, 'Palmarosa'),
       (6, 'Clou de girofle'),
       (7, 'Thym vulgaire'),
       (8, 'Géranium');

INSERT INTO oil_property (oil_id, property_id)
VALUES (1, 1),
       (1, 7),
       (2, 3),
       (2, 5),
       (3, 1),
       (5, 5),
       (5, 7),
       (6, 1),
       (6, 3),
       (6, 8),
       (7, 1),
       (7, 3),
       (7, 8);