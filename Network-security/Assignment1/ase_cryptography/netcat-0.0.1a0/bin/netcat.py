#!/usr/bin/env python3
"""Python netcat implementation."""
# https://cryptography.io/en/latest/hazmat/primitives/symmetric-encryption/#cryptography.hazmat.primitives.ciphers.Cipher
import argparse
import base64
import os
import random
import re
import socket
import sys
import threading
import gmpy2
import gmpy2 as gmp

# For all the cryptographic primitives use the cryptography module of python pyca (https://cryptography.io)
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC

# -------------------------------------------------------------------------------------------------
# GLOBALS
# -------------------------------------------------------------------------------------------------

# In case the server is running in UDP mode
# it must wait for the client to connect in order
# to retrieve its addr and port in order to be able
# to send data back to it.
UDP_CLIENT_ADDR = None
UDP_CLIENT_PORT = None

NAME = os.path.basename(sys.argv[0])
VERSION = "0.1.0-alpha"

# -------------------------------------------------------------------------------------------------
# HELPER FUNCTIONS
# -------------------------------------------------------------------------------------------------

''' -*- coding: utf-8 -*-
    @Author  : Hao Wang
    @Time    : 2021/07/25 12:00
    @Function: return a n - bit random integer'''


def rand_n(n):
    # return a n - bit random integer
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


''' -*- coding: utf-8 -*-
    @Author  : Hao Wang
    @Time    : 2021/07/25 12:00
    @Function: find the n - bit random prime integer'''


def get_prime(x):
    n = rand_n(x)
    if not gmpy2.is_prime(n):
        n = gmpy2.next_prime(n)
    return n


''' -*- coding: utf-8 -*-
    @Author  : Hao Wang
    @Time    : 2021/07/25 12:00
    @Function: find the greatest common factor by rolling Division'''


def gcd(a, b):
    r = a % b
    while (r != 0):
        a = b
        b = r
        r = a % b
    return b


''' -*- coding: utf-8 -*-
    @Author  : Hao Wang
    @Time    : 2021/07/25 12:00
    @Function: Euler function - violent cycle version'''


def euler(a):
    count = 0
    for i in range(1, a):
        if gcd(a, i) == 1:
            count += 1
    return count


''' -*- coding: utf-8 -*-
    @Author  : Hao Wang
    @Time    : 2021/07/25 12:00
    @Function: Order of output B in mod (a)'''


def order(a, n, b):
    #   输出b在mod(a)中的阶
    #   n是mod(a)群的阶
    p = 1
    while (p <= n and (b ** p % a != 1)):
        p += 1
    if p <= n:
        return p
    else:
        return -1


''' -*- coding: utf-8 -*-
    @Author  : Hao Wang
    @Time    : 2021/07/25 12:00
    @Function: find the primitive root of any number'''


def primitive_root(a):
    n = euler(a)
    for b in range(2, a):
        if order(a, n, b) == n:
            prim = b
            break
    return prim


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


def str2b(data):
    """Convert string into bytes type."""
    try:
        return data.encode("utf-8")
    except UnicodeDecodeError:
        pass
    try:
        return data.encode("utf-8-sig")
    except UnicodeDecodeError:
        pass
    try:
        return data.encode("ascii")
    except UnicodeDecodeError:
        return data.encode("latin-1")


def log(msg, level, verbose):
    """Log messages to stderr."""
    if level == "error":
        print("%s" % (msg), file=sys.stderr)
    elif level == "warning":
        print("%s" % (msg), file=sys.stderr)
    elif level == "info" and verbose > 0:
        print("%s" % (msg), file=sys.stderr)
    elif level == "debubg:" and verbose > 1:
        print("%s" % (msg), file=sys.stderr)
    else:
        print("Fatal, wrong logging level: '%s'. Please report this issue", file=sys.stderr)
        sys.exit(1)


# -------------------------------------------------------------------------------------------------
# CLIENT/SERVER COMMUNICATOIN FUNCTIONS
# -------------------------------------------------------------------------------------------------


