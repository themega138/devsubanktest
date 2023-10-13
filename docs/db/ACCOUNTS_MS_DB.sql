CREATE SEQUENCE "PUBLIC"."ACCOUNT_SEQ" START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE "PUBLIC"."MOVEMENT_SEQ" START WITH 1 INCREMENT BY 50;
CREATE CACHED TABLE "PUBLIC"."ACCOUNT"(
                                          "ID" BIGINT NOT NULL,
                                          "UID" UUID,
                                          "BALANCE" NUMERIC(38, 2),
                                          "CLIENT_ID" BIGINT NOT NULL,
                                          "CLIENT_UID" CHARACTER VARYING(255) NOT NULL,
                                          "NUMBER" CHARACTER VARYING(255) NOT NULL,
                                          "STATE" BOOLEAN,
                                          "TYPE" TINYINT
);
ALTER TABLE "PUBLIC"."ACCOUNT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E4" PRIMARY KEY("ID");
CREATE CACHED TABLE "PUBLIC"."MOVEMENT"(
                                           "ID" BIGINT NOT NULL,
                                           "UID" UUID,
                                           "AMOUNT" NUMERIC(38, 2),
                                           "CURRENT_BALANCE" NUMERIC(38, 2),
                                           "DATE" TIMESTAMP(6),
                                           "INITIAL_BALANCE" NUMERIC(38, 2),
                                           "STATE" BOOLEAN,
                                           "TYPE" TINYINT,
                                           "ACCOUNT_ID" BIGINT
);
ALTER TABLE "PUBLIC"."MOVEMENT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_28" PRIMARY KEY("ID");
ALTER TABLE "PUBLIC"."MOVEMENT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" CHECK("TYPE" BETWEEN 0 AND 1) NOCHECK;
ALTER TABLE "PUBLIC"."ACCOUNT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E" CHECK("TYPE" BETWEEN 0 AND 1) NOCHECK;
ALTER TABLE "PUBLIC"."MOVEMENT" ADD CONSTRAINT "PUBLIC"."UK_T4ESVQBHJYBYEUM1JM1Y66AIW" UNIQUE("UID");
ALTER TABLE "PUBLIC"."ACCOUNT" ADD CONSTRAINT "PUBLIC"."UK_G0R31XIWJPN6BES07NSURPGH9" UNIQUE("UID");
ALTER TABLE "PUBLIC"."ACCOUNT" ADD CONSTRAINT "PUBLIC"."UK_DBFIUBQAHB32NS85K023GR6NN" UNIQUE("NUMBER");
ALTER TABLE "PUBLIC"."MOVEMENT" ADD CONSTRAINT "PUBLIC"."FKOEMEANANV9W9QNBCOCCBL70A0" FOREIGN KEY("ACCOUNT_ID") REFERENCES "PUBLIC"."ACCOUNT"("ID") NOCHECK;

