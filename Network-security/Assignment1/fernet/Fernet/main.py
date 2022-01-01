# This is a sample Python script.
import base64
import os

import chardet
from cryptography.fernet import Fernet
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC


def print_hi(name):
    password = b"password"
    salt = os.urandom(16)

    kdf = PBKDF2HMAC(
         algorithm=hashes.SHA256(),
         length=32,
         salt=salt,
         iterations=100000,
     )
    key = base64.urlsafe_b64encode(kdf.derive(password))
    f = Fernet(key)
    token = f.encrypt(b"Secret message!")

    triple = b'password: ' + password + b'salt: ' + salt + b'token: ' + token
    triple_base64  = base64.b64encode(triple)



    print(chardet.detect(token))
    print(token)

    print(f.decrypt(token))

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
