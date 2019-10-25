-- -----------------------------------------------------
-- Table message
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS message (
    IDT_MESSAGE                 SERIAL       NOT NULL,
    DES_DETAIL                  VARCHAR(150) NOT NULL,
    PRIMARY KEY (IDT_MESSAGE)
);