def send(s, udp=False, crlf=False, verbose=0):
    print("clict any button to generate Diffie-Hellman algorithm parameters")

    """Send one newline terminated line to a connected socket."""
    # In case of sending data back to an udp client we need to wait
    # until the client has first connected and told us its addr/port
    if udp and UDP_CLIENT_ADDR is None and UDP_CLIENT_PORT is None:
        while UDP_CLIENT_ADDR is None and UDP_CLIENT_PORT is None:
            pass
        if verbose > 0:
            print("Client:     %s:%i" % (UDP_CLIENT_ADDR, UDP_CLIENT_PORT), file=sys.stderr)

    # Loop for the thread
    while True:
        # Read user input
        data = input()
        # Ensure to terminate with desired newline
        if isinstance(data, bytes):
            data = b2str(data)
        if crlf:
            data += "\r\n"
        else:
            data += "\n"

        ''' -*- coding: utf-8 -*-
            @Author  : Hao Wang
            @Time    : 2021/07/25 12:00
            @Function: Data confidentiality, Data integrity and Device authentication'''

        global i
        i = i + 1

        if i == 1:  # The first time communication process: key exchange

            # ------------ STAGE 1: DIFFIE-HELLMAN ALGORITHM ----------------------------------

            privatekey = 5  # assume common private keys are used

            # p_parameter = 65537
            # g_parameter = 2
            p_parameter = int(get_prime(16))  # parameter of Diffie-Hellman algorithm
            g_parameter = int(primitive_root(p_parameter))  # parameter of Diffie-Hellman algorithm
            print("g: ", g_parameter)
            print("p: ", p_parameter)

            publickey = g_parameter ** privatekey % p_parameter  # Mathematically calculated public key
            print("Sent public key: ", publickey)
            print("Diffie-Hellman algorithm parameters all generated")
            key = (publickey ** privatekey % p_parameter).to_bytes(16, 'big')
            print("Actual session key: ", key)

            # ------------ STAGE 2: KEY DERIVATION -------------------------------------------

            print("Key derivation begins!")
            ''' 
            The session key extraction mechanism should include the use of a salt value
            For simplicity we use os.urandom() function directly for random values whenever needed in your protocol
            '''
            salt = os.urandom(16)

            # key derivation
            # include use of a salt value which is part of PBKDF2HMAC key derivation algorithm
            kdf = PBKDF2HMAC(
                algorithm=hashes.SHA256(),
                length=32,
                salt=salt,
                iterations=100000,
            )

            key_derive = kdf.derive(str(publickey).encode())  # key derivation

            # ------------ STAGE 3: SEND MESSAGE -------------------------------------------

            # generate triple (salt, publickey, key_derive) plus DH algorithm parameter p_parameter
            triple_derive_plus = b'salt: ' + salt + b'publickey: ' + str(
                publickey).encode() + b'key_derive: ' + key_derive + b'p_parameter: ' + str(p_parameter).encode()

            # base64 is used to encode binary byte sequence triple into ASCII character sequence text
            triple_derive_plus_base64 = base64.b64encode(triple_derive_plus)  # base64-encoded triple
            data = triple_derive_plus_base64 + b'\n'
            print('The sent key exchange data: ' + data.decode())  # send the base64-encoded data

            print("The key exchange agreement scheme to decide a session key succeed! Please communicate now!\n")

        else:  # The rest of the communication process : AES algorithm
            global k
            k = k + 1

            print("plaintext: ", data)
            data = data.encode()

            # ------------ STAGE 1: ENCRYPTION -------------------------------------------

            iv = os.urandom(16)

            # use the generated session key by the above key exchange step
            cipher = Cipher(algorithms.AES(key), modes.CBC(iv))
            encryptor = cipher.encryptor()
            ct = encryptor.update(data) + encryptor.finalize()

            # ------------ STAGE 2: SEND MESSAGE -----------------------------------------

            # generate double (iv, ct)
            double = b'iv: ' + iv + b'ct: ' + ct

            # base64 is used to encode binary byte sequence triple into ASCII character sequence text
            double_base64 = base64.b64encode(double) + b'\n'  # base64 encode double
            data = double_base64
            print('The ' + str(k) + ' time sent data: ' + data.decode())  # send the base64-encoded triple

        size = len(data)

        if len(data) != 0:
            """print(chardet.detect(data))"""

        send = 0

        # Loop until all bytes have been send
        while send < size:
            try:
                if udp:
                    send += s.sendto(data, (UDP_CLIENT_ADDR, UDP_CLIENT_PORT))
                else:
                    send += s.send(data)
            except (OSError, socket.error) as error:
                print("[Send Error] %s" % (error), file=sys.stderr)
                print(s, file=sys.stderr)
                s.close()
                # exit the thread
                return

    # Close connection when thread stops
    s.close()


