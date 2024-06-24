# Cryptography Frequently Asked Questions

## What is PEM file format?
<a id="what-is-pem-file-format"></a>
PEM stands for Privacy-Enhanced Mail, widely used for storing cryptographic data like private keys, certificates etc. These files have header (-----BEGIN PRIVATE KEY-----) and footer (-----END PRIVATE KEY-----) along with base64 encoded data. Below is an example PEM file.

```console
-----BEGIN PRIVATE KEY-----
MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDMRghA
-----END PRIVATE KEY-----
```