CREATE TABLE spaceship (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           series VARCHAR(255) NOT NULL
);

INSERT INTO spaceship (name, series) VALUES ('X-Wing', 'Star Wars');
INSERT INTO spaceship (name, series) VALUES ('Millennium Falcon', 'Star Wars');
INSERT INTO spaceship (name, series) VALUES ('USS Enterprise', 'Star Trek');