def receive(s, udp=False, bufsize=1024, verbose=0):
    """Read one newline terminated line from a connected socket."""
    global UDP_CLIENT_ADDR
    global UDP_CLIENT_PORT

    if verbose > 0:
        print("Receiving:  bufsize=%i" % (bufsize), file=sys.stderr)

    # Loop for the thread
    while True:

        ''' -*- coding: utf-8 -*-
            @Author  : Hao Wang
            @Time    : 2021/07/25 12:00
            @Function: Data confidentiality, Data integrity and Device authentication'''

        global j
        j = j + 1

        data = ""
        size = len(data)

        while True:
            try:
                (byte, addr) = s.recvfrom(bufsize)
                data += b2str(byte)

                # If we're receiving data from a UDP client
                # we can finally set its addr/port in order
                # to send data back to it (see send() function)
                if udp:
                    UDP_CLIENT_ADDR, UDP_CLIENT_PORT = addr

            except socket.error as err:
                print(err, file=sys.stderr)
                print(s, file=sys.stderr)
                s.close()
                sys.exit(1)
            if not data:
                if verbose > 0:
                    print("[Receive Error] Upstream connection is gone", file=sys.stderr)
                s.close()
                # exit the thread
                return
            # Newline terminates the read request
            if data.endswith("\n"):
                break
            # Sometimes a newline is missing at the end
            # If this round has the same data length as previous, we're done
            if size == len(data):
                break
            size = len(data)
        # Remove trailing newlines
        data = data.rstrip("\r\n")
        data = data.rstrip("\n")
        if verbose > 0:
            print("< ", end="", flush=True, file=sys.stderr)

        if j == 1:  # The first time communication process: key exchange

            # ------------ STAGE 1: RECEIVE MESSAGE -------------------------------------------

            print("The received key exchange data", data)  # receive the base64-encoded data
            triple_derive_plus1 = base64.b64decode(data)  # base64-decode it

            salt_loc = triple_derive_plus1.find(b'salt')  # find the start location of salt in the base64-decoded triple
            publickey_loc = triple_derive_plus1.find(
                b'publickey')  # find the publickey location of ciphertext in the base64-decoded triple
            key_derive_loc = triple_derive_plus1.find(
                b'key_derive')  # find the start location of key_derive in the base64-decoded triple
            p_parameter_loc = triple_derive_plus1.find(
                b'p_parameter')

            salt1 = triple_derive_plus1[
                    salt_loc + len("salt") + 2:publickey_loc]  # cut the salt from the base64-decoded triple
            publickey1 = triple_derive_plus1[
                         publickey_loc + len(
                             "publickey") + 2:key_derive_loc]  # cut the publickey from the base64-decoded triple
            key_derive1 = triple_derive_plus1[key_derive_loc + len(
                "key_derive") + 2:p_parameter_loc]  # cut the key_derive from the base64-decoded triple
            # cut the p_parameter from the base64-decoded triple
            p_parameter1 = triple_derive_plus1[p_parameter_loc + len("p_parameter") + 2: len(triple_derive_plus1)]

            # ------------ STAGE 2: KEY DERIVATION -------------------------------------------

            # verify key derivation
            kdf1 = PBKDF2HMAC(
                algorithm=hashes.SHA256(),
                length=32,
                salt=salt1,
                iterations=100000,
            )
            kdf1.verify(publickey1, key_derive1)
            print("Key derivation verification passed!")

            # ------------ STAGE 3: DIFFIE-HELLMAN ALGORITHM ----------------------------------

            privatekey = 5  # assume common private keys are used
            publickey1 = int(publickey1)
            print("The received publickey: ", publickey1)
            key1 = (publickey1 ** privatekey % int(p_parameter1)).to_bytes(16, 'big')
            print("The Calculated actual session key: ", key1)
            print("\n")

        else:  # The rest of the communication process : AES algorithm
            global k
            k = k + 1

            print('The ' + str(k) + ' time received data: ' + data)

            # ------------ STAGE 1: RECEIVE MESSAGE -------------------------------------------

            double1 = base64.b64decode(data)  # receive the base64-encoded double and base64-decode it

            iv_loc = double1.find(b'iv')  # find the iv location of ciphertext in the base64-decoded double
            ct_loc = double1.find(b'ct')  # find the ct location of token in the base64-decoded double

            iv1 = double1[iv_loc + len("iv") + 2:ct_loc]  # cut the iv from the base64-decoded double
            ct1 = double1[ct_loc + len("ct") + 2:len(double1)]  # cut the ct from the base64-decoded double

            # ------------ STAGE 2: DECRYPTION -----------------------------------------------

            # use the generated session key by the above key exchange step
            cipher1 = Cipher(algorithms.AES(key1), modes.CBC(iv1))
            decryptor = cipher1.decryptor()
            plaintext_bytes = decryptor.update(ct1) + decryptor.finalize()
            print("Calculated plaintext: ", plaintext_bytes.decode())

    # Close connection when thread stops
    s.close()


