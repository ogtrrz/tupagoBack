package tupago.back.crypto;


import java.security.*;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import java.security.Security;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;

//import java.util.TreeSet;




public class SHA512withRSAandMGF1 {}


    //hacer estos metodos con Rust
    public static void main(String[] args) throws Exception {
//        TreeSet<String> algorithms = new TreeSet<>();

        Security.addProvider(new BouncyCastleFipsProvider());

//        for (Provider provider : Security.getProviders())
//            for (Provider.Service service : provider.getServices())
//                if (service.getType().equals("Signature"))
//                    algorithms.add(service.getAlgorithm());
//        for (String algorithm : algorithms)
//            System.out.println(algorithm);
        // Generate a key pair (public and private keys)
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // Message to be signed
        String message = "Hello, world!";

        // Create a signature instance using SHA512withRSAandMGF1
        Signature signature = Signature.getInstance("SHA512withRSAandMGF1");

        // Initialize the signature with the private key for signing
        signature.initSign(privateKey);

        // Update the signature with the data to be signed
        signature.update(message.getBytes());

        // Generate the digital signature
        byte[] digitalSignature = signature.sign();

        System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(digitalSignature));

        // Verify the signature using the public key
        signature.initVerify(publicKey);
        signature.update(message.getBytes());

        boolean verified = signature.verify(digitalSignature);
        System.out.println("Signature Verified: " + verified);
    }



//Rust code openssl is FIPS comply

//[dependencies]
//    openssl = "0.10"
//    rsa = "0.4"

