CREATE SEQUENCE "PUBLIC"."CLIENT_SEQ" START WITH 1 RESTART WITH 51 INCREMENT BY 50;

CREATE CACHED TABLE "PUBLIC"."CLIENT"(
                                         "ID" BIGINT NOT NULL,
                                         "UID" UUID,
                                         "ADDRESS" CHARACTER VARYING(255),
                                         "AGE" INTEGER,
                                         "GENDER" TINYINT,
                                         "NAME" CHARACTER VARYING(255),
                                         "PERSONAL_ID" CHARACTER VARYING(255),
                                         "PHONE" CHARACTER VARYING(255),
                                         "PASSWORD" CHARACTER VARYING(255),
                                         "STATE" BOOLEAN
);
ALTER TABLE "PUBLIC"."CLIENT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_76" PRIMARY KEY("ID");
ALTER TABLE "PUBLIC"."CLIENT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" CHECK("GENDER" BETWEEN 0 AND 2) NOCHECK;
ALTER TABLE "PUBLIC"."CLIENT" ADD CONSTRAINT "PUBLIC"."UK_QBMHIJ8INFWG32B2WYOEOWU27" UNIQUE("UID");
