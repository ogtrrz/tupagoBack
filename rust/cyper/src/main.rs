use std::error::Error;
use std::{io, str};
use std::io::ErrorKind;
use std::iter::repeat;
use std::str::from_utf8;
use crypto::aead::{AeadDecryptor, AeadEncryptor};
use crypto::aes_gcm::AesGcm;



/// orig must be a string of the form [hexNonce]/[hexCipherText]/[hexMac]. This
/// is the data returned from encrypt(). This function splits the data, removes
/// the hex encoding, and returns each as a list of bytes.
fn split_iv_data_mac(orig: &str) -> Result<(Vec<u8>, Vec<u8>, Vec<u8>), Box<dyn Error>> {
    let split: Vec<&str> = orig.split('/').into_iter().collect();

    if split.len() != 3 {
        return Err(Box::new(io::Error::from(ErrorKind::Other)));
    }
    let iv_res = hex::decode(split[0]);
    if iv_res.is_err() {
        return Err(Box::new(io::Error::from(ErrorKind::Other)));
    }
    let iv = iv_res.unwrap();

    let data_res = hex::decode(split[1]);
    if data_res.is_err() {
        return Err(Box::new(io::Error::from(ErrorKind::Other)));
    }
    let data = data_res.unwrap();

    let mac_res = hex::decode(split[2]);
    if mac_res.is_err() {
        return Err(Box::new(io::Error::from(ErrorKind::Other)));
    }
    let mac = mac_res.unwrap();

    Ok((iv, data, mac))
}

/// gets a valid key. This must be exactly 16 bytes. if less than 16 bytes, it will be padded with 0.
/// If more than 16 bytes, it will be truncated
fn get_valid_key(key: &str) -> Vec<u8> {
    let mut bytes = key.as_bytes().to_vec();
    if bytes.len() < 16 {
        for j in 0..(16 - bytes.len()) {
            bytes.push(0x00);
        }
    } else if bytes.len() > 16 {
        bytes = bytes[0..16].to_vec();
    }

    bytes
}

///Decryption using AES-GCM 128
///iv_data_mac is a string that contains the iv/nonce, data, and mac values. All these values
/// must be hex encoded, and separated by "/" i.e. [hex(iv)/hex(data)/hex(mac)]. This function decodes
/// the values. key (or password) is the raw (not hex encoded) password
pub fn decrypt(iv_data_mac: &str, key: &str) -> Result<Vec<u8>, Box<dyn Error>> {
    let (iv, data, mac) = split_iv_data_mac(iv_data_mac)?;
    let key = get_valid_key(key);

    let key_size = crypto::aes::KeySize::KeySize128;

    // I don't use the aad for verification. aad isn't encrypted anyway, so it's just specified
    // as &[].
    let mut decipher = AesGcm::new(key_size, &key, &iv, &[]);

    // create a list where the decoded data will be saved. dst is transformed in place. It must be exactly the same
    // size as the encrypted data
    let mut dst: Vec<u8> = repeat(0).take(data.len()).collect();
    let result = decipher.decrypt(&data, &mut dst, &mac);

    if result { println!("Successful decryption"); }
    println!("\nDecrypted {}", str::from_utf8(&dst).unwrap());

    Ok(dst)
}

/// Creates an initial vector (iv). This is also called a nonce
fn get_iv(size: usize) -> Vec<u8> {
    let mut iv = vec![];
    for j in 0..size {
        let r = rand::random();
        iv.push(r);
    }

    iv
}

