##Create an account:

http://localhost:9000/account-service/account  - POST


{
"accountFirstName": "test2",
"accountLastName": "account",
"dateOfBirth": "10/10/1985",
"accountType": "CONSUMER",
"mobileNumber": 9999999999,
"address": "1234 test addr",
"accountBalance": 1000.00
}

{
"accountFirstName": "test2",
"accountLastName": "account",
"dateOfBirth": "10/10/1985",
"accountType": "CONSUMER",
"mobileNumber": 9999999999,
"address": "1234 test addr",
"accountBalance": 1000.00
}

{
"accountFirstName": "test3",
"accountLastName": "account",
"dateOfBirth": "10/10/1985",
"accountType": "CONSUMER",
"mobileNumber": 9999999999,
"address": "1234 test addr",
"accountBalance": 1000.00
}

##Get account details:

http://localhost:9000/account-service/account?accountNumber=10000 - GET
http://localhost:9000/account-service/account?accountNumber=10001 - GET

##Get all acounts:

http://localhost:9000/account-service/accounts  - GET


##Update an account:

http://localhost:9000/account-service/account - PUT
{
"accountNumber": 10002,
"address": "1234 test addr1"
}


##Delete and account:

http://localhost:9000/account-service/account?accountNumber=10002  -- DELETE



##Make a deposit to an account:

http://localhost:9000/deposit-service/deposit  - POST

{
    "accountNumber": 10000,
    "depositAmount": 5000.00,
    "depositType": "CASH"
}

{
    "accountNumber": 10001,
    "depositAmount": 5000.00,
    "depositType": "CHEQUE"
 }

##Withdraw amount from an account:

http://localhost:9000/withdraw-service/withdraw  - POST

{
    "accountNumber": 10000,
    "withdrawAmount": 1000,
    "withdrawType": "ATM"
}

{
    "accountNumber": 10001,
    "withdrawAmount": 1000,
    "withdrawType": "CASH"
}

##Transfer between two accounts:

http://localhost:9000/transfer-service/transfer - POST

{
    "accountNumber": 10001,
    "depositAmount": 2500,
    "depositType": "TRANSFER",
    "sourceAccountNumber": 10000
}


##Get data for all accounts:

http://localhost:9000/account-service/accounts - GET


##Get files from s3:

http://localhost:9000/static/test.txt - GET



##Spring Boot Admin:

http://localhost:6060

##Zipkin:
http://localhost:9411

##Grafana:

http://localhost:3000

##Kibana:

http://localhost:5601

##S3 Minio server:

http://localhost:4000

##Keycloak Auth server:

http://localhost:8080



