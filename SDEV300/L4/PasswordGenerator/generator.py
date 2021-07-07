import hashlib

def get_password_input():
    password = input("Enter a password to encode: ").strip()

    if len(password) < 5:
        print("Please enter a password with a length greater than 5.")
        return get_password_input()
    
    return password

def generate():
    password = get_password_input()
    password = password.encode()

    print("Generating 10 passwords:\n")

    print("MD5:", hashlib.md5(password).hexdigest())
    print("SHA256:", hashlib.sha256(password).hexdigest())
    print("SHA512:", hashlib.sha512(password).hexdigest())
    print("BLAKE2B:", hashlib.blake2b(password).hexdigest())
    print("BLAKE2S:", hashlib.blake2s(password).hexdigest())
    print("SHA1:", hashlib.sha1(password).hexdigest())
    print("SHAKE128:", hashlib.shake_128(password).digest(12))
    print("SHAKE256:", hashlib.shake_256(password).digest(12))
    print("SHA3-224:", hashlib.sha3_224(password).hexdigest())
    print("SHA3-384:", hashlib.sha3_384(password).hexdigest())

generate()