# -------------------------------------------------------------------------------------------------
# CLIENT/SERVER INITIALIZATION FUNCTIONS
# -------------------------------------------------------------------------------------------------

#
# Server/Client (TCP+UDP)
#
def create_socket(udp=False, verbose=0):
    """Create TCP or UDP socket."""
    try:
        if udp:
            if verbose > 0:
                print("Socket:     UDP", file=sys.stderr)
            return socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        else:
            if verbose > 0:
                print("Socket:     TCP", file=sys.stderr)
            return socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    except socket.error as error:
        print("[Socker Error] %s", (error), file=sys.stderr)
        sys.exit(1)


#
# Server (TCP+UDP)
#
def bind(s, host, port, verbose=0):
    """Bind TCP or UDP socket to host/port."""
    if verbose > 0:
        print("Binding:    %s:%i" % (host, port), file=sys.stderr)
    try:
        s.bind((host, port))
    except (OverflowError, OSError, socket.error) as error:
        print("[Bind Error] %s" % (error), file=sys.stderr)
        print(s, file=sys.stderr)
        s.close()
        sys.exit(1)


#
# Server (TCP only)
#
def listen(s, backlog=1, verbose=0):
    """Make TCP socket listen."""
    try:
        if verbose > 0:
            print("Listening:  backlog=%i" % (backlog), file=sys.stderr)
        s.listen(backlog)
    except socket.error as error:
        print("[Listen Error] %s", (error), file=sys.stderr)
        print(s, file=sys.stderr)
        s.close()
        sys.exit(1)


#
# Server (TCP only)
#
def accept(s, verbose=0):
    """Accept connections on TCP socket."""
    try:
        c, addr = s.accept()
    except (socket.gaierror, socket.error) as error:
        print("[Accept Error] %s", (error), file=sys.stderr)
        print(s, file=sys.stderr)
        s.close()
        sys.exit(1)

    host, port = addr
    if verbose > 0:
        print("Client:     %s:%i" % (host, port), file=sys.stderr)

    return c


#
# Client (TCP+UDP)
#
def resolve(hostname, verbose=0):
    """Resolve hostname to IP addr or return False in case of error."""
    if verbose > 0:
        print("Resolving:  %s" % (hostname), file=sys.stderr)
    try:
        return socket.gethostbyname(hostname)
    except socket.gaierror as error:
        print("[Resolve Error] %s" % (error), file=sys.stderr)
        return False


#
# Client (TCP+UDP)
#
def connect(s, addr, port, verbose=0):
    """Connect to a server via IP addr/port."""
    if verbose > 0:
        print("Connecting: %s:%i" % (addr, port), file=sys.stderr)
    try:
        s.connect((addr, port))
    except socket.error as error:
        print("[Connect Error] %s" % (error), file=sys.stderr)
        print(s, file=sys.stderr)
        s.close()
        sys.exit(1)


# -------------------------------------------------------------------------------------------------
# CLIENT
# -------------------------------------------------------------------------------------------------


def run_client(host, port, udp=False, bufsize=1024, crlf=False, verbose=0):
    """Connect to host:port and send data."""
    global UDP_CLIENT_ADDR
    global UDP_CLIENT_PORT

    s = create_socket(udp=udp, verbose=verbose)

    addr = resolve(host, verbose=verbose)
    if not addr:
        s.close()
        sys.exit(1)

    if udp:
        UDP_CLIENT_ADDR = addr
        UDP_CLIENT_PORT = port
    else:
        connect(s, addr, port, verbose=verbose)

    # Start sending and receiving threads
    tr = threading.Thread(
        target=receive, args=(s,), kwargs={"udp": udp, "bufsize": bufsize, "verbose": verbose}
    )
    ts = threading.Thread(
        target=send, args=(s,), kwargs={"udp": udp, "crlf": crlf, "verbose": verbose}
    )
    # If the main thread kills, this thread will be killed too.
    tr.daemon = True
    ts.daemon = True
    # Start threads
    tr.start()
    ts.start()

    # Do cleanup on the main program
    while True:
        if not tr.is_alive():
            s.close()
            sys.exit(0)
        if not ts.is_alive():
            s.close()
            sys.exit(0)


