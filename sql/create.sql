CREATE TABLE gift_certificates
(
    certificate_id   INT GENERATED ALWAYS AS IDENTITY,
    "name"           varchar(120) NOT NULL,
    description      varchar(120) NOT NULL,
    price            INTEGER          NOT NULL,
    duration         INTEGER          NOT NULL,
    create_date      DATE        NOT NULL,
    last_update_date DATE       NOT NULL,
    PRIMARY KEY (certificate_id)
);


CREATE TABLE tags
(
    tag_id INT GENERATED ALWAYS AS IDENTITY,
    "name" varchar(120) NOT NULL,
    PRIMARY KEY (tag_id)

);

CREATE TABLE gift_certificates_tags
(


            certificate_id  INTEGER
            CONSTRAINT gift_certificates_tags_gift_certificates_id_fk
            REFERENCES gift_certificates,
            tag_id INTEGER
        CONSTRAINT gift_certificates_tags_tags_id_fk
            REFERENCES tags
);

CREATE TABLE users
(
    user_id INT GENERATED ALWAYS AS IDENTITY,
    "name" varchar(120) NOT NULL,
    PRIMARY KEY (user_id)

);



