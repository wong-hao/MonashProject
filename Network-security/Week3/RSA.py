import binascii

import gmpy2 as gmp
from gmpy2 import mpz
import random
import os
def str2mpz (msg ) :
    m = bytes (msg , 'ascii')
    return gmp. mpz( int. from_bytes (m, byteorder ='little') )
def mpz2str (num ) :
    return str( gmp. to_binary ( num) [2:] , 'ascii')
def gen_prime(n):
    p = rand_n(n)

    while not gmp.is_prime(p):
        p = gmp.next_prime(p)
    return p

def rand_n (n) :

# the upper limit that fits in n bits (n bits all 1)
    upper = 2**n - 1
# the lower limit with 1 in its most significant bit
    lower = 2**( n - 1)
# initialising python ’s random number generator
    random . seed (a = os. urandom (512) , version = 2)
    rand_state = gmp. random_state ( random . getrandbits (4096) )
# gmpy2 also has a random number generator , now we know how to use both
    x = gmp. mpz_random ( rand_state , upper )
    if x < lower :
        x = x + lower
    return x
def rsa_keygen (N) :
    p = gen_prime(N//2)
    q = gen_prime(N//2)
    e = 65537 # a common choice
    n=p*q
    phi = (p - 1) * (q - 1)
    if (gmp.gcd(e, phi)!=1):
        return False, -1,-1,-1
    d = gmp.invert(e, phi)
    return True,n,e,d

def encrypt(e, n, message):
    M=str2mpz(message)
    C = gmp.powmod(M, e, n)
    return C
def decrypt(d, n, C):
    M = gmp.powmod(C, d, n)
    message=mpz2str(M)
    return message
def main () :
    status , n, e, d = rsa_keygen (2048)
    message='assqsqsq'
    EncryptMessage=encrypt(e,n,message)
    print('EncryptMessage:',EncryptMessage)
    DecryptMessage=decrypt(d,n,EncryptMessage)
    print('DecryptMessage:',DecryptMessage)
if __name__ == '__main__' :
    main()
