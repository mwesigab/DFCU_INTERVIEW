INSERT INTO LOANS (LOAN_NO, CUSTOMER_ACCT_NO, DISBURSEMENT_DATE, OUTSTANDING_AMOUNT, STATUS)
VALUES ( 'LN4203200','4203207511',now(),2000000,'ACTIVE' ),
       ( 'LN4203201','4203207516',now(),3000000,'ACTIVE' ),
       ( 'LN4203202','4203207515',now(),1000000,'ACTIVE' ),
       ( 'LN4203203','4203207511',now(),5000000,'ACTIVE' ),
       ( 'LN4203204','4203207517',now(),500000,'ACTIVE' );

INSERT INTO CUSTOMERS (CUSTOMER_ACCT_NO, FIRST_NAME, LAST_NAME, STATUS)
VALUES ('4203207510', 'Benjamin', 'Mwesiga', 'ACTIVE'),
       ('4203207511', 'John', 'Doe', 'ACTIVE'),
       ('4203207512', 'Jane', 'Doe', 'ACTIVE'),
       ('4203207513', 'Gabriel', 'Ntwari', 'ACTIVE'),
       ('4203207514', 'Vivian', 'Bulage', 'ACTIVE'),
       ('4203207515', 'James', 'Maddison', 'ACTIVE'),
       ('4203207516', 'Ruby', 'Lovelace', 'ACTIVE'),
       ('4203207517', 'Charles', 'Babbage', 'ACTIVE'),
       ('4203207518', 'Elon', 'Musk', 'ACTIVE'),
       ('4203207519', 'Nikola', 'Tesla', 'ACTIVE');

INSERT INTO USERS (USERNAME,PASSWORD,TOKEN,STATUS) VALUES ( 'mwesiga@admin.com','admin','Token','ACTIVE' );