//    extern crate openssl;
//    extern crate rsa;
//
//    use openssl::rsa::Rsa;
//    use openssl::sign::{Signer, Verifier};
//    use rsa::padding::PaddingScheme;
//    use rsa::RSAPrivateKey;
//
//    fn main() {
//    // Generate or load your RSA private key
//    let private_key_pem = include_str!("private_key.pem");
//    let rsa_private_key = Rsa::private_key_from_pem(private_key_pem.as_bytes()).expect("Failed to load private key");
//
//    // Generate or load your RSA public key (for verification)
//    let public_key_pem = include_str!("public_key.pem");
//    let rsa_public_key = Rsa::public_key_from_pem(public_key_pem.as_bytes()).expect("Failed to load public key");
//
//    // Message to be signed
//    let message = b"Hello, world!";
//
//    // Create a signer with SHA512withRSAandMGF1 padding scheme
//    let mut signer = Signer::new(rsa_private_key.clone()).expect("Failed to create signer");
//    signer.set_rsa_padding(PaddingScheme::PKCS1_PSS_SHA512_MGF1);
//
//    // Sign the message
//    signer.update(message).expect("Failed to update signer");
//    let signature = signer.sign_to_vec().expect("Failed to sign");
//
//    // Verify the signature
//    let mut verifier = Verifier::new(rsa_public_key.clone()).expect("Failed to create verifier");
//    verifier.set_rsa_padding(PaddingScheme::PKCS1_PSS_SHA512_MGF1);
//
//    let verified = verifier.verify(message, &signature).expect("Failed to verify");
//
//    if verified {
//    println!("Signature verified successfully!");
//    } else {
//    println!("Signature verification failed.");
//    }
//    }
//
//
////kafka consumer producer with rust
//[dependencies]
//    rdkafka = "0.24"
//
//    extern crate rdkafka;
//    use rdkafka::producer::{FutureProducer, FutureRecord};
//    use rdkafka::util::get_rdkafka_version;
//
//    #[tokio::main]
//    async fn main() {
//    let producer: FutureProducer = rdkafka::config::ClientConfig::new()
//    .set("bootstrap.servers", "localhost:9092")
//    .create()
//    .expect("Producer creation failed");
//
//    println!("rdkafka version: {}", get_rdkafka_version());
//
//    let topic = "my-topic";
//
//    for i in 0..10 {
//    let message = format!("Message {}", i);
//    let record = FutureRecord::to(topic)
//    .key("key")
//    .payload(&message);
//
//    match producer.send(record, 0).await {
//    Ok(delivery) => {
//    println!(
//    "Sent message '{}' to topic '{}' - partition: {}, offset: {}",
//    message,
//    topic,
//    delivery.partition(),
//    delivery.offset()
//    );
//    }
//    Err((e, _message)) => {
//    eprintln!("Failed to send message: {:?}", e);
//    }
//    }
//    }
//    }
//
//    extern crate rdkafka;
//    use rdkafka::consumer::{Consumer, StreamConsumer};
//    use rdkafka::message::BorrowedMessage;
//    use rdkafka::config::ClientConfig;
//
//    fn main() {
//    let consumer: StreamConsumer = ClientConfig::new()
//    .set("bootstrap.servers", "localhost:9092")
//    .set("group.id", "my-consumer-group")
//    .create()
//    .expect("Consumer creation failed");
//
//    let topic = "my-topic";
//
//    consumer.subscribe(&[topic]).expect("Subscription failed");
//
//    println!("Starting Kafka consumer...");
//
//    for message in consumer.start().iter() {
//    match message {
//    Ok(borrowed_message) => process_message(&borrowed_message),
//    Err(err) => eprintln!("Error while processing message: {:?}", err),
//    }
//    }
//    }
//
//    fn process_message(msg: &BorrowedMessage) {
//    let key = msg.key().map(|k| k.to_owned());
//    let payload = msg.payload().map(|p| String::from_utf8_lossy(p).to_string());
//
//    println!(
//    "Received message: key='{:?}', payload='{:?}' - partition: {}, offset: {}",
//    key,
//    payload,
//    msg.partition(),
//    msg.offset()
//    );
//    }
//
//
//
//    //Redis Consumer Producer Strems
//    [dependencies]
//    redis = "0.16"
//
//
//    extern crate redis;
//
//    use redis::Commands;
//    use std::thread::sleep;
//    use std::time::Duration;
//
//    fn main() {
//    let client = redis::Client::open("redis://127.0.0.1/").expect("Failed to connect to Redis");
//    let conn = client.get_connection().expect("Failed to get Redis connection");
//
//    let stream_name = "mystream";
//    let mut counter = 1;
//
//    loop {
//    let message = format!("Message {}", counter);
//    let _result: () = conn.xadd(stream_name, "*", &[("message", message)]).expect("Failed to add message to stream");
//    println!("Produced: {}", message);
//    counter += 1;
//
//    // Sleep for a while to control the message rate
//    sleep(Duration::from_secs(1));
//    }
//    }
//
//    extern crate redis;
//
//    use redis::Commands;
//
//    fn main() {
//    let client = redis::Client::open("redis://127.0.0.1/").expect("Failed to connect to Redis");
//    let conn = client.get_connection().expect("Failed to get Redis connection");
//
//    let stream_name = "mystream";
//    let consumer_group = "mygroup";
//    let consumer_name = "myconsumer";
//
//    // Create a consumer group if it doesn't exist
//    let _result: () = conn.xgroup_create(stream_name, consumer_group, "0", true).unwrap_or(());
//
//    loop {
//    let messages: Vec<(String, Vec<(String, String)>)> = conn
//    .xreadgroup(
//    consumer_group,
//    consumer_name,
//    &[stream_name],
//    &[">"],
//    1, // Number of messages to read at a time
//    false, // Noack mode (acknowledgement is not implemented in this example)
//    )
//    .expect("Failed to read from stream");
//
//    for (_stream, message_data) in messages.iter() {
//    for (message_id, fields) in message_data.iter() {
//    let message = fields
//    .iter()
//    .find(|(key, _)| key == "message")
//    .map(|(_, value)| value.to_owned())
//    .unwrap_or_else(|| "No message field".to_owned());
//
//    println!("Received: {} - Message ID: {}", message, message_id);
//
//    // Acknowledge the message
//    let _ack_result: () = conn
//    .xack(stream_name, consumer_group, &[message_id])
//    .expect("Failed to acknowledge message");
//    }
//    }
//    }
//    }
//