# -------------------------------------------------------------------------------------------------
# SERVER
# -------------------------------------------------------------------------------------------------


def run_server(host, port, udp=False, backlog=1, bufsize=1024, crlf=False, verbose=0):
    """Start TCP/UDP server on host/port and wait endlessly to sent/receive data."""
    s = create_socket(udp=udp, verbose=verbose)

    bind(s, host, port, verbose=verbose)

    if not udp:
        listen(s, backlog=backlog, verbose=verbose)
        c = accept(s, verbose=verbose)
    else:
        c = s

    # start sending and receiving threads
    tr = threading.Thread(
        target=receive, args=(c,), kwargs={"udp": udp, "bufsize": bufsize, "verbose": verbose}
    )
    ts = threading.Thread(
        target=send, args=(c,), kwargs={"udp": udp, "crlf": crlf, "verbose": verbose}
    )
    # if the main thread kills, this thread will be killed too.
    tr.daemon = True
    ts.daemon = True
    # start threads
    tr.start()
    ts.start()

    # do cleanup on the main program
    while True:
        if not tr.is_alive():
            c.close()
            s.close()
            sys.exit(0)
        if not ts.is_alive():
            c.close()
            s.close()
            sys.exit(0)


# -------------------------------------------------------------------------------------------------
# COMMAND LINE ARGUMENTS
# -------------------------------------------------------------------------------------------------


def get_version():
    """Return version information."""
    return """%(prog)s: Version %(version)s (%(url)s) by %(author)s""" % (
        {
            "prog": NAME,
            "version": VERSION,
            "url": "https://github.com/cytopia/netcat",
            "author": "cytopia",
        }
    )


def _args_check_port(value):
    """Check arguments for invalid port number."""
    min_port = 1
    max_port = 65535
    intvalue = int(value)

    if intvalue < min_port or intvalue > max_port:
        raise argparse.ArgumentTypeError("%s is an invalid port number." % value)
    return intvalue


def _args_check_forwards(value):
    """Check forward argument (-L/-R) for correct pattern."""
    match = re.search(r"(.+):(.+)", value)
    if match is None or len(match.groups()) != 2:
        raise argparse.ArgumentTypeError("%s is not a valid 'addr:port' format." % value)
    _args_check_port(match.group(2))
    return value


