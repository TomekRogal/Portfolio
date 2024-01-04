
ALTER TABLE institution CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
INSERT INTO institution(name, description) VALUES('Dbam o Zdrowie', 'Pomoc dzieciom z ubogich rodzin.');
INSERT INTO institution(name, description) VALUES('Dla dzieci', 'Pomoc osobom znajdującym się w trudnej sytuacji życiowej.');
INSERT INTO institution(name, description) VALUES('A kogo', 'Pomoc wybudzaniu dzieci ze śpiączki.');
INSERT INTO institution(name, description) VALUES('Bez domu', 'Pomoc dla osób nie posiadających miejsca zamieszkania');


ALTER TABLE category CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
INSERT INTO category(name) VALUES('zabawki');
INSERT INTO category(name) VALUES('ubrania');
INSERT INTO category(name) VALUES('jedzenie');

INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');
