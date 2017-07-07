import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer
import java.util.Properties

/**
  * Created by vladyslav.bachurin on 7/5/2017.
  */
object KafkaProducerDemo {
  def main(args: Array[String]): Unit = {
    var properties = new Properties
    properties.setProperty("bootstrap.servers", "192.168.99.100:9092")
    properties = ProducerConfig.addSerializerToConfig(properties, new StringSerializer, new StringSerializer)

    properties.setProperty("acks", "1")
    properties.setProperty("retries", "3")
    properties.setProperty("linger.ms", "1")

    println(properties)

    val producer = new KafkaProducer[String, String](properties)
    for (key <- 0 to 10) {
      val producerRecord = new ProducerRecord[String, String]("first_topic", key.toString, s"message with key $key")
      producer.send(producerRecord)
    }

    producer.close

  }

}
