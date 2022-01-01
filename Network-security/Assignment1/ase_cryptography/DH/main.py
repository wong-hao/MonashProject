from base64 import b64encode

from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives.kdf.hkdf import HKDF
from cryptography.hazmat.primitives.kdf.hkdf import HKDFExpand
from cryptography.hazmat.primitives.kdf.concatkdf import ConcatKDFHash
from cryptography.hazmat.primitives.kdf.concatkdf import ConcatKDFHMAC
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
from cryptography.hazmat.primitives.kdf.x963kdf import X963KDF
from cryptography.hazmat.primitives.kdf.kbkdf import (CounterLocation, KBKDFHMAC,
                                                      Mode)
from cryptography.hazmat.primitives.serialization import load_pem_parameters
import argparse
import binascii as ba
import os

from cryptography.hazmat.primitives import hashes, kdf
from cryptography.hazmat.primitives.asymmetric import dh
from cryptography.hazmat.primitives.kdf.hkdf import HKDF

def hh():
    parameters = dh.generate_parameters(generator=2, key_size=512, backend=default_backend())

    a_private_key = parameters.generate_private_key()
    a_peer_public_key = a_private_key.public_key()

    salt = os.urandom(16)

    # derive
    # include use of a salt value which is part of PBKDF2HMAC key derivation algorithm
    kdf = PBKDF2HMAC(
        algorithm=hashes.SHA256(),
        length=32,
        salt=salt,
        iterations=100000,
    )
    key_derive = kdf.derive(str(a_peer_public_key).encode())
    print("key_derive: ", key_derive)

    b_private_key = parameters.generate_private_key()
    b_peer_public_key = b_private_key.public_key()

    a_shared_key = a_private_key.exchange(b_peer_public_key)
    b_shared_key = b_private_key.exchange(a_peer_public_key)
    print("a_shared_key: ", a_shared_key)
    print("len of a_shared_key: ", len(a_shared_key))


def generate():
    p = 0xFFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AACAA68FFFFFFFFFFFFFFFF
    g = 2
    y = 65537
    pn = dh.DHParameterNumbers(p, g)
    parameters = pn.parameters()
    print("parameters: ", parameters)
    peer_public_numbers = dh.DHPublicNumbers(y, pn)

    peer_public_key = peer_public_numbers.public_key()
    print("peer_public_key: ", peer_public_key)
    print(peer_public_key.parameters())

def DH():
    # Generate some parameters. These can be reused.
    parameters = dh.generate_parameters(generator=2, key_size=1024, backend=default_backend())
    # Generate a private key for use in the exchange.
    server_private_key = parameters.generate_private_key()
    # In a real handshake the peer is a remote client. For this
    # example we'll generate another local private key though. Note that in
    # a DH handshake both peers must agree on a common set of parameters.
    peer_private_key = parameters.generate_private_key()
    shared_key = server_private_key.exchange(peer_private_key.public_key())
    # Perform key derivation.
    derived_key = HKDF(
        algorithm=hashes.SHA256(),
        length=32,
        salt=None,
        info=b'handshake data',
    ).derive(shared_key)
    print("derived_key: ", derived_key)

    # And now we can demonstrate that the handshake performed in the
    # opposite direction gives the same final value
    same_shared_key = peer_private_key.exchange(
        server_private_key.public_key()
    )
    same_derived_key = HKDF(
        algorithm=hashes.SHA256(),
        length=32,
        salt=None,
        info=b'handshake data',
    ).derive(same_shared_key)
    print("same_derived_key: ", same_derived_key)

    if derived_key == same_derived_key:
        print("yes")

def DHE():
    # Generate some parameters. These can be reused.
    parameters = dh.generate_parameters(generator=2, key_size=2048)
    # Generate a private key for use in the exchange.
    private_key = parameters.generate_private_key()
    # In a real handshake the peer_public_key will be received from the
    # other party. For this example we'll generate another private key and
    # get a public key from that. Note that in a DH handshake both peers
    # must agree on a common set of parameters.
    peer_public_key = parameters.generate_private_key().public_key()
    shared_key = private_key.exchange(peer_public_key)
    # Perform key derivation.
    derived_key = HKDF(
        algorithm=hashes.SHA256(),
        length=32,
        salt=None,
        info=b'handshake data',
    ).derive(shared_key)
    # For the next handshake we MUST generate another private key, but
    # we can reuse the parameters.
    private_key_2 = parameters.generate_private_key()
    peer_public_key_2 = parameters.generate_private_key().public_key()
    shared_key_2 = private_key_2.exchange(peer_public_key_2)
    derived_key_2 = HKDF(
        algorithm=hashes.SHA256(),
        length=32,
        salt=None,
        info=b'handshake data',
    ).derive(shared_key_2)


def main():
    hh()


if __name__ == '__main__':
    main()
