# Encryption and Decryption of data using RSA keys

RSA stand for (Rivest-Shamir-Adleman) is a *Asymmetric Encryption Algorithm*, which use a pair of keys for encryption and decryption: a public key and a private key. The public key is used to encrypt the data, and the private key is used to decrypt it.

## Steps

### Step 1: Generate a private key using openssl

```console
openssl genrsa -out test_private_key.pem 2048
```

Output of the private key contains Base64 encoded data of RSA components like modulus and exponents in PEM file format. <sup>[(What is PEM file format?)](../../Q&A.md#what-is-pem-file-format)</sup>

### Step 2: Generate a public key using openssl

```console
openssl rsa -in test_private_key.pem -pubout -out test_public_key.pem
```

This command too generates key in PEM file format.

### Step 3: Encrypt data using the public key

#### Encrypting using openssl console

```console
openssl pkeyutl -encrypt -inkey test_public_key.pem -pubin -in test.txt -out test_encrypted_message.bin
```

### Step 4: Decrypt data using the private key

#### Decrypting using openssl console

```console
openssl pkeyutl -decrypt -inkey test_private_key.pem -in test_encrypted_message.bin -out test_decrypted_message.txt
```