def get_args():
    """Retrieve command line arguments."""
    parser = argparse.ArgumentParser(
        formatter_class=argparse.RawTextHelpFormatter,
        add_help=False,
        usage="""%(prog)s [-Cnuv] [-e cmd] hostname port
       %(prog)s [-Cnuv] [-e cmd] -l [hostname] port

       # port scan
       %(prog)s [-Cnuv] [-e cmd] -z hostname port

       %(prog)s [-Cnuv] -L addr:port [hostname] port
       %(prog)s [-Cnuv] -R addr:port hostname port
       %(prog)s -V, --version
       %(prog)s -h, --help
       """
              % ({"prog": NAME}),
        description="Netcat implementation in Python with connect, listen and forward mode.",
        epilog="""examples:

  Create bind shell
    %(prog)s -l -e '/bin/bash' 8080

  Create reverse shell
    %(prog)s -e '/bin/bash' example.com 4444

  Local forward: Make localhost port available to another interface
    %(prog)s -L 127.0.0.1:3306 192.168.0.1 3306

  Remote forward: Forward local port to remote server
    %(prog)s -R 127.0.0.1:3306 example.com 4444"""
               % ({"prog": NAME}),
    )

    positional = parser.add_argument_group("positional arguments")
    mode = parser.add_argument_group("mode arguments")
    optional = parser.add_argument_group("optional arguments")
    misc = parser.add_argument_group("misc arguments")

    positional.add_argument(
        "hostname", nargs="?", type=str, help="Address to listen, forward or connect to"
    )
    positional.add_argument(
        "port", type=_args_check_port, help="Port to listen, forward or connect to"
    )

    mode.add_argument(
        "-l",
        "--listen",
        action="store_true",
        help="Listen mode: Enable listen mode for inbound connects",
    )

    mode.add_argument(  # port scan
        "-z",
        "--zero",
        action="store_true",
        default=False,
        help="""[Zero-I/0 mode]:

    """,
    )

    mode.add_argument(
        "-L",
        "--local",
        metavar="addr:port",
        type=_args_check_forwards,
        help="""Local forward mode: Specify local <addr>:<port> to which traffic
should be forwarded to.
Netcat will listen locally (specified by hostname and port) and
forward all traffic to the specified value for -L/--local.""",
    )
    mode.add_argument(
        "-R",
        "--remote",
        metavar="addr:port",
        type=_args_check_forwards,
        help="""Remote forward mode: Specify local <addr>:<port> from which traffic
should be forwarded from.
Netcat will connect remotely (specified by hostname and port) and
for ward all traffic from the specified value for -R/--remote.""",
    )

    optional.add_argument(
        "-e",
        "--exec",
        metavar="cmd",
        type=str,
        help="Execute shell command. Only works with connect or listen mode.",
    )
    optional.add_argument(
        "-C", "--crlf", action="store_true", help="Send CRLF as line-endings (default: LF)",
    )
    optional.add_argument(
        "-n", "--nodns", action="store_true", help="Do not resolve DNS",
    )
    optional.add_argument("-u", "--udp", action="store_true", help="UDP mode")
    optional.add_argument(
        "-v",
        "--verbose",
        action="count",
        default=0,
        help="Be verbose and print info to stderr. Use -vv or -vvv for more verbosity.",
    )
    misc.add_argument("-h", "--help", action="help", help="Show this help message and exit")
    misc.add_argument(
        "-V",
        "--version",
        action="version",
        version=get_version(),
        help="Show version information and exit",
    )
    args = parser.parse_args()

    # Check mutually exclive arguments
    if args.exec is not None and (args.local is not None or args.remote is not None):
        parser.print_usage()
        print(
            "%s: error: -e/--cmd cannot be used together with -L/--local or -R/--remote" % (NAME),
            file=sys.stderr,
        )
        sys.exit(1)
    if args.listen and (args.local is not None or args.remote is not None):
        parser.print_usage()
        print(
            "%s: error: -l/--listen cannot be used together with -L/--local or -R/--remote"
            % (NAME),
            file=sys.stderr,
        )
        sys.exit(1)

    # [MODE] --zero port scan
    if args.zero and (args.listen or args.local or args.remote):
        parser.print_usage()
        print(
            "%s: error: -z/--zero mutually excl. with -l/--listen, -L or -R" % (APPNAME),
            file=sys.stderr,
        )
        sys.exit(1)

    if args.local is not None and args.remote is not None:
        parser.print_usage()
        print(
            "%s: error: -L/--local cannot be used together with -R/--remote" % (NAME),
            file=sys.stderr,
        )
        sys.exit(1)

    # Required arguments
    if args.hostname is None and (not args.listen and args.local is None):
        parser.print_usage()
        print(
            "%s: error: the following arguments are required: hostname" % (NAME), file=sys.stderr,
        )
        sys.exit(1)

    return args


# -------------------------------------------------------------------------------------------------
# MAIN ENTRYPOINT
# -------------------------------------------------------------------------------------------------


def main():
    """Start the program."""
    args = get_args()

    listen_backlog = 1
    receive_buffer = 1024
    hostname = args.hostname if args.hostname is not None else "0.0.0.0"

    ''' -*- coding: utf-8 -*-
        @Author  : Hao Wang
        @Time    : 2021/07/25 12:00
        @Function: count variable'''

    global i
    global j
    global k

    i = 0  # Sender's count variable
    j = 0  # Receiver's count variable
    k = 0  # global count variable

    if args.listen:
        run_server(
            hostname,
            args.port,
            args.udp,
            backlog=listen_backlog,
            bufsize=receive_buffer,
            crlf=args.crlf,
            verbose=args.verbose,
        )
    else:
        run_client(
            args.hostname,
            args.port,
            args.udp,
            bufsize=receive_buffer,
            crlf=args.crlf,
            verbose=args.verbose,
        )


if __name__ == "__main__":
    # Catch Ctrl+c and exit without error message
    try:
        main()
    except KeyboardInterrupt:
        print()
        sys.exit(1)
