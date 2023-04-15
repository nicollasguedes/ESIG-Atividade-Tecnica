CREATE TABLE roles
(
    id   char(36) NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE tokens
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    token           VARCHAR(255) NULL,
    expiration_date datetime NULL,
    email           VARCHAR(255) NULL,
    CONSTRAINT pk_tokens PRIMARY KEY (id)
);

CREATE TABLE user
(
    id            char(36)         NOT NULL,
    login         VARCHAR(255)     NOT NULL,
    password      VARCHAR(255)     NOT NULL,
    name          VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    cellphone     VARCHAR(255) NULL,
    active        BIT(1) DEFAULT 0 NOT NULL,
    created_at    datetime         NOT NULL,
    updated_at    datetime NULL,
    last_login_at datetime NULL,
    role_id       char(36)         NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_login UNIQUE (login);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);