//Rust en modo servidor para el Consumer
//Este condigo no tiene aknowledgemnt y lee de 1 en uno
//Debemos hacer que cuente dependiendo maximo proceso por milisegundo
//y tomar de ese numero en ese numero por milisegundo si el stream tiene
//menos que esos tomarlos y procesarlos
//checar como funciona en prod agregar el aknoledge y manejar el dead letter

//[dependencies]
//    actix = "3.2"
//    actix-rt = "2.7"
//    redis = "0.16"
//
//
//
//    use actix::{Actor, ActorContext, Addr, StreamHandler};
//    use actix_rt::System;
//    use redis::aio::Connection;
//    use redis::AsyncCommands;
//    use std::time::Duration;
//
//// Define a custom actor to handle Redis streaming
//    struct RedisConsumer {
//    redis: Addr<RedisActor>,
//    }
//
//    impl RedisConsumer {
//    fn new(redis: Addr<RedisActor>) -> Self {
//    Self { redis }
//    }
//    }
//
//    impl Actor for RedisConsumer {
//    type Context = actix::Context<Self>;
//
//    fn started(&mut self, ctx: &mut Self::Context) {
//        // Start consuming messages from Redis Stream
//        let stream_name = "mystream";
//        let consumer_group = "mygroup";
//        let consumer_name = "myconsumer";
//
//        let redis_actor = self.redis.clone();
//        ctx.spawn(async move {
//        let mut connection = redis_actor.get_connection().await.expect("Failed to get Redis connection");
//        let mut last_id: Option<String> = None;
//
//    loop {
//    let messages: Vec<(String, Vec<(String, String)>)> = connection
//    .xreadgroup(
//    consumer_group,
//    consumer_name,
//    &[stream_name],
//    &[">"],
//    1, // Number of messages to read at a time
//    false, // Noack mode (acknowledgement is not implemented in this example)
//    )
//    .await
//    .expect("Failed to read from stream");
//
//    for (_stream, message_data) in messages.iter() {
//    for (message_id, fields) in message_data.iter() {
//    let message = fields
//    .iter()
//    .find(|(key, _)| key == "message")
//    .map(|(_, value)| value.to_owned())
//    .unwrap_or_else(|| "No message field".to_owned());
//
//    println!("Received: {} - Message ID: {}", message, message_id);
//
//    // Acknowledge the message
//    let _ack_result: () = connection
//    .xack(stream_name, consumer_group, &[message_id.clone()])
//    .await
//    .expect("Failed to acknowledge message");
//    }
//    }
//
//    // Remember the last processed message ID
//    if let Some(last_message_id) = messages.last().map(|(_, data)| data[0].0.clone()) {
//    last_id = Some(last_message_id);
//    }
//
//    // Add an idle period to avoid high CPU usage
//    tokio::time::sleep(Duration::from_secs(1)).await;
//    }
//    });
//    }
//    }
//
//// Define a Redis actor to manage the Redis connection
//    struct RedisActor {
//    connection: Connection,
//    }
//
//    impl Actor for RedisActor {
//    type Context = actix::Context<Self>;
//}
//
//    impl RedisActor {
//    async fn new(redis_url: &str) -> Self {
//    let client = redis::Client::open(redis_url).expect("Failed to connect to Redis");
//    let connection = client.get_async_connection().await.expect("Failed to get Redis connection");
//    Self { connection }
//    }
//
//    fn get_connection(&self) -> Connection {
//    self.connection.clone()
//    }
//    }
//
//    #[actix_rt::main]
//    async fn main() {
//    let redis_url = "redis://127.0.0.1/";
//    let redis_actor = RedisActor::new(redis_url).await;
//    let redis_actor_addr = redis_actor.start();
//
//    // Start the Actix system
//    System::new().block_on(async {
//    let _consumer = RedisConsumer::new(redis_actor_addr.clone());
//    actix::Arbiter::spawn(_consumer);
//
//    actix::System::current().stop();
//    });
//    }
