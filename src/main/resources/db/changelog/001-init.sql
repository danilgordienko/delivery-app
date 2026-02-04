CREATE TABLE IF NOT EXISTS provider (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS product (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL UNIQUE,
                         price DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS delivery (
                          id BIGSERIAL PRIMARY KEY,
                          provider_id BIGINT NOT NULL,
                          delivery_date DATE NOT NULL,

                          CONSTRAINT fk_delivery_provider
                              FOREIGN KEY (provider_id)
                                  REFERENCES provider(id)
);

CREATE TABLE IF NOT EXISTS delivery_product (
                                  id BIGSERIAL PRIMARY KEY,
                                  delivery_id BIGINT NOT NULL,
                                  product_id BIGINT NOT NULL,
                                  weight DECIMAL(10,3) NOT NULL,

                                  CONSTRAINT fk_dp_delivery
                                      FOREIGN KEY (delivery_id)
                                          REFERENCES delivery(id)
                                          ON DELETE CASCADE,

                                  CONSTRAINT fk_dp_product
                                      FOREIGN KEY (product_id)
                                          REFERENCES product(id)
);
