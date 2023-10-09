package tupago.back.crypto;


import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

import java.security.Security;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;


//crear codigo en Rust
public class AES128 {}

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleFipsProvider());
        // Input data and encryption key
        String inputText = "Hello, AES-GCM!";
        String encryptionKey = "ThisIsA128BitKey"; // 128-bit key

        // Generate a random initialization vector (IV) for GCM mode
        byte[] iv = new byte[12]; // 96 bits IV for AES-GCM
        // You should use a cryptographically secure random number generator to generate the IV in practice
        // For simplicity, this example uses a fixed IV.

        // Create a SecretKey object from the encryptionKey
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");

        // Initialize the Cipher with AES/GCM/NoPadding
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new GCMParameterSpec(128, iv));

        // Encrypt the input text
        byte[] encryptedBytes = cipher.doFinal(inputText.getBytes());

        // Encode the encrypted data in Base64
        String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("Original Text: " + inputText);
        System.out.println("Encrypted (Base64): " + encryptedBase64);
    }




//Rust code
//[dependencies]
//    aes = "0.7"
//    base64 = "0.12"


//    extern crate aes;
//    extern crate base64;
//
//    use aes::Aes128;
//    use aes::block_cipher_trait::generic_array::GenericArray;
//    use aes::block_cipher_trait::BlockCipher;
//    use base64::encode;
//    use rand::Rng;
//
//    fn main() {
//    // Define your 128-bit encryption key (16 bytes)
//    let encryption_key: [u8; 16] = [
//    0x2b, 0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6, 0xab, 0xf7, 0x97, 0x75, 0x46, 0x20, 0x63, 0xed,
//    ];
//
//    // Generate a random 96-bit IV (Initialization Vector)
//    let mut iv = [0u8; 12];
//    rand::thread_rng().fill(&mut iv);
//
//    // Message to be encrypted
//    let message = "Hello, AES-GCM!";
//
//    // Encrypt the message
//    let ciphertext = encrypt_aes_gcm(message.as_bytes(), &encryption_key, &iv);
//
//    // Encode the encrypted data in Base64
//    let encrypted_base64 = encode(&ciphertext);
//
//    println!("Original Text: {}", message);
//    println!("Encrypted (Base64): {}", encrypted_base64);
//    }
//
//    fn encrypt_aes_gcm(message: &[u8], key: &[u8], iv: &[u8]) -> Vec<u8> {
//    let cipher = Aes128::new(GenericArray::from_slice(key));
//    let mut ciphertext = vec![0u8; message.len()];
//    let mut tag = vec![0u8; 16]; // GCM authentication tag is 16 bytes
//
//    let nonce = GenericArray::from_slice(iv);
//
//    let cipher_result = aes::gcm::aead_encrypt(&cipher, nonce, &[], message, &mut ciphertext, &mut tag);
//
//    match cipher_result {
//    Ok(_) => {
//    // Append the authentication tag to the ciphertext
//    ciphertext.extend(tag);
//    ciphertext
//    }
//    Err(_) => panic!("Encryption failed"),
//    }
//    }
