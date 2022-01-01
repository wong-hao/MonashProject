import base64
import os

"""https://pycryptodome.readthedocs.io/en/latest/src/cipher/aes.html"""

from Crypto.Cipher import AES
from Crypto.Util import Counter
import chardet


# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

def b2str(data):
    """Convert bytes into string type."""
    try:
        return data.decode("utf-8")
    except UnicodeDecodeError:
        pass
    try:
        return data.decode("utf-8-sig")
    except UnicodeDecodeError:
        pass
    try:
        return data.decode("ascii")
    except UnicodeDecodeError:
        return data.decode("latin-1")


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.

    while (1):
        data = input()

        key = b'1234123412341234'
        cipher = AES.new(key, AES.MODE_EAX)
        nonce = cipher.nonce

        data = data.encode()
        print("nonce: ", nonce)
        ciphertext, tag = cipher.encrypt_and_digest(data)
        triple = b'nonce: ' + nonce + b'ciphertext: ' + ciphertext + b'tag' + tag
        print("Triple: ", triple)
        triple_base64 = base64.b64encode(triple) + b'\n'

        data = triple_base64
        print("Ciphertext: ", ciphertext)
        print("data/triple_base64: ", data)
        print("tag: ", tag)

        triple1 = base64.b64decode(data)
        print("triple1: ", triple1)
        nonce_loc = triple1.find(b'nonce')
        ciphertext_loc = triple1.find(b'ciphertext')
        tag_loc = triple1.find(b'tag')

        nonce1 = triple1[nonce_loc + len("nonce") + 2:ciphertext_loc]
        ciphertext1 = triple1[ciphertext_loc + len("ciphertext") + 2:tag_loc]
        tag1 = triple1[tag_loc + len("tag"):len(triple1)]

        cipher1 = AES.new(key, AES.MODE_EAX, nonce=nonce1)

        print("nonce1: ", nonce1)
        print("ciphertext1: ", ciphertext1)
        print("tag1: ", tag1)

        plaintext = cipher1.decrypt(ciphertext1)
        try:
            cipher1.verify(tag1)
            print("The message is authentic:", plaintext.decode())
        except ValueError:
            print("Key incorrect or message corrupted")


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
