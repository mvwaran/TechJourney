# Digital Signing

Digital signing ensures the authenticity, integrity of digital data, such as documents, messages, software, and transactions. It involves the use of cryptographic keys (private and public keys) to create a digital signature that uniquely identifies the signer and verifies the integrity of the signed data.

## Steps

### Step 1: Generate a private key using openssl

```console
openssl genrsa -out test_private_key.pem 2048
```

Output of the private key contains Base64 encoded data of RSA components like modulus and exponents in PEM file format. <sup>[(What is PEM file format?)](../FAQ.md#what-is-pem-file-format)</sup>

### Step 2: Generate a public key using openssl

```console
openssl rsa -in test_private_key.pem -pubout -out test_public_key.pem
```

This command too generates key in PEM file format. Share this file to your recepients.

### Step 3: Start sign-in using private key

#### Step 3.1: Create SHA256 hash of your data using openssl

To sign in first you have to create HASH of your data, this hash will be signed using private key. HASH will be unique for every data.

```console
openssl dgst -sha256 -binary test.txt > test_hash.txt
```

#### Step 3.2: Sign in HASH using private key using openssl

```console
openssl pkeyutl -sign -inkey test_private_key.pem -in test_hash.txt -out test_signature.bin
```

Send the digitally signed signature file (test_signature.bin) along with your original data file (test.txt) and HASH file (test_hash.txt).

### Step 4: Testing

As a recepient of the above file, to verify if the data is really signed by the expected sender, use the public key to get the HASH value and compare the HASH with the expected hash.

```console
openssl pkeyutl -verify -pubin -inkey test_public_key.pem -sigfile test_signature.bin -in test_hash.txt
```

If the hash value matched with test_hash.txt file, then you get *Signature Verified Successfully* message.