///encrypt "data" using "password" as the password
/// Output is [hexNonce]/[hexCipher]/[hexMac] (nonce and iv are the same thing)
pub fn encrypt(data: &[u8], password: &str) -> String {
    let key_size = crypto::aes::KeySize::KeySize128;

    //pad or truncate the key if necessary
    let valid_key = get_valid_key(password);
    let iv = get_iv(12); //initial vector (iv), also called a nonce
    let mut cipher = AesGcm::new(key_size, &valid_key, &iv, &[]);

    //create a vec of data.len 0's. This is where the encrypted data will be saved.
    //the encryption is performed in-place, so this vector of 0's will be converted
    //to the encrypted data
    let mut encrypted: Vec<u8> = repeat(0).take(data.len()).collect();

    //create a vec of 16 0's. This is for the mac. This library calls it a "tag", but it's really
    // the mac address. This vector will be modified in place, just like the "encrypted" vector
    // above
    let mut mac: Vec<u8> = repeat(0).take(16).collect();

    //encrypt data, put it into "encrypted"
    cipher.encrypt(data, &mut encrypted, &mut mac[..]);

    //create the output string that contains the nonce, cipher text, and mac
    let hex_iv = hex::encode(iv);
    let hex_cipher = hex::encode(encrypted);
    let hex_mac = hex::encode(mac);
    let output = format!("{}/{}/{}", hex_iv, hex_cipher, hex_mac);

    output
}

fn main() {
    let data = "hello world";
    let password = "12345";

    println!("Data to encrypt: \"{}\" and password: \"{}\"", &data, &password);

    println!("Encrypting now");
    let res = encrypt(data.as_bytes(), password);
    println!("Encrypted response: {}", res);

    println!("Decrypting the response");
    let decrypted_bytes = decrypt(res.as_str(), password).unwrap();
    let decrypted_string = from_utf8(&decrypted_bytes).unwrap();
    println!("Decrypted response: {}", decrypted_string);
}

// use aes_gcm::{
//     aead::{Aead, AeadCore, KeyInit, OsRng},
//     Aes256Gcm,
//     Key, // Or `Aes128Gcm`
//     Nonce,
// };

// fn main() {
//     // The encryption key can be generated randomly:
//     let key = Aes256Gcm::generate_key(OsRng);

//     // Transformed from a byte array:
//     let key: &[u8; 32] = &[42; 32];
//     let key: &Key<Aes256Gcm> = key.into();

//     // Note that you can get byte array from slice using the `TryInto` trait:
//     let key: &[u8] = &[42; 32];
//     let key: [u8; 32] = key.try_into();

//     // Alternatively, the key can be transformed directly from a byte slice
//     // (panicks on length mismatch):
//     let key = Key::<Aes256Gcm>::from_slice(key);

//     let cipher = Aes256Gcm::new(&key);
//     let nonce = Aes256Gcm::generate_nonce(&mut OsRng); // 96-bits; unique per message
//     let ciphertext = cipher.encrypt(&nonce, b"plaintext message".as_ref());
//     let plaintext = cipher.decrypt(&nonce, ciphertext.as_ref());
//     assert_eq!(&plaintext, b"plaintext message");
    
// }




// use aes_gcm_stream::{Aes256GcmStreamDecryptor, Aes256GcmStreamEncryptor};
// use zeroize::Zeroize;

// fn main() {
//     // IMPORTANT! key and nonce SHOULD generate by random
//     let mut key = [0u8; 32];
//     let mut nonce = [0; 12];

//     let mut encryptor = Aes256GcmStreamEncryptor::new(key.clone(), &nonce);

//     let mut ciphertext = vec![];
//     ciphertext.extend_from_slice(&encryptor.update(b"Hello "));
//     ciphertext.extend_from_slice(&encryptor.update(b" World"));
//     ciphertext.extend_from_slice(&encryptor.update(b"!"));
//     let (last_block, tag) = encryptor.finalize();
//     ciphertext.extend_from_slice(&last_block);
//     ciphertext.extend_from_slice(&tag);

//     println!("Ciphertext: {}", hex::encode(&ciphertext));

//     let mut decryptor = Aes256GcmStreamDecryptor::new(key.clone(), &nonce);

//     let mut plaintext = vec![];
//     plaintext.extend_from_slice(decryptor.update(&ciphertext).as_slice());
//     plaintext.extend_from_slice(&decryptor.finalize().expect("decrypt error"));

//     println!("Plaintext: {}", String::from_utf8_lossy(&plaintext));
//     key.zeroize();
//     nonce.zeroize();
// }




