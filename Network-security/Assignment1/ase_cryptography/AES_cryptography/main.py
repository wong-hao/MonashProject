# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
import base64
import os
import chardet

from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes

def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    data = input()
    print_hi('PyCharm')
    key = os.urandom(32)
    iv = os.urandom(16)
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv))
    encryptor = cipher.encryptor()
    print("leng of data: ", len(data.encode()))
    ct = encryptor.update(data.encode()) + encryptor.finalize()
    ct_base64 = base64.b64encode(ct)

    decryptor = cipher.decryptor()
    ct_base64_debase64 = base64.b64decode(ct_base64)
    print((decryptor.update(ct_base64_debase64) + decryptor.finalize()).decode())
# See PyCharm help at https://www.jetbrains.com/help/pycharm/
