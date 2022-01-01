#!/ usr/bin/env python3
import gmpy2
import gmpy2 as gmp
from gmpy2 import mpz
import random
import os


def str2mpz(msg):
    '''To convert strings to mpz integers '''
    m = bytes(msg, 'ascii')
    return gmp.mpz(int.from_bytes(m, byteorder='little'))


def mpz2str(num):
    '''To convert the mpz integers back to string '''
    return str(gmp.to_binary(num)[2:], 'ascii')


def rand_n(n):
    '''return a n - bit random integer '''
    # the upper limit that fits in n bits (n bits all 1)
    upper = 2 ** n - 1
    # the lower limit with 1 in its most significant bit
    lower = 2 ** (n - 1)
    # initialising python ’s random number generator
    random.seed(a=os.urandom(512), version=2)
    rand_state = gmp.random_state(random.getrandbits(4096))
    # gmpy2 also has a random number generator , now we know how to use both
    x = gmp.mpz_random(rand_state, upper)
    if x < lower:
        x = x + lower
    return x


def rsa_keygen(N):
    '''To generate RSA key pair '''
    p = get_prime(N)
    q = get_prime(N)
    n = p * q
    phi = (p - 1) * (q - 1)
    e = gmpy2.mpz(65537)
    d = gmpy2.invert(e, phi)
    return 1, n, e, d

    pass


def get_prime(x):
    n = rand_n(x)
    if not gmpy2.is_prime(n):
        n = gmpy2.next_prime(n)
    return n


def encrypt(m, e, n):
    return gmpy2.powmod(m, e, n)


def decrypt(c, d, n):
    return gmpy2.powmod(c, d, n)


def main():
    print("---------------------------(a)---------------------------")
    status, n, e, d = rsa_keygen(2048)
    print("status:", status)
    print("n:", n)
    print("e:", e)
    print("d:", d)

    print("Please input the plaintext: ")
    plaintext = int(input())
    print("plaintext:", plaintext)
    ciphertext = encrypt(plaintext, e, n)
    print("---------------------------(b)---------------------------")
    print("ciphertext:", ciphertext)
    ciphertext_decrypted = decrypt(ciphertext, d, n)
    print("---------------------------(c)---------------------------")
    print("decryped ciphertext:", ciphertext_decrypted)


if __name__ == '__main__':